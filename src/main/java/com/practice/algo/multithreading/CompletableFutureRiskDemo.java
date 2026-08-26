package com.practice.algo.multithreading;

import java.util.concurrent.*;

public class CompletableFutureRiskDemo {

    record CreditRisk(boolean approved) {}
    record PositionRisk(double exposure) {}
    record MarketRisk(double volatility) {}

    record RiskResult(
            CreditRisk credit,
            PositionRisk position,
            MarketRisk market) {
    }

    public static void main(String[] args) {

        ExecutorService executor =
                Executors.newFixedThreadPool(10);

        try {

            CompletableFuture<CreditRisk> credit =
                    CompletableFuture.supplyAsync(
                            CompletableFutureRiskDemo::checkCreditRisk,
                            executor);

            CompletableFuture<PositionRisk> position =
                    CompletableFuture.supplyAsync(
                            CompletableFutureRiskDemo::checkPositionRisk,
                            executor);

            CompletableFuture<MarketRisk> market =
                    CompletableFuture.supplyAsync(
                            CompletableFutureRiskDemo::checkMarketRisk,
                            executor);

            CompletableFuture<RiskResult> riskResult =
                    credit.thenCombine(
                            position,
                            (c, p) -> new PartialRisk(c, p)
                    ).thenCombine(
                            market,
                            (partial, m) ->
                                    new RiskResult(
                                            partial.credit(),
                                            partial.position(),
                                            m
                                    )
                    );

            RiskResult result = riskResult.join();

            System.out.println(result);

        } finally {
            executor.shutdown();
        }
    }

    record PartialRisk(
            CreditRisk credit,
            PositionRisk position) {
    }

    static CreditRisk checkCreditRisk() {

        sleep(1000);

        System.out.println(
                Thread.currentThread().getName()
                        + " credit risk");

        return new CreditRisk(true);
    }

    static PositionRisk checkPositionRisk() {

        sleep(1500);

        System.out.println(
                Thread.currentThread().getName()
                        + " position risk");

        return new PositionRisk(500_000);
    }

    static MarketRisk checkMarketRisk() {

        sleep(700);

        System.out.println(
                Thread.currentThread().getName()
                        + " market risk");

        return new MarketRisk(0.25);
    }

    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}