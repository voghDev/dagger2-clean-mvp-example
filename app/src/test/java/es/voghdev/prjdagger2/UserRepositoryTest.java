/*
 * Copyright (C) 2016 Olmo Gallegos Hernández.
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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.repository.UserRepository;
import es.voghdev.prjdagger2.usecase.GetUsers;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class UserRepositoryTest extends BaseUnitTest {

    @Mock
    GetUsers mockGetUsersFile;
    @Mock
    GetUsers mockGetUsersApi;
    @Mock
    Context mockContext;
    @Mock
    UserListCollaborator userListCollaborator;

    RepositoryCaller repositoryCaller;

    @Captor
    ArgumentCaptor<GetUsers.Listener> argumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldUseApiDataSourceForGettingUsers() throws Exception {
        UserRepository userRepository = givenAMockedRepository();

        userRepository.getAsync(new EmptyListener());

        verify(mockGetUsersApi, times(1)).getAsync(any(GetUsers.Listener.class));
    }

    @Test
    public void shouldReturnTwoUsersWhenUsingApiDataSource() throws Exception {
        givenAMockedRepository();

        repositoryCaller = new RepositoryCaller(mockGetUsersApi);
        repositoryCaller.getUsers();

        verify(mockGetUsersApi, times(1)).getAsync(argumentCaptor.capture());
        verify(mockGetUsersFile, times(0)).getAsync(any(GetUsers.Listener.class));

        assertEquals(2, repositoryCaller.getResult().size());
    }

    @Test
    public void shouldReturnOneUsersWhenUsingFileDataSource() throws Exception {
        givenAMockedRepository();

        repositoryCaller = new RepositoryCaller(mockGetUsersFile);
        repositoryCaller.getUsers();

        verify(mockGetUsersFile, times(1)).getAsync(argumentCaptor.capture());
        verify(mockGetUsersApi, times(0)).getAsync(any(GetUsers.Listener.class));

        assertEquals(1, repositoryCaller.getResult().size());
    }

    @Test
    public void shouldNotUseFileDataSourceForGettingUsers() throws Exception {
        UserRepository userRepository = givenAMockedRepository();

        userRepository.getAsync(new EmptyListener());

        verify(mockGetUsersFile, times(0)).getAsync(any(GetUsers.Listener.class));
    }

    private UserRepository givenAMockedRepository() {
        final List<User> apiUsers = givenThereAreSomeUsers();
        final List<User> fileUsers = givenThereIsOneUser();

        UserRepository userRepository = new UserRepository(mockContext, mockGetUsersApi, mockGetUsersFile);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((GetUsers.Listener) invocation.getArguments()[0]).onUsersReceived(apiUsers, true);
                return null;
            }
        }).when(mockGetUsersApi).getAsync(any(GetUsers.Listener.class));

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((GetUsers.Listener) invocation.getArguments()[0]).onUsersReceived(fileUsers, true);
                return null;
            }
        }).when(mockGetUsersFile).getAsync(any(GetUsers.Listener.class));

        return userRepository;
    }

    protected List<User> givenThereAreSomeUsers() {
        List<User> list = new ArrayList<User>();
        list.add(createMockUser("005-271", "Philip Borke", "St. Etienne 57",
                "ph@some-company.com", SAMPLE_AVATAR, "12345678"));
        list.add(createMockUser("005-272", "Andre Lozano", "St. Etienne 58",
                "alzn@some-company.com", SAMPLE_AVATAR, "12345679"));
        return list;
    }

    protected List<User> givenThereIsOneUser() {
        List<User> list = new ArrayList<User>();
        list.add(createMockUser("005-273", "Anna Ivanova", "St. Etienne 61",
                "anna@some-company.com", SAMPLE_AVATAR, "12345677"));
        return list;
    }

    private class EmptyListener implements GetUsers.Listener {
        @Override
        public void onUsersReceived(List<User> users, boolean isCached) {

        }

        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onNoInternetAvailable() {

        }
    }
}
