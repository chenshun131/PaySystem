package com.chenshun.test.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * User: mew <p />
 * Time: 18/1/15 08:53  <p />
 * Version: V1.0  <p />
 * Description: 计算包含 first，但不包含 last <p />
 */
public class Task extends RecursiveAction {

    private List<Product> products;

    private int first;

    private int last;

    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if (last - first < 10) {
            updatePrices();
        } else {
            int middle = (first + last) / 2;
            Task t1 = new Task(products, first, middle, increment);
            Task t2 = new Task(products, middle, last, increment);
            invokeAll(t1, t2);
        }
    }

    private void updatePrices() {
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setName("Product " + i);
            product.setPrice(product.getPrice() * (1 + increment));
        }
    }

    public static void main(String[] args) {
        List<Product> products = generate(10000);
        Task task = new Task(products, 0, products.size(), 0.2);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(task);
        try {
            do {
                System.out.printf("Main: Thread Count: %d\n", forkJoinPool.getActiveThreadCount());
                System.out.printf("Main: Thread Steal: %d\n", forkJoinPool.getStealCount());
                System.out.printf("Main: Thread Parallelism: %d\n", forkJoinPool.getParallelism());
                TimeUnit.MILLISECONDS.sleep(5);
            } while (!task.isDone());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        forkJoinPool.shutdown();
        if (task.isCompletedNormally()) {
            System.out.printf("Main: The process has completed normally.");
        }
        for (Product product : products) {
            if (product.getPrice() != 12) {
                System.out.printf("Produt %s: %f", product.getName(), product.getPrice());
            }
        }
        System.out.printf("Main: End of the program.");
    }

    private static List<Product> generate(int size) {
        List<Product> ret = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Product product = new Product("Product " + i, 10);
            ret.add(product);
        }
        return ret;
    }

}
