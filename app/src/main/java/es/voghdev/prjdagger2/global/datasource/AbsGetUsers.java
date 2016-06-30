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
package es.voghdev.prjdagger2.global.datasource;

import java.util.List;

import es.voghdev.prjdagger2.global.model.User;

public abstract class AbsGetUsers {
    public void getUsers(final Listener listener){
        setListener(listener);
        getUsers();
    }

    public abstract void getUsers();

    protected Listener listener = new NullListener();

    protected void setListener(Listener listener) {
        if(listener != null)
            this.listener = listener;
    }

    public interface Listener{
        public void onUsersListReceived(List<User> users);
        public void onUsersListError(Exception e);
        public void onNoInternetAvailable();
    }

    private class NullListener implements Listener{
        public void onUsersListReceived(List<User> users) { }
        public void onUsersListError(Exception e) { }
        public void onNoInternetAvailable() { }
    }
}
