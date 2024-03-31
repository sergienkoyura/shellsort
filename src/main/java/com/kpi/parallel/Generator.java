package com.kpi.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    public static List<Integer> generate(int count){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++){
            list.add(new Random().nextInt(count));
        }
        return list;
    }
}
