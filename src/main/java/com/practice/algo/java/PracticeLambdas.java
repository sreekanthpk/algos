package com.practice.algo.java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

class Transaction {
    String type;
    int amount;

    public Transaction(String food, int i) {
        this.type = food;
        this.amount = i;
    }
}

public class PracticeLambdas {

    public static void main(String... args){
        List<String> words = Arrays.asList("apple","banana","apple","orange","banana","apple");
        words.stream()
                .collect(Collectors.groupingBy(w -> w,Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);

        List<Transaction> transactions = Arrays.asList(
                new Transaction("Food", 50),
                new Transaction("Travel", 200),
                new Transaction("Shopping", 120),
                new Transaction("Food", 30),
                new Transaction("Travel", 150),
                new Transaction("Bills", 100),
                new Transaction("Shopping", 80),
                new Transaction("Food", 70),
                new Transaction("Bills", 50),
                new Transaction("Travel", 100)
        );

        transactions.stream().collect(Collectors.groupingBy(w->w.type,Collectors.summingInt(w->w.amount))).entrySet().stream().forEach(System.out::println);

        //predicate
        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println(isEven.test(4)); // tr

        Function<String, Integer> length = s -> s.length();
        System.out.println(length.apply("Hello")); // 5

        Consumer<String> printer = s -> System.out.println(s);
        printer.accept("Hello Lambda!"); // Prints Hello Lambda!

        Supplier<Double> randomValue = () -> Math.random();
        System.out.println(randomValue.get()); // Random double

    }


}
