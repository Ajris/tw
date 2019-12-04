package task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/home/ajris/IdeaProjects/tw/lab8/bible.txt");
        Files.lines(path)
//                .parallel()
                .map(a -> a.split("\\s+").length)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);
    }
}
