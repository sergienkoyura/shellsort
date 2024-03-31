package com.kpi.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ShellSortTask extends RecursiveTask<List<Integer>> {
    private final List<Integer> main;
    private final int from;
    private final int to;
    private final int count;

    public ShellSortTask(List<Integer> main, int from, int to, int count) {
        this.main = main;
        this.from = from;
        this.to = to;
        this.count = count;
    }

    @Override
    protected List<Integer> compute() {
        if(to - from > count / 100) {
            int barrier = (from + to) / 2;
            ShellSortTask startTask = new ShellSortTask(getChunk(main, from, barrier), from, barrier, count);
            ShellSortTask endTask = new ShellSortTask(getChunk(main, barrier, to), barrier, to, count);
            startTask.fork();
            endTask.fork();

            main.clear();
            main.addAll(startTask.join());
            main.addAll(endTask.join());
        } else {
            ShellSortUtil.shellSort(main);
        }
        return main;
    }
    private List<Integer> getChunk(List<Integer> main, int start, int end){
        return new ArrayList<>(main.stream().filter(el -> el >= start && el < end).toList());
    }
}
