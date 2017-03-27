package es.voghdev.prjdagger2.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import es.voghdev.prjdagger2.R;
import es.voghdev.prjdagger2.global.App;
import es.voghdev.prjdagger2.global.di.DaggerUserListComponent;
import es.voghdev.prjdagger2.global.di.UserListComponent;
import es.voghdev.prjdagger2.global.di.UserListModule;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.interactor.GetUsersInteractor;
import es.voghdev.prjdagger2.ui.presenter.UserDetailPresenter;
import es.voghdev.prjdagger2.ui.presenter.abs.AbsUserDetailPresenter;
import es.voghdev.prjdagger2.usecase.ShowUserDetail;
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

    @InjectView(R.id.user_detail_userImage)
    ImageView userImage;

    @InjectView(R.id.user_detail_pbr_loading)
    ProgressBar pbr;

    @Inject
    ShowUserGreeting showUserGreeting;

    AbsUserDetailPresenter presenter;

    @Inject
    ShowUserDetail showUserDetail;

    @Inject
    GetUsersInteractor getUserInteractor;

    @OnClick(R.id.user_detail_userImage)
    public void userClicked(View view) {
        presenter.onUserPictureClicked();
    }

    private UserListComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);


        ButterKnife.inject(this);

        Bundle bundle = getIntent().getExtras();
        final String userId = (String) bundle.get("userId");

        component().inject(this);

        presenter = new UserDetailPresenter(this, getUserInteractor, userId);
        presenter.setView(this);

        presenter.initialize();


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
    public void showUserClickedMessage(User user) {
        showUserGreeting.show(user);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showUserName(String username) {
        if (!username.isEmpty()) {
            tvUserName.setText(username);
        } else {
            tvUserName.setText("-");
        }
    }

    @Override
    public void showUserFacebookId(String userFacebookid) {
        if (!userFacebookid.isEmpty()) {
            userFacebookId.setText(userFacebookid);
        } else {
            userFacebookId.setText("-");
        }
    }

    @Override
    public void showUserAddress(String userAddres) {
        if (!userAddres.isEmpty()) {
            userAddress.setText(userAddres);
        } else {
            userAddress.setText("-");
        }
    }

    @Override
    public void showUserEmail(String useremail) {
        if (!useremail.isEmpty()) {
            userEmail.setText(useremail);
        } else {
            userEmail.setText("-");
        }
    }

    @Override
    public void showUsername(String name) {
        if (!name.isEmpty()) {
            userName.setText(name);
        } else {
            userName.setText("-");
        }
    }

    @Override
    public void showUserImage(String thumbnail) {
        Picasso.with(this).load(thumbnail)
                // .transform(new RoundedTransformation())
                .resizeDimen(R.dimen.user_thumbnail_w, R.dimen.user_thumbnail_h)
                .placeholder(R.mipmap.background1)
                .into(userImage);
    }

    @Override
    public void configureToolbar(String username) {
        getSupportActionBar().setTitle(username);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
