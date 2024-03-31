package com.kpi.parallel;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ShellSortUtil {

    public static void shellSortParallel(List<Integer> list, int threads){
        ForkJoinPool pool = new ForkJoinPool(threads);
        pool.invoke(new ShellSortTask(list, 0, list.size(), list.size()));
    }

    public static void shellSort(List<Integer> list) {
        for (int gap = list.size(); gap > 0; gap /= 2)
            for (int i = gap; i < list.size(); i++)
                for (int j = i - gap; j >= 0 && list.get(j) > list.get(j + gap); j -= gap)
                    swapList(list, j, j + gap);

    }

    public static void swapList(List<Integer> list, int p1, int p2) {
        int temp = list.get(p1);
        list.set(p1, list.get(p2));
        list.set(p2, temp);
    }

    public static boolean isSortedAscending(List<Integer> array) {
        int count = 0;
        for (int i = 0; i < array.size() - 1; i++) {
            if (array.get(i) > array.get(i + 1)) {
                count++;
            }
        }
        return !(count > 0);
    }

    public static boolean compareLists(List<Integer> list1, List<Integer> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }

        return true;
    }
}
