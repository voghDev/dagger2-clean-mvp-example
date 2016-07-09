package es.voghdev.prjdagger2.interactor.impl;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import es.voghdev.prjdagger2.interactor.Executor;
import es.voghdev.prjdagger2.interactor.Interactor;

public class ThreadExecutor implements Executor {
    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>();

    private ThreadPoolExecutor threadPoolExecutor;

    public ThreadExecutor() {
        int corePoolSize = CORE_POOL_SIZE;
        int maxPoolSize = MAX_POOL_SIZE;
        int keepAliveTime = KEEP_ALIVE_TIME;
        TimeUnit timeUnit = TIME_UNIT;
        BlockingQueue<Runnable> workQueue = WORK_QUEUE;
        threadPoolExecutor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, workQueue);
    }

    @Override
    public void run(final Interactor interactor) {
        if (interactor == null) {
            throw new IllegalArgumentException("Interactor to execute can't be null");
        }
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                interactor.run();
            }
        });
    }
}
