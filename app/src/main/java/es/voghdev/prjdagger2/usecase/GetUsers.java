package es.voghdev.prjdagger2.usecase;

import java.util.List;

import es.voghdev.prjdagger2.global.model.User;

public interface GetUsers {
    public List<User> get();
    public void getAsync(final Listener listener);

    public interface Listener {
        public void onUsersReceived(final List<User> users, boolean isCached);
        public void onError(Exception e);
        public void onNoInternetAvailable();
    }
}
