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
package es.voghdev.prjdagger2.global.di;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import es.voghdev.prjdagger2.datasource.api.GetUsersApiImpl;
import es.voghdev.prjdagger2.datasource.datafile.GetUsersFileImpl;
import es.voghdev.prjdagger2.global.App;
import es.voghdev.prjdagger2.interactor.GetUsersInteractor;
import es.voghdev.prjdagger2.interactor.impl.MainThreadImpl;
import es.voghdev.prjdagger2.interactor.impl.ThreadExecutor;
import es.voghdev.prjdagger2.repository.UserRepository;

@Module
public class MainModule {
    private App application;

    GetUsersInteractor interactor;
    UserRepository userRepository;

    public MainModule(App application) {
        this.application = application;

        interactor = new GetUsersInteractor(new GetUsersApiImpl(10, 0),
                new ThreadExecutor(),
                new MainThreadImpl());

        userRepository = new UserRepository(application, interactor, new GetUsersFileImpl(application,
                new ThreadExecutor(),
                new MainThreadImpl()));
    }

    @Provides
    GetUsersInteractor provideUserInteractor() {
        return interactor;
    }

    @Provides
    UserRepository provideUserRepository() {
        return userRepository;
    }

    @Provides
    @Named("applicationContext")
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

}