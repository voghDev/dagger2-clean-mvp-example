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

import java.util.List;

import javax.inject.Inject;

import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.interactor.GetUsersInteractor;

public abstract class UserListPresenter extends Presenter<UserListPresenter.View>{
    @Inject
    protected GetUsersInteractor interactor;

    public abstract void onUserPictureClicked(User user);
    public abstract void onUserRowClicked(User user);

    public interface View {
        public void showUserList(List<User> users);
        public void showUserListError(Exception e);
        public void showNoInternetMessage();

        public void showLoading();
        public void hideLoading();

        public void makeUserSayHello(User user);
        public void showUserClickedMessage(User user);
    }
}
