/*
 * Copyright (C) 2016 Olmo Gallegos Hernández
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
package es.voghdev.prjdagger2;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.voghdev.prjdagger2.global.App;
import es.voghdev.prjdagger2.global.di.MainModule;
import es.voghdev.prjdagger2.global.di.RootComponent;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.interactor.GetUsersInteractor;
import es.voghdev.prjdagger2.ui.presenter.UserListPresenter;
import es.voghdev.prjdagger2.usecase.GetUsers;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserListPresenterTest {

    @Mock
    GetUsersInteractor mockInteractor;
    @Mock
    App mockApp;
    @Mock
    RootComponent mockComponent;
    @Mock
    MainModule mockModule;
    @Mock
    Context mockContext;
    @Mock
    UserListPresenter.View mockView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateANonNullMockedPresenter() throws Exception {
        UserListPresenter presenter = givenAMockedPresenter();
        assertNotNull(presenter);
    }

    @Test
    public void shouldShowLoadingOnStart() throws Exception {
        UserListPresenter presenter = givenAMockedPresenter();

        presenter.initialize();

        verify(mockView, times(1)).showLoading();
    }

    @Test
    public void shouldCallGetUsersOnStart() throws Exception {
        UserListPresenter presenter = givenAMockedPresenter();

        presenter.initialize();

        verify(mockInteractor, times(1)).getUsers(any(GetUsers.Listener.class));
    }

    @Test
    public void shouldSayHelloWhenAUserIsClicked() throws Exception {
        UserListPresenter presenter = givenAMockedPresenter();
        User mockUser = givenAMockedUser();

        presenter.onUserPictureClicked(mockUser);

        verify(mockView, times(1)).makeUserSayHello(mockUser);
    }

    private void givenAMockedEnvironment() {
        when(mockContext.getApplicationContext()).thenReturn(mockApp);
        when(mockApp.getComponent()).thenReturn(mockComponent);
        when(mockApp.getMainModule()).thenReturn(mockModule);
    }

    private UserListPresenter givenAMockedPresenter() {
        givenAMockedEnvironment();
        UserListPresenter presenter = new UserListPresenter(mockContext, mockInteractor);
        presenter.setView(mockView);
        return presenter;
    }

    private User givenAMockedUser() {
        return mock(User.class);
    }
}
