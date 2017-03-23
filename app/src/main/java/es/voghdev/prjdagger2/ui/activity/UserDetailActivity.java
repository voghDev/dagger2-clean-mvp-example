package es.voghdev.prjdagger2.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import es.voghdev.prjdagger2.R;
import es.voghdev.prjdagger2.global.App;
import es.voghdev.prjdagger2.global.di.DaggerUserListComponent;
import es.voghdev.prjdagger2.global.di.UserListComponent;
import es.voghdev.prjdagger2.global.di.UserListModule;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.interactor.GetUsersInteractor;
import es.voghdev.prjdagger2.ui.picasso.RoundedTransformation;
import es.voghdev.prjdagger2.ui.presenter.UserDetailPresenter;
import es.voghdev.prjdagger2.ui.presenter.abs.AbsUserDetailPresenter;
import es.voghdev.prjdagger2.usecase.ShowUserGreeting;

public class UserDetailActivity extends AppCompatActivity implements AbsUserDetailPresenter.View {
    @InjectView(R.id.user_detail_tv_data_username)
    TextView tvUserName;

    @InjectView(R.id.user_detail_tv_data_address)
    TextView userAddress;

    @InjectView(R.id.user_detail_tv_data_name)
    TextView userName;

    @InjectView(R.id.user_detail_tv_data_facebookId)
    TextView userFacebookId;

    @InjectView(R.id.user_detail_tv_data_email)
    TextView userEmail;

    @InjectView(R.id.user_detail_tv_data_thumbnail)
    TextView userThumbnail;

    @InjectView(R.id.user_detail_userImage)
    ImageView userImage;

    @InjectView(R.id.user_detail_pbr_loading)
    ProgressBar pbr;

    @Inject
    ShowUserGreeting showUserGreeting;

    @Inject
    AbsUserDetailPresenter presenter;

    @Inject
    GetUsersInteractor getUserInteractor;

    private UserListComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        ButterKnife.inject(this);

        Bundle bundle = getIntent().getExtras();
        final String userId = (String) bundle.get("userId");

        //component.inject(this);

        presenter = new UserDetailPresenter(this, getUserInteractor, userId);
        presenter.setView(this);
        presenter.initialize();

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

    @Override
    public void showNoInternetMessage() {
        Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        pbr.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbr.setVisibility(View.GONE);
    }

    @Override
    public void showErrorLoading() {
        Toast.makeText(this, getResources().getString(R.string.no_user_data), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserData(User user) {
        userInfo(user);
    }

    @Override
    public void showUserClickedMessage() {

    }

    private UserListComponent component() {
        if (component == null) {
            component = DaggerUserListComponent.builder()
                    .rootComponent(((App) getApplication()).getComponent())
                    .userListModule(new UserListModule(getApplicationContext()))
                    .mainModule(((App) getApplication()).getMainModule())
                    .build();
        }
        return component;
    }

}
