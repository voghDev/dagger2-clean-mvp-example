package es.voghdev.prjdagger2.ui.view;

import android.content.Context;
import android.widget.Toast;

import es.voghdev.prjdagger2.R;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.usecase.ShowUserGreeting;

/**
 * Created by olmo on 31/10/16.
 */
public class ShowUserGreetingToastImpl implements ShowUserGreeting {
    Context context;

    public ShowUserGreetingToastImpl(Context context) {
        this.context = context;
    }

    @Override
    public void show(User user) {
        Toast.makeText(context, context.getString(R.string.user_greeting, user.getName()),
                Toast.LENGTH_LONG).show();
    }
}
