package task3;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final String pathBase = "http://dnd5eapi.co/api/monsters/";

    public static void main(String[] args) {
        long startSync = System.currentTimeMillis();
        for (int i = 1; i <= 20; i++) {
            String resource = getResource(pathBase + i);
//            System.out.println(resource);
        }
        long endSync = System.currentTimeMillis();
        System.out.println("CURRENT: " + (endSync - startSync));

        ExecutorService executor = Executors.newFixedThreadPool(16);

        long startAsync = System.currentTimeMillis();
        List<CompletableFuture<String>> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            list.add(getResourceAsync(pathBase + i, executor));
//                    .thenAccept(System.out::println));
        }

        for(CompletableFuture<?> a : list)
            a.join();

        long endAsync = System.currentTimeMillis();
        System.out.println("CURRENT: " + (endAsync - startAsync));
        executor.shutdown();
    }

    private static String getResource(String path) {
        RestTemplate restTemplate = new RestTemplate();
        Monster object = restTemplate.getForObject(path, Monster.class);
        return object.toString();
    }

    private static CompletableFuture<String> getResourceAsync(String path, ExecutorService executor) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        executor.submit(() -> completableFuture.complete(getResource(path)));

        return completableFuture;
    }
}
