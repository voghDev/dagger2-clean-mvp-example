package es.voghdev.prjdagger2.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import es.voghdev.prjdagger2.R;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.ui.picasso.RoundedTransformation;
import es.voghdev.prjdagger2.usecase.ShowUserGreeting;

public class UserDetailActivity extends AppCompatActivity {
    @InjectView(R.id.userName)
    TextView tvUserName;

    @InjectView(R.id.userAddress)
    TextView userAddress;

    @InjectView(R.id.name)
    TextView userName;

    @InjectView(R.id.userFacebookId)
    TextView userFacebookId;

    @InjectView(R.id.userEmail)
    TextView userEmail;

    @InjectView(R.id.userThumbnail)
    TextView userThumbnail;

    @InjectView(R.id.userImage)
    ImageView userImage;

    @Inject
    ShowUserGreeting showUserGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        ButterKnife.inject(this);

        Bundle bundle = getIntent().getExtras();
        final User user = (User) bundle.get("usuario");

        userInfo(user);

    }
    private void userInfo(User user) {
        if (user != null) {
            if (!user.getName().isEmpty()) {
                tvUserName.setText(user.getName());
            } else {
                tvUserName.setText("-");
            }
            if (!user.getUsername().isEmpty()) {
                userName.setText(user.getUsername());
            } else {
                userName.setText("-");
            }
            if (!user.getAddress().isEmpty()) {
                userAddress.setText(user.getAddress());
            } else {
                userAddress.setText("-");
            }
            if (!user.getFacebookId().isEmpty()) {
                userFacebookId.setText(user.getFacebookId());
            } else {
                userFacebookId.setText("-");
            }
            if (!user.getEmail().isEmpty()) {
                userEmail.setText(user.getEmail());
            } else {
                userEmail.setText("-");
            }
            if (!user.getThumbnail().isEmpty()) {
                userThumbnail.setText(user.getThumbnail());
            } else {
                userThumbnail.setText("-");
            }
            Picasso.with(this).load(user.getThumbnail())
                    .transform(new RoundedTransformation())
                    .resizeDimen(R.dimen.user_thumbnail_w, R.dimen.user_thumbnail_h)
                    .placeholder(R.mipmap.background1)
                    .into(userImage);
        }
    }
}
