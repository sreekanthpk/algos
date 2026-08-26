package com.practice.algo.multithreading;

import java.util.function.*;

public class FuncInter {

    public static void main(String... args){
        Supplier<String> supplier = () -> "Hello";

        System.out.println(supplier.get());

        Consumer<String> consumer =
                name -> System.out.println("Hello " + name);

        consumer.accept("John");

        Function<String, Integer> length =
                String::length;

        System.out.println(length.apply("Java"));

        Predicate<Integer> isEven =
                x -> x % 2 == 0;

        System.out.println(isEven.test(10));


        BiFunction<Integer, Integer, Integer> add =
                (a, b) -> a + b;

        System.out.println(add.apply(10, 20));

        BiConsumer<String, Integer> printPerson =
                (name, age) ->
                        System.out.println(name + " is " + age);

        printPerson.accept("John", 30);

        BiPredicate<Integer, Integer> isGreater =
                (a, b) -> a > b;

        System.out.println(isGreater.test(20, 10));

        UnaryOperator<Integer> square =
                x -> x * x;

        System.out.println(square.apply(5));

        BinaryOperator<Integer> add1 =
                (a, b) -> a + b;

        System.out.println(add1.apply(10, 20));




    }
}
