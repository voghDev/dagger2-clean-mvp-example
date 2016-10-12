package es.voghdev.prjdagger2;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
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

/**
 * Created by olmo on 12/10/16.
 */

public class UserListPresenterTest {

    GetUsersInteractor mockInteractor;
    App mockApp;
    RootComponent mockComponent;
    MainModule mockModule;
    Context mockContext;
    UserListPresenter.View mockView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(UserListPresenterTest.class);
        mockModule = mock(MainModule.class);
        mockApp = mock(App.class);
        mockContext = mock(Context.class);
        mockInteractor = mock(GetUsersInteractor.class);
        mockView = mock(UserListPresenter.View.class);
        mockComponent = mock(RootComponent.class);
    }

    @Test
    public void shouldCreateANonNullMockedPresenter() throws Exception {
        givenAMockedEnvironment();

        UserListPresenter presenter = new UserListPresenter(mockContext, mockInteractor);
        assertNotNull(presenter);
    }

    @Test
    public void shouldShowLoadingOnStart() throws Exception {
        UserListPresenter presenter = givenAMockedPresenter();

        presenter.setView(mockView);
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
