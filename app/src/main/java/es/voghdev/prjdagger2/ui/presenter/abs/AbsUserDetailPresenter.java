package es.voghdev.prjdagger2.ui.presenter.abs;


import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.ui.presenter.Presenter;

public abstract class AbsUserDetailPresenter extends Presenter<AbsUserDetailPresenter.View> {

    public abstract void onUserPictureClicked();

    public interface View {

        void showNoInternetMessage();

        void showLoading();

        void hideLoading();

        void showErrorLoading();

        void showUserData(User user);

        void showUserClickedMessage(User user);
    }
}
