package com.kpi.parallel;

import java.util.ArrayList;
import java.util.List;

public class Timer {
    public static void main(String[] args) {

        int count = 50000;
        int threads = 6;
        int repeats = 20;
        long plainAvgTime = 0;
        long parallelAvgTime = 0;

        System.out.printf("%d elements%n", count);
        warmup(count, threads);
        for (int i = 1; i <= repeats; i++){
            List<Integer> listPlain = Generator.generate(count);
            List<Integer> listParallel = new ArrayList<>(listPlain);

            long start = System.currentTimeMillis();
            ShellSortUtil.shellSort(listPlain);
            long end = System.currentTimeMillis();

            plainAvgTime = (plainAvgTime * (i - 1) + end - start) / i;

            System.out.printf("Plain algorithm -> process took %d milliseconds%n", end - start);

            start = System.currentTimeMillis();
            ShellSortUtil.shellSortParallel(listParallel, threads);
            end = System.currentTimeMillis();

            parallelAvgTime = (parallelAvgTime * (i - 1) + end - start) / i;

            System.out.printf("Parallel algorithm -> %d threads -> process took %d milliseconds%n", threads, end - start);

            System.out.printf("Sorted: %b%n", ShellSortUtil.isSortedAscending(listParallel));
            System.out.printf("Same: %b%n", ShellSortUtil.compareLists(listPlain, listParallel));

        }

        System.out.println("AVG PLAIN: " + plainAvgTime);
        System.out.println("AVG PARALLEL: " + parallelAvgTime);

        System.out.printf("better in %.3f times", (double) plainAvgTime / parallelAvgTime);
    }

    static void warmup(int count, int threads){
        for (int i = 0; i < 20; i++){
            System.out.println("Warmup " + i);
            List<Integer> listPlain = Generator.generate(count);
            List<Integer> listParallel = new ArrayList<>(listPlain);
            ShellSortUtil.shellSort(listPlain);
            ShellSortUtil.shellSortParallel(listParallel, threads);
        }
    }
}
