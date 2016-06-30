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
import android.view.LayoutInflater;

import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RendererAdapter;

import java.util.ArrayList;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import es.voghdev.prjdagger2.global.App;
import es.voghdev.prjdagger2.global.DaggerApplication;
import es.voghdev.prjdagger2.global.datasource.datafile.GetUsersFileImpl;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.interactor.UserInteractor;
import es.voghdev.prjdagger2.ui.presenter.UserListPresenter;
import es.voghdev.prjdagger2.ui.presenter.impl.UserListPresenterImpl;
import es.voghdev.prjdagger2.ui.renderer.UserRendererBuilder;

@Module
public class MainModule {
    private App application;

    UserInteractor interactor;

    public MainModule(App application) {
        this.application = application;

        interactor = new UserInteractor(new GetUsersFileImpl(application)); // new GetUsersMockImpl();
    }

    @Provides
    UserInteractor provideUserInteractor(){
        return interactor;
    }

    @Provides
    @Named("applicationContext")
    Context provideApplicationContext(){
        return application.getApplicationContext();
    }

}