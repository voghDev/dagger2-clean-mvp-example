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
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.ui.presenter.UserListPresenter;
import es.voghdev.prjdagger2.ui.presenter.impl.UserListPresenterImpl;
import es.voghdev.prjdagger2.ui.renderer.UserRenderer;
import es.voghdev.prjdagger2.ui.renderer.UserRendererBuilder;

@Module
public class UserListModule {

    RVRendererAdapter<User> adapter;
    private final Context mContext;

    public UserListModule(final Context context, final UserRenderer.OnUserClicked listener) {
        mContext = context;
        adapter = new RVRendererAdapter<User>(
                LayoutInflater.from(context),
                new UserRendererBuilder(context, listener),
                new ListAdapteeCollection<User>(new ArrayList<User>())
        );
    }

    @Provides
    UserListPresenter provideUserListPresenter(){
        return new UserListPresenterImpl(mContext);
    }

    @Provides
    RVRendererAdapter<User> provideUserAdapter(){
        return adapter;
    }
}
