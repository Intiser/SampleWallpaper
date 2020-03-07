package com.ahsan.wallpaper.executor;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {

    public static void execute(Runnable runnable){
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(runnable);
    }

}
