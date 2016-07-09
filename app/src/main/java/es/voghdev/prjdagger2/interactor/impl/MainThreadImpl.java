package es.voghdev.prjdagger2.interactor.impl;

import android.os.Handler;
import android.os.Looper;

import es.voghdev.prjdagger2.interactor.MainThread;

public class MainThreadImpl implements MainThread {
    private Handler handler;

    public MainThreadImpl() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}