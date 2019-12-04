package task3;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    private static final String pathBase = "http://dnd5eapi.co/api/monsters/";

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            String resource = getResource(pathBase + i);
            System.out.println(resource);
        }

        Executor executor = Executors.newCachedThreadPool();

        for (int i = 1; i <= 200; i++) {
            getResourceAsync(pathBase + i, executor)
                    .thenAccept(System.out::println);
        }
    }

    private static String getResource(String path) {
        RestTemplate restTemplate = new RestTemplate();
        Monster object = restTemplate.getForObject(path, Monster.class);
        return object.toString();
    }

    private static CompletableFuture<String> getResourceAsync(String path, Executor executor) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        executor.execute(() -> completableFuture.complete(getResource(path)));

        return completableFuture;
    }
}
