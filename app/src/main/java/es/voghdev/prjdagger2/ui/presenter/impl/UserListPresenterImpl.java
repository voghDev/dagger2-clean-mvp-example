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
package es.voghdev.prjdagger2.ui.presenter.impl;

import android.content.Context;

import java.util.List;

import es.voghdev.prjdagger2.global.App;
import es.voghdev.prjdagger2.global.di.RootComponent;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.ui.presenter.UserListPresenter;
import es.voghdev.prjdagger2.usecase.GetUsers;

public class UserListPresenterImpl extends UserListPresenter {
    protected Context context;

    public UserListPresenterImpl(Context ctx){
        context = ctx;

        getComponent().inject(this);
    }

    @Override
    public void initialize() {
        view.showLoading();
        interactor.getUsers(new GetUsers.Listener() {
            @Override
            public void onUsersReceived(List<User> users, boolean isCached) {
                view.showUserList(users);
                view.hideLoading();
            }

            @Override
            public void onError(Exception e) {
                view.showUserListError(e);
                view.hideLoading();
            }

            @Override
            public void onNoInternetAvailable() {
                view.showNoInternetMessage();
                view.hideLoading();
            }

        });
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onUserPictureClicked(User user) {
        view.makeUserSayHello(user);
    }

    @Override
    public void onUserRowClicked(User user) {
        view.showUserClickedMessage(user);
    }

    protected RootComponent getComponent() {
        return ((App) context.getApplicationContext()).getComponent();
    }
}
