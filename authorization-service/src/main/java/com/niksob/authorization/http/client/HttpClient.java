package com.niksob.authorization.http.client;

import com.niksob.authorization.exception.http.HttpClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HttpClient {

    private final WebClient.Builder webBuilder;

    public <T> Mono<T> sendGetRequest(String uri, Class<T> returnClass) {
        final WebClient client = webBuilder.baseUrl(uri).build();

        return client.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(returnClass)
                .switchIfEmpty(Mono.error(new HttpClientException()));
    }

    public <T, R> Mono<T> sendPostRequest(String uri, R body, Class<R> bodyClass, Class<T> resultClass) {
        final WebClient client = webBuilder.baseUrl(uri).build();

        return client.post()
                .body(Mono.just(body), bodyClass)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(resultClass)
                .doOnError(HttpClientException::new);
    }
}
