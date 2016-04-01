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

public abstract class GetEntity<T> {
    public void get(final Listener listener){
        setListener(listener);
        get();
    }

    protected abstract void get();

    protected Listener listener = new NullListener();

    protected void setListener(Listener listener) {
        if(listener != null)
            this.listener = listener;
    }

    public interface Listener<T>{
        public void onResultsReceived(List<T> list);
        public void onError(Exception e);
        public void onNoInternetAvailable();
    }

    public static class NullListener implements Listener{
        public void onResultsReceived(List list) {}
        public void onError(Exception e) {}
        public void onNoInternetAvailable() {}
    }
}
