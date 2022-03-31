package com.xyzcorp.httpclient;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class HttpTest {

    String urlString = "https://gist.githubusercontent" +
        ".com/dhinojosa/877425fb98a939a816e2c56f02bbedd0/raw" +
        "/84158bf19d58dca011cecf267eae0307232b76f3/countries.json";

    @Test
    void testBasicApiCall() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request =
            HttpRequest
                .newBuilder()
                .uri(URI.create(urlString))
                .build();

        HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    @Test
    void testSimpleAsyncGet() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request =
            HttpRequest
                .newBuilder()
                .uri(URI.create(urlString))
                .build();

        CompletableFuture<HttpResponse<String>> future =
            client.sendAsync(request,
                HttpResponse.BodyHandlers.ofString());

        future
            .thenApply(HttpResponse::body)
            .thenAccept(System.out::println);

        Thread.sleep(30000);
    }

    @Test
    void testSimpleAsyncGetJson() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request =
            HttpRequest
                .newBuilder()
                .uri(URI.create(urlString))
                .build();

        CompletableFuture<HttpResponse<String>> future =
            client.sendAsync(request,
                HttpResponse.BodyHandlers.ofString());

        CompletableFuture<Map<String, Country>> mapCompletableFuture = future
            .thenApply(HttpResponse::body)
            .thenApply(JSONDeserializer::processJson);


        CompletableFuture<String> stringCompletableFuture = mapCompletableFuture
            .thenApplyAsync(m -> CountryFunctions.findLanguagesByRegion(m, "en", "Americas"));

        mapCompletableFuture
            .orTimeout(15, TimeUnit.SECONDS)
            .thenAccept(System.out::println);

        Thread.sleep(15000);
    }
}
