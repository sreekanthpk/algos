package com.practice.algo.multithreading;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class ParallelFileProcessor {

    record FileResult(
            Path path,
            long lines,
            long words,
            long characters
    ) {}

    static class FileProcessor implements Callable<FileResult> {

        private final Path path;

        FileProcessor(Path path) {
            this.path = path;
        }

        @Override
        public FileResult call() throws IOException {

            long lines = 0;
            long words = 0;
            long characters = 0;

            try (Stream<String> stream = Files.lines(path)) {

                for (String line : (Iterable<String>) stream::iterator) {

                    lines++;
                    characters += line.length();

                    if (!line.isBlank()) {
                        words += line.trim()
                                .split("\\s+")
                                .length;
                    }
                }
            }

            return new FileResult(
                    path,
                    lines,
                    words,
                    characters
            );
        }
    }

    public static void main(String[] args) throws Exception {

        Path directory = Path.of(args.length > 0
                ? args[0]
                : "input");

        int numberOfWorkers = 4;

        ExecutorService executor =
                Executors.newFixedThreadPool(numberOfWorkers);

        CompletionService<FileResult> completionService =
                new ExecutorCompletionService<>(executor);

        int submittedTasks = 0;

        try {

            // -------------------------------------------------
            // 1. Discover files using Java NIO
            // -------------------------------------------------

            try (Stream<Path> paths = Files.walk(directory)) {

                for (Path path : (Iterable<Path>) paths
                        .filter(Files::isRegularFile)
                        .filter(p -> p.toString()
                                .toLowerCase()
                                .endsWith(".txt"))::iterator) {

                    // -------------------------------------------------
                    // 2. Submit file-processing task
                    // -------------------------------------------------

                    completionService.submit(
                            new FileProcessor(path)
                    );

                    submittedTasks++;
                }
            }

            // -------------------------------------------------
            // 3. Process results as soon as workers finish
            // -------------------------------------------------

            for (int i = 0; i < submittedTasks; i++) {

                Future<FileResult> future =
                        completionService.take();

                try {

                    FileResult result = future.get();

                    System.out.printf(
                            "%s | lines=%d | words=%d | chars=%d%n",
                            result.path(),
                            result.lines(),
                            result.words(),
                            result.characters()
                    );

                } catch (ExecutionException e) {

                    System.err.println(
                            "File processing failed: "
                                    + e.getCause()
                    );
                }
            }

        } finally {

            // -------------------------------------------------
            // 4. Graceful shutdown
            // -------------------------------------------------

            executor.shutdown();

            if (!executor.awaitTermination(
                    10,
                    TimeUnit.SECONDS)) {

                executor.shutdownNow();
            }
        }
    }
}