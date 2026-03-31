package com.practice.algo.java;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class VarHandleExample {
}

class Counter {
    int value = 0;
}

class Main {
    static VarHandle VALUE_HANDLE;

    static {
        try {
            VALUE_HANDLE = MethodHandles.lookup()
                    .findVarHandle(Counter.class, "value", int.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
