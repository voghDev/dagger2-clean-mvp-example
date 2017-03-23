package es.voghdev.prjdagger2.ui.view;

import android.content.Context;
import android.content.Intent;

import es.voghdev.prjdagger2.ui.activity.UserDetailActivity;
import es.voghdev.prjdagger2.usecase.ShowUserDetail;

public class ShowUserDetailImpl implements ShowUserDetail {
    private Context context;

    public ShowUserDetailImpl(Context context) {
        this.context = context;
    }

    @Override
    public void show(String id) {
        Intent i = new Intent(this.context, UserDetailActivity.class);
        i.putExtra("userId", id);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.context.startActivity(i);
    }
}
