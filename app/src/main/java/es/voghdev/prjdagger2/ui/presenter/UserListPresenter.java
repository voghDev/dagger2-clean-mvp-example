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
package es.voghdev.prjdagger2.ui.presenter;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import es.voghdev.prjdagger2.global.App;
import es.voghdev.prjdagger2.global.di.RootComponent;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.interactor.GetUsersInteractor;
import es.voghdev.prjdagger2.usecase.GetUsers;

public class UserListPresenter extends Presenter<UserListPresenter.View> {

    protected GetUsers interactor;

    protected Context context;

    @Inject
    public UserListPresenter(Context ctx, GetUsersInteractor getUsersInteractor) {
        context = ctx;
        interactor = getUsersInteractor;

        getComponent().inject(this);
    }

    @Override
    public void initialize() {
        view.showLoading();
        interactor.getAsync(new GetUsers.Listener() {
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

    public void onUserPictureClicked(User user) {
        view.makeUserSayHello(user);
    }

    public void onUserRowClicked(User user) {
        view.showUserClickedMessage(user);
    }

    protected RootComponent getComponent() {
        return ((App) context.getApplicationContext()).getComponent();
    }

    public interface View {
        void showUserList(List<User> users);

        void showUserListError(Exception e);

        void showNoInternetMessage();

        void showLoading();

        void hideLoading();

        void makeUserSayHello(User user);

        void showUserClickedMessage(User user);
    }
}
