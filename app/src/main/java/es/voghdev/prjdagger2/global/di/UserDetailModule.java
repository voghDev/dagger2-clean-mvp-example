package es.voghdev.prjdagger2.global.di;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import es.voghdev.prjdagger2.ui.view.ShowUserDetailImpl;
import es.voghdev.prjdagger2.usecase.ShowUserDetail;

@Module
public class UserDetailModule {
    protected Context context;
    protected ShowUserDetailImpl showUserDetailImpl;

    public UserDetailModule(Context context) {
        this.context = context;

        showUserDetailImpl = new ShowUserDetailImpl(context);

    }

    @Provides
    public ShowUserDetail provideShowUserDetail() {
        return showUserDetailImpl;
    }
}
