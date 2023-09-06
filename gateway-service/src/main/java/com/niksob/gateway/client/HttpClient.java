package com.niksob.gateway.client;

import com.niksob.gateway.exception.http.HttpClientException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class HttpClient {

    private final WebClient.Builder webBuilder;

    public <T> Mono<T> sendGetRequest(String uri, Class<T> clazz) {
        final WebClient client = webBuilder.baseUrl(uri).build();

        return client.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(clazz)
                .doOnError(HttpClientException::new);
    }

    public <T, R> Mono<R> sendPostRequest(String uri, T body, Class<T> bodyClass, Class<R> resultClass) {
        final WebClient client = webBuilder.baseUrl(uri).build();

        return client.post()
                .body(Mono.just(body), bodyClass)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(resultClass)
                .doOnError(t -> {
                    final String message = String.format(
                            "Request to uri: %s was failed. Exception message: %s",
                            uri, t.getMessage()
                    );
                    throw new HttpClientException(message);
                });
    }

    public <T> Mono<Void> sendPostRequest(String uri, T body, Class<T> bodyClass) {
        final WebClient client = webBuilder.baseUrl(uri).build();

        return client.post()
                .body(Mono.just(body), bodyClass)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return Mono.empty();
                    }
                    throw new HttpClientException(String.format("Request to uri: %s was failed.", uri));
                });
    }
}
