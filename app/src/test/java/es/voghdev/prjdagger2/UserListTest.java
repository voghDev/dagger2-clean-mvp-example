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
package es.voghdev.prjdagger2;

import android.content.Context;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.voghdev.prjdagger2.global.DaggerApplication;
import es.voghdev.prjdagger2.global.di.MainModule;
import es.voghdev.prjdagger2.global.di.RootComponent;
import es.voghdev.prjdagger2.ui.presenter.UserListPresenter;
import es.voghdev.prjdagger2.ui.presenter.UserListViewMockImpl;
import es.voghdev.prjdagger2.ui.presenter.impl.UserListPresenterImpl;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserListTest {

    @Mock Context fakeContext;

    @Test
    public void shouldNotCreateNullMockedContext() throws Exception {
        Context context = mock(Context.class);
        assertNotNull(context);
    }

    @Test
    public void shouldBeLoadingOnStart() throws Exception {
        Context context = mock(Context.class);

        UserListPresenter.View mockView = mock(UserListViewMockImpl.class);
        // This line throws NPE because of the dagger2 component thing
        UserListPresenter mockPresenter = new UserListPresenterImpl(context);

        mockPresenter.setView(mockView);
        mockPresenter.initialize();

        verify(mockView, times(1)).showLoading();
    }

    @Test
    public void shouldCreateNonNullMockedView() throws Exception {
        UserListPresenter.View mockView = mock(UserListPresenter.View.class);

        assertNotNull(mockView);
    }
}
