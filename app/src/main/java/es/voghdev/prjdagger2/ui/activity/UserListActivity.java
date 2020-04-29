/*
 * Copyright (C) 2015 Olmo Gallegos Hernández.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.voghdev.prjdagger2.ui.activity;

import android.os.Bundle;
<<<<<<< HEAD
=======
import android.view.LayoutInflater;
>>>>>>> Migrate ButterKnife to ViewBinding
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
<<<<<<< HEAD
import androidx.recyclerview.widget.RecyclerView;
=======
>>>>>>> Migrate ButterKnife to ViewBinding

import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import es.voghdev.prjdagger2.R;
import es.voghdev.prjdagger2.databinding.ActivityUsersListBinding;
import es.voghdev.prjdagger2.global.App;
import es.voghdev.prjdagger2.global.di.DaggerUserListComponent;
import es.voghdev.prjdagger2.global.di.UserListComponent;
import es.voghdev.prjdagger2.global.di.UserListModule;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.interactor.GetUsersInteractor;
import es.voghdev.prjdagger2.ui.presenter.UserListPresenter;
import es.voghdev.prjdagger2.ui.renderer.UserRenderer;
import es.voghdev.prjdagger2.ui.renderer.UserRendererBuilder;
import es.voghdev.prjdagger2.usecase.ShowUserClicked;
import es.voghdev.prjdagger2.usecase.ShowUserGreeting;

public class UserListActivity extends AppCompatActivity implements UserListPresenter.View {

    RVRendererAdapter<User> adapter;

    UserListPresenter presenter;

    ActivityUsersListBinding binding;

    @Inject
    GetUsersInteractor getUsersInteractor;

    @Inject
    ShowUserClicked showUserClicked;
    @Inject
    ShowUserGreeting showUserGreeting;

    private UserListComponent component;

    final UserRenderer.OnUserClicked mUserClickListener = new UserRenderer.OnUserClicked() {
        @Override
        public void onPictureClicked(User user) {
            presenter.onUserPictureClicked(user);
        }

        @Override
        public void onBackgroundClicked(User user) {
            presenter.onUserRowClicked(user);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component().inject(this);

        binding = ActivityUsersListBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        adapter = new RVRendererAdapter(
                new UserRendererBuilder(this, mUserClickListener),
                new ListAdapteeCollection(new ArrayList<User>())
        );

        presenter = new UserListPresenter(this, getUsersInteractor);
        presenter.setView(this);
        presenter.initialize();

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        binding.usersList.setLayoutManager(new LinearLayoutManager(this));
        binding.usersList.setAdapter(adapter);
    }

    @Override
    public void showUserList(List<User> users) {
        for (User u : users) {
            adapter.add(u);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void showUserListError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoInternetMessage() {
        Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        binding.usersProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.usersProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void makeUserSayHello(User user) {
        showUserGreeting.show(user);
    }

    @Override
    public void showUserClickedMessage(User user) {
        showUserClicked.show(user);
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
