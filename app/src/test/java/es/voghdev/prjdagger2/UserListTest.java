package es.voghdev.prjdagger2;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import android.content.Context;
import android.os.Looper;
import android.test.mock.MockContext;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import es.voghdev.prjdagger2.ui.presenter.UserListPresenter;
import es.voghdev.prjdagger2.ui.presenter.impl.UserListPresenterImpl;
import es.voghdev.prjdagger2.ui.presenter.UserListViewMockImpl;
import es.voghdev.prjdagger2.ui.presenter.UserListViewStatus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserListTest {

    @Test
    public void shouldBeLoadingOnStart() throws Exception {
        Context context = mock(Context.class);

        UserListPresenter.View mockView = new UserListViewMockImpl();
        UserListPresenter userListPresenter = new UserListPresenterImpl(context);
        userListPresenter.setView(mockView);
        UserListViewStatus status = (UserListViewStatus) mockView;
    }

    @Test
    public void shouldCreateNonNullMockedContext() throws Exception {
        Context context = mock(Context.class);

        assertNotNull(context);
    }
}
