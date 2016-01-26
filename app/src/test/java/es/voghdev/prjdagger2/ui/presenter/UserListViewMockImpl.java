package es.voghdev.prjdagger2.ui.presenter;

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
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.ui.presenter.UserListPresenter;
import es.voghdev.prjdagger2.ui.presenter.UserListViewStatus;
import java.util.List;

public class UserListViewMockImpl implements UserListPresenter.View, UserListViewStatus {
    boolean loading = false;
    String errorMessage = "";
    String userMessage = "";
    List<User> users;

    @Override
    public void showUserList(List<User> users) {
        this.users = users;
    }

    @Override
    public void showUserListError(Exception e){
        errorMessage = e.getMessage();
    }

    @Override
    public void showNoInternetMessage(){
        errorMessage = "No internet available";
    }

    @Override
    public void showLoading() {
        loading = true;
    }

    @Override
    public void hideLoading() {
        loading = false;
    }

    @Override
    public void makeUserSayHello(User user){
        userMessage = String.format("Hello! My name is %s", user.getName());
    }

    @Override
    public void showUserClickedMessage(User user){
        userMessage = String.format("User %s was clicked", user.getName());
    }

    @Override
    public boolean isLoading(){
        return loading;
    }

    @Override
    public boolean isShowingUserList(){
        return users != null && users.size() > 0;
    }

    @Override
    public int getUserListCount(){
        return users.size();
    }

    @Override
    public User getUserAtIndex(int index){
        return users.get(index);
    }

    @Override
    public boolean isShowingError(){
        return !errorMessage.isEmpty();
    }

    @Override
    public String getErrorMessage(){
        return errorMessage;
    }
}
