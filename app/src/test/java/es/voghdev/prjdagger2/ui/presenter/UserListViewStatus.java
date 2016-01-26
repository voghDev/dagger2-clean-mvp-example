package es.voghdev.prjdagger2.ui.presenter;

import es.voghdev.prjdagger2.global.model.User;

public interface UserListViewStatus{
    public boolean isLoading();

    public boolean isShowingUserList();
    public int getUserListCount();
    public User getUserAtIndex(int index);

    public boolean isShowingError();
    public String getErrorMessage();
}