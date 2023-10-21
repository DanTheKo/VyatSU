package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Race {
    private ArrayList<Stage> stages;


    public ArrayList<Stage> getStages() {
        return stages;
    }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }


}