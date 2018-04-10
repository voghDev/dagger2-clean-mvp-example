/*
 * Copyright (C) 2016 Olmo Gallegos Hernández
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
package es.voghdev.prjdagger2;

import java.util.List;

import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.interactor.GetUsersInteractor;
import es.voghdev.prjdagger2.ui.presenter.UserListPresenter;
import es.voghdev.prjdagger2.usecase.GetUsers;

public class PresenterCaller implements GetUsers.Listener {
    UserListPresenter.View view;
    GetUsersInteractor collaborator;

    public PresenterCaller(GetUsersInteractor collaborator) {
        this.collaborator = collaborator;
    }

    public void getUsers() {
        collaborator.getAsync(this);
    }

    public UserListPresenter.View getView() {
        return view;
    }

    @Override
    public void onUsersReceived(List<User> users, boolean isCached) {

    }

    @Override
    public void onError(Exception e) {
        /* Empty */
    }

    @Override
    public void onNoInternetAvailable() {
        /* Empty */
    }
}
