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

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.usecase.GetUsers;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserListTest extends BaseUnitTest {
    @Mock
    UserListCollaborator userListCollaborator;

    UserListCaller userListCaller;

    @Captor
    ArgumentCaptor<GetUsers.Listener> argumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userListCaller = new UserListCaller(userListCollaborator);
    }

    @Test
    public void shouldAddTwoPlusTwo() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void shouldReturnAMockedListOfUsers() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                List<User> users = new ArrayList<User>();
                User b = createMockUser("A001", "John Smith", "Sunset Blvd. 27", "smithjohn", "", "1248234823");
                users.add(b);
                ((GetUsers.Listener) invocation.getArguments()[0]).onUsersReceived(users, true);
                return null;
            }
        }).when(userListCollaborator).getUsers(
                any(GetUsers.Listener.class));

        userListCaller.getUsers();

        verify(userListCollaborator, times(1)).getUsers(
                any(GetUsers.Listener.class));

        assertEquals(userListCaller.getResult().size(), 1);
        assertEquals(userListCaller.getResult().get(0).getName(), "John Smith");
    }

    @Test
    public void shouldReturnAMockedListOfUsersUsingAnArgumentCaptor() throws Exception {
        userListCaller.getUsers();

        List<User> result = new ArrayList<User>();
        User a = createMockUser("A001", "John Smith", "Sunset Blvd. 27", "smithjohn", "", "1248234823");
        result.add(a);

        // Let's call the callback. ArgumentCaptor.capture() works like a matcher.
        verify(userListCollaborator, times(1)).getUsers(
                argumentCaptor.capture());

        assertNull(userListCaller.getResult());

        // Once you're satisfied, trigger the reply on callbackCaptor.getValue().
        argumentCaptor.getValue().onUsersReceived(result, true);

        // Some assertion about the state after the callback is called
        assertThat(userListCaller.getResult().size(), is(equalTo(1)));
    }
}
