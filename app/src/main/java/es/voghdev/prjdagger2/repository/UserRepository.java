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

    @Override
    public List<User> get() {
        return null;
    }

    @Override
    public void getAsync(Listener listener) {

    }
}
