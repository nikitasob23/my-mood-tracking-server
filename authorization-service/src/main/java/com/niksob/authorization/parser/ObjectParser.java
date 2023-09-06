package com.niksob.authorization.parser;

import org.slf4j.event.KeyValuePair;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ObjectParser {

    private final BinaryOperator<Optional<Object>> throwDuplicateKeyExceptionOperator = (k, v) -> {
        throw new IllegalStateException(String.format("Duplicate key %s", k));
    };

    public <T> T parse(T inflatingObj, T storageObj) {

        if (inflatingObj.getClass().isRecord()) {
            return parseRecord(inflatingObj, storageObj);
        }
        return parseClass(inflatingObj, storageObj);
    }

    private String convertGetterToSetterName(String getterName) {
        return getterName.replaceFirst("get", "set");
    }

    @SuppressWarnings("unchecked")
    private <T> T parseRecord(T inflatingObj, T storageObj) {

        var inflatingObjAccessorNameToResultMap = getAccessorNameToResultMap(inflatingObj);
        var storageObjAccessorNameToResultMap = getAccessorNameToResultMap(storageObj);

        var inflatedObjMap = concatNonNullFields(
                inflatingObjAccessorNameToResultMap,
                storageObjAccessorNameToResultMap
        );

        final Constructor<?> constructor = getConstructor(inflatingObj);

        final Object[] params = inflatedObjMap.values().stream()
                .map(this::convertOptionalToObj)
                .toArray();

        try {
            return (T) constructor.newInstance(params);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Optional<Object>> concatNonNullFields(
            Map<String, Optional<Object>> inflatingNameToResultMap,
            Map<String, Optional<Object>> storageNameToResultMap
    ) {
        storageNameToResultMap.forEach((methodName, storageObjAccessorResult) ->

                inflatingNameToResultMap.merge(methodName, storageObjAccessorResult,
                        (inflatingAccessorResult, newAccessorResult) ->
                                inflatingAccessorResult.isPresent() ? inflatingAccessorResult : newAccessorResult
                )
        );
        return inflatingNameToResultMap;
    }

    private Object convertOptionalToObj(Optional<Object> optional) {
        if (optional.isEmpty()) {
            return null;
        }
        return optional.get();
    }

    private Constructor<?> getConstructor(Object obj) {

        final Class<?> clazz = obj.getClass();

        final Class<?>[] paramTypes = Stream.of(clazz)
                .map(Class::getRecordComponents)
                .flatMap(Arrays::stream)
                .map(RecordComponent::getAccessor)
                .map(Method::getReturnType)
                .toArray(Class<?>[]::new);

        try {
            return clazz.getDeclaredConstructor(paramTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> Map<String, Optional<Object>> getAccessorNameToResultMap(T obj) {
        return Stream.of(obj)
                .map(Object::getClass)
                .map(Class::getRecordComponents)
                .flatMap(Arrays::stream)
                .map(RecordComponent::getAccessor)
                .collect(Collectors.toMap(
                        Method::getName,
                        method -> getMethodResult(obj, method),
                        throwDuplicateKeyExceptionOperator,
                        LinkedHashMap::new
                ));
    }

    private <T> Optional<Object> getMethodResult(T obj, Method method) {
        try {
            final Object methodResult = method.invoke(obj);
            if (methodResult == null) {
                return Optional.empty();
            }
            return Optional.of(methodResult);

        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T parseClass(T inflatingObj, T storageObj) {
        final Set<String> inflatingNullResultGetters = getterNamesWithNullResults(inflatingObj);
        final Set<KeyValuePair> storageGetterToResultMap = pairMethodNameToResult(storageObj);

        return setValuesIfNull(inflatingObj, inflatingNullResultGetters, storageGetterToResultMap);
    }

    private Set<String> getterNamesWithNullResults(Object object) {
        return Stream.of(object)
                .map(this::pairMethodNameToResult)
                .flatMap(Set::stream)
                .filter(this::returnedMethodValueIsNull)
                .map(methodNameToResult -> methodNameToResult.key)
                .collect(Collectors.toSet());
    }

    private Set<KeyValuePair> pairMethodNameToResult(Object object) {
        return Stream.of(object)
                .map(Object::getClass)
                .map(Class::getMethods).flatMap(Arrays::stream)
                .map(Method::getName)
                .filter(this::filterGetter)
                .map(getterName -> pairMethodNameToResult(object, getterName))
                .collect(Collectors.toSet());
    }

    private <T> T setValuesIfNull(
            T methodOwner,
            Set<String> inflatingNullResultGetters,
            Set<KeyValuePair> storageGetterToResultMap
    ) {
        final Map<String, Object> setterNameToArgMap = storageGetterToResultMap.stream()
                .filter(getterToResult -> inflatingNullResultGetters.contains(getterToResult.key))
                .map(this::convertToSetter)
                .collect(Collectors.toMap(pair -> pair.key, pair -> pair.value));

        final Map<Object, Object> setterToArgMap = Stream.of(methodOwner)
                .map(Object::getClass)
                .map(Class::getMethods).flatMap(Arrays::stream)
                .map(method -> new KeyValuePair(method.getName(), method))
                .filter(nameToMethod -> setterNameToArgMap.containsKey(nameToMethod.key))
                .collect(Collectors.toMap(pair -> pair.value, pair -> setterNameToArgMap.get(pair.key)));

        setterToArgMap.forEach((setterObj, arg) -> {

            final Method setter = (Method) setterObj;

            try {
                setter.invoke(methodOwner, arg);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
        return methodOwner;
    }

    private KeyValuePair convertToSetter(KeyValuePair getterToResult) {
        return Stream.of(getterToResult)
                .map(this::removeGetPrefixToMethodName)
                .map(this::addSetPrefixToMethodName)
                .findFirst().get();
    }

    private KeyValuePair addSetPrefixToMethodName(KeyValuePair methodNameToResult) {
        final String setterName = "set" + methodNameToResult.key;
        return new KeyValuePair(setterName, methodNameToResult.value);
    }

    private KeyValuePair removeGetPrefixToMethodName(KeyValuePair keyValuePair) {
        final String methodName = keyValuePair.key.substring(3);
        return new KeyValuePair(methodName, keyValuePair.value);
    }

    private boolean returnedMethodValueIsNull(KeyValuePair methodNameToResult) {
        return methodNameToResult.value == null;
    }

    private KeyValuePair pairMethodNameToResult(Object methodOwner, String methodName) {
        Object methodResult;
        try {
            methodResult = methodOwner
                    .getClass()
                    .getDeclaredMethod(methodName)
                    .invoke(methodOwner);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return new KeyValuePair(methodName, methodResult);
    }

    private boolean filterGetter(String methodName) {
        return methodName.startsWith("get") && !methodName.equals("getClass");
    }
}
