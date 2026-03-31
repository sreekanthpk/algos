package com.practice.algo.queue;

public class MyArrayDeQueue<T>  {
    private int head;
    private int tail;
    T[] array;
    private int mask;
    int currentSize = 0;


   public MyArrayDeQueue(int size){
       if(Integer.bitCount(size)!=1){
           throw new IllegalArgumentException("size must be a power of 2");
       }
       mask = size - 1;

       array = (T[])new Object[size];
       head = 0;
       tail = 0;

   }

    public void addFirst(T t) {
        if (currentSize == array.length) throw new IllegalStateException("Deque full");
        head = (head - 1) & mask;
        array[head] = t;
        currentSize++;
    }

    public void addLast(T t) {
        if (currentSize == array.length) throw new IllegalStateException("Deque full");
        array[tail] = t;
        tail = (tail + 1) & mask;
        currentSize++;
    }


    public T removeFirst() {
        if (currentSize == 0) throw new IllegalStateException("Deque empty");
        T value = array[head];
        array[head] = null;
        head = (head + 1) & mask;
        currentSize--;
        return value;
    }


    public T removeLast() {
        if (currentSize == 0) throw new IllegalStateException("Deque empty");
        tail = (tail - 1) & mask;
        T value = array[tail];
        array[tail] = null;
        currentSize--;
        return value;
    }


    public static void main(String[] args) {
        MyArrayDeQueue<Integer> dq = new MyArrayDeQueue<>(8);

        System.out.println("=== Test 1: addLast and removeFirst ===");
        dq.addLast(1);
        dq.addLast(2);
        dq.addLast(3);
        System.out.println(dq.removeFirst()); // 1
        System.out.println(dq.removeFirst()); // 2
        System.out.println(dq.removeFirst()); // 3

        System.out.println("=== Test 2: addFirst and removeLast ===");
        dq.addFirst(10);
        dq.addFirst(20);
        dq.addFirst(30);
        System.out.println(dq.removeLast()); // 10
        System.out.println(dq.removeLast()); // 20
        System.out.println(dq.removeLast()); // 30

        System.out.println("=== Test 3: mixed addFirst/addLast and removeFirst/removeLast ===");
        dq.addLast(100);  // [100]
        dq.addFirst(200); // [200, 100]
        dq.addLast(300);  // [200, 100, 300]
        System.out.println(dq.removeFirst()); // 200
        System.out.println(dq.removeLast());  // 300
        System.out.println(dq.removeFirst()); // 100

        System.out.println("=== Test 4: wrap-around behavior ===");
        for (int i = 1; i <= 8; i++) {
            dq.addLast(i);
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(dq.removeFirst()); // 1 2 3
        }
        dq.addLast(9);
        dq.addLast(10); // this should wrap around
        while (true) {
            try {
                System.out.println(dq.removeFirst());
            } catch (IllegalStateException e) {
                break;
            }
        }

        System.out.println("=== Test 5: edge cases ===");
        try {
            dq.removeFirst(); // should throw
        } catch (IllegalStateException e) {
            System.out.println("Caught expected exception on empty removeFirst");
        }

        try {
            dq.removeLast(); // should throw
        } catch (IllegalStateException e) {
            System.out.println("Caught expected exception on empty removeLast");
        }

        System.out.println("=== Test 6: fill to capacity ===");
        MyArrayDeQueue<Integer> fullDq = new MyArrayDeQueue<>(4);
        fullDq.addLast(1);
        fullDq.addLast(2);
        fullDq.addLast(3);
        fullDq.addLast(4);
        try {
            fullDq.addLast(5); // should throw
        } catch (IllegalStateException e) {
            System.out.println("Caught expected exception on addLast when full");
        }

        System.out.println("All tests completed successfully!");
    }

}
