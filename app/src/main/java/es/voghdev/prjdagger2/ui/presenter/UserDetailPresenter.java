package es.voghdev.prjdagger2.ui.presenter;


import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import es.voghdev.prjdagger2.global.App;
import es.voghdev.prjdagger2.global.di.RootComponent;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.interactor.GetUsersInteractor;
import es.voghdev.prjdagger2.ui.presenter.abs.AbsUserDetailPresenter;
import es.voghdev.prjdagger2.usecase.GetUsers;

public class UserDetailPresenter extends AbsUserDetailPresenter {
    protected Context context;
    protected GetUsers interactor;
    protected String userId;
    protected User user;

    @Inject
    public UserDetailPresenter(Context context, GetUsersInteractor getUsersInteractor, String userId) {
        this.context = context;
        this.interactor = getUsersInteractor;
        this.userId = userId;

        getComponent().inject(this);

    }

    @Override
    public void onUserPictureClicked() {
        view.showUserClickedMessage(this.user);
    }

    @Override
    public void initialize() {
        view.showLoading();
        interactor.getAsync(new GetUsers.Listener() {
            @Override
            public void onUsersReceived(List<User> users, boolean isCached) {
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getId().equals((userId))) {
                        user = users.get(i);
                        view.showUserAddress(user.getAddress());
                        view.showUsername(user.getName());
                        view.showUserEmail(user.getEmail());
                        view.showUserFacebookId(user.getFacebookId());
                        view.showUserName(user.getUsername());
                        view.showUserImage(user.getThumbnail());
                        view.configureToolbar(user.getName());
                    }
                }
                view.hideLoading();
            }

            @Override
            public void onError(Exception e) {
                view.showErrorLoading();
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
