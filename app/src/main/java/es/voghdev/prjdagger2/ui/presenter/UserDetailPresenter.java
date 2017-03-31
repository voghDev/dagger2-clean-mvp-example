package es.voghdev.prjdagger2.ui.presenter;


import android.content.Context;

import javax.inject.Inject;

import es.voghdev.prjdagger2.global.App;
import es.voghdev.prjdagger2.global.di.RootComponent;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.interactor.GetUsersInteractor;
import es.voghdev.prjdagger2.repository.UserRepository;
import es.voghdev.prjdagger2.ui.presenter.abs.AbsUserDetailPresenter;
import es.voghdev.prjdagger2.usecase.GetUserById;
import es.voghdev.prjdagger2.usecase.GetUsers;

public class UserDetailPresenter extends AbsUserDetailPresenter {
    protected Context context;
    protected GetUsers interactor;
    protected GetUserById getUserById;
    protected String userId;
    protected User user;

    @Inject
    public UserDetailPresenter(Context context,
                               GetUsersInteractor getUsersInteractor,
                               UserRepository userRepository,
                               String id) {
        this.context = context;
        this.interactor = getUsersInteractor;
        this.getUserById = userRepository;
        this.userId = id;

        getComponent().inject(this);
    }

    @Override
    public void onUserPictureClicked() {
        view.showUserClickedMessage(this.user);
    }

    @Override
    public void initialize() {
        view.showLoading();
        getUserById.getUserById(userId, new GetUserById.Listener() {
            @Override
            public void onSuccess(User u, boolean isCached) {
                user = u;
                view.showUserAddress(user.getAddress());
                view.showUsername(user.getName());
                view.showUserEmail(user.getEmail());
                view.showUserFacebookId(user.getFacebookId());
                view.showUserName(user.getUsername());
                view.showUserImage(user.getThumbnail());
                view.configureToolbar(user.getName());

                view.hideLoading();
            }

            @Override
            public void onError(Exception e) {
                view.showUserError();
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

    protected RootComponent getComponent() {
        return ((App) context.getApplicationContext()).getComponent();
    }
}
