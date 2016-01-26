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

import es.voghdev.prjdagger2.global.DaggerApplication;
import es.voghdev.prjdagger2.global.datasource.GetUsers;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.ui.presenter.UserListPresenter;

public class UserListPresenterImpl extends UserListPresenter implements GetUsers.Listener {
    protected Context mContext;

    public UserListPresenterImpl(Context ctx){
        mContext = ctx;

        ((DaggerApplication)ctx.getApplicationContext())
                .getComponent().inject(this); // This line provides injected fields declared in UserListPresenter.java
    }

    @Override
    public void initialize() {
        view.showLoading();
        interactor.getUsers(this);
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
    public void onUsersListReceived(List<User> users) {
        view.showUserList(users);
        view.hideLoading();
    }

    @Override
    public void onUsersListError(Exception e) {
        view.showUserListError(e);
        view.hideLoading();
    }

    @Override
    public void onNoInternetAvailable() {
        view.showNoInternetMessage();
        view.hideLoading();
    }

    @Override
    public void onUserPictureClicked(User user) {
        view.makeUserSayHello(user);
    }

    @Override
    public void onUserRowClicked(User user) {
        view.showUserClickedMessage(user);
    }
}
