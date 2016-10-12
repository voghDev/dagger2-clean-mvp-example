package es.voghdev.prjdagger2;

import java.util.List;

import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.ui.presenter.UserListPresenter;

/**
 * Created by olmo on 12/10/16.
 */

public class UserListMockView implements UserListPresenter.View {
    public int loadingCount;

    @Override
    public void showUserList(List<User> users) {

    }

    @Override
    public void showUserListError(Exception e) {

    }

    @Override
    public void showNoInternetMessage() {

    }

    @Override
    public void showLoading() {
        ++loadingCount;
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void makeUserSayHello(User user) {

    }

    @Override
    public void showUserClickedMessage(User user) {

    }
}
