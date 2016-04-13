/*
 * Copyright (C) 2015 Olmo Gallegos Hernández.
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
package es.voghdev.prjdagger2.global;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import es.voghdev.prjdagger2.global.di.DaggerRootComponent;
import es.voghdev.prjdagger2.global.di.MainModule;
import es.voghdev.prjdagger2.global.di.RootComponent;

public class DaggerApplication extends Application {
    private RootComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencyInjector();
    }

    private void initializeDependencyInjector() {
        component = DaggerRootComponent.builder()
                .mainModule(new MainModule(this))
                .build();
        component.inject(this);

    }

    public RootComponent getComponent() {
        return component;
    }

    @VisibleForTesting
    public void setComponent(RootComponent component) {
        this.component = component;
    }
}
