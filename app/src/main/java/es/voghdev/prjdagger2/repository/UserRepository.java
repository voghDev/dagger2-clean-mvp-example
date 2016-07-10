package es.voghdev.prjdagger2.repository;

import java.util.ArrayList;
import java.util.List;

import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.usecase.GetUsers;

public class UserRepository implements GetUsers {
    CachePolicy cachePolicy;
    GetUsers apiDataSource;
    GetUsers fileDataSource;
    List<User> users = new ArrayList<>();

    public UserRepository(CachePolicy cachePolicy, GetUsers apiDataSource, GetUsers fileDataSource) {
        this.cachePolicy = cachePolicy;
        this.apiDataSource = apiDataSource;
        this.fileDataSource = fileDataSource;
    }

    public void setCachePolicy(CachePolicy cachePolicy) {
        this.cachePolicy = cachePolicy;
    }

    @Override
    public List<User> get() {
        invalidateCacheIfNecessary(cachePolicy, users);

        if(users != null && users.size() > 0)
            return users;

        List<User> apiUsers = apiDataSource.get();
        cachePolicy = new TimedCachePolicy(TimedCachePolicy.ONE_MINUTE);
        users = apiUsers;
        return users;
    }

    private void invalidateCacheIfNecessary(CachePolicy cachePolicy, List<User> users) {
        if(!cachePolicy.isCacheValid())
            users.clear();
    }

    @Override
    public void getAsync(Listener listener) {

    }
}
