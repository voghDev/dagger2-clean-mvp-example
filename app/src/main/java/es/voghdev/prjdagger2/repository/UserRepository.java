package es.voghdev.prjdagger2.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.repository.cachepolicy.CachePolicy;
import es.voghdev.prjdagger2.repository.cachepolicy.NoCachePolicy;
import es.voghdev.prjdagger2.repository.cachepolicy.TimedCachePolicy;
import es.voghdev.prjdagger2.usecase.GetUserById;
import es.voghdev.prjdagger2.usecase.GetUsers;

public class UserRepository implements GetUsers, GetUserById {
    Context context;
    CachePolicy cachePolicy;
    GetUsers apiDataSource;
    GetUsers fileDataSource;
    List<User> users = new ArrayList<>();

    public UserRepository(Context context, GetUsers apiDataSource, GetUsers fileDataSource) {
        this.context = context;
        this.apiDataSource = apiDataSource;
        this.fileDataSource = fileDataSource;
        this.cachePolicy = new NoCachePolicy();
    }

    public void setCachePolicy(CachePolicy cachePolicy) {
        this.cachePolicy = cachePolicy;
    }

    @Override
    public List<User> get() {
        invalidateCacheIfNecessary(cachePolicy, users);

        if (users != null && users.size() > 0) {
            return users;
        }

        List<User> apiUsers = apiDataSource.get();
        cachePolicy = new TimedCachePolicy(TimedCachePolicy.ONE_MINUTE);
        users = apiUsers;
        return users;
    }

    private void invalidateCacheIfNecessary(CachePolicy cachePolicy, List<User> users) {
        if (!cachePolicy.isCacheValid()) {
            users.clear();
        }
    }

    @Override
    public void getAsync(final GetUsers.Listener listener) {
        invalidateCacheIfNecessary(cachePolicy, users);

        if (users != null && users.size() > 0) {
            listener.onUsersReceived(users, true);
        } else {
            apiDataSource.getAsync(new GetUsers.Listener() {
                @Override
                public void onUsersReceived(List<User> list, boolean isCached) {
                    users = list;
                    listener.onUsersReceived(list, isCached);
                }

                @Override
                public void onError(Exception e) {
                    listener.onError(e);
                }

                @Override
                public void onNoInternetAvailable() {
                    listener.onNoInternetAvailable();
                }
            });
        }
    }

    @Override
    public void getUserById(String id, GetUserById.Listener listener) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                listener.onSuccess(user, true);
            }
        }
    }
}
