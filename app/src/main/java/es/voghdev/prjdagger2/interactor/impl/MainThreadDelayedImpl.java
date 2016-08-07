/*
 * Copyright (C) 2016 Olmo Gallegos Hernández.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.voghdev.prjdagger2.interactor.impl;

import android.os.Handler;
import android.os.Looper;

import es.voghdev.prjdagger2.interactor.MainThread;

public class MainThreadDelayedImpl implements MainThread {
    private Handler handler;
    long delay;

    public MainThreadDelayedImpl(long delayMs) {
        this.handler = new Handler(Looper.getMainLooper());
        delay = delayMs;
    }

    @Override
    public void post(Runnable runnable) {
        handler.postDelayed(runnable, delay);
    }
}