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

import dagger.Module;
import dagger.Provides;
import es.voghdev.prjdagger2.ui.view.ShowUserClickedToastImpl;
import es.voghdev.prjdagger2.ui.view.ShowUserDetailImpl;
import es.voghdev.prjdagger2.ui.view.ShowUserGreetingToastImpl;
import es.voghdev.prjdagger2.usecase.ShowUserClicked;
import es.voghdev.prjdagger2.usecase.ShowUserDetail;
import es.voghdev.prjdagger2.usecase.ShowUserGreeting;

@Module
public class UserListModule {

    Context mContext;
    ShowUserClicked showUserClickedToast;
    ShowUserGreeting showUserGreetingToast;
    ShowUserDetail showUserDetail;

    public UserListModule(final Context context) {
        mContext = context;

        showUserClickedToast = new ShowUserClickedToastImpl(mContext);
        showUserGreetingToast = new ShowUserGreetingToastImpl(mContext);
        showUserDetail = new ShowUserDetailImpl(context);
    }

    @Provides
    public ShowUserClicked provideShowUserClicked() {
        return showUserClickedToast;
    }

    @Provides
    public ShowUserGreeting provideShowUserGreeting() {
        return showUserGreetingToast;
    }

    @Provides
    public ShowUserDetail provideShowUserDetail() {
        return showUserDetail;
    }

}
