package es.voghdev.prjdagger2;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.ui.activity.UserListActivity;
import es.voghdev.prjdagger2.usecase.GetUsers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class) @LargeTest
public class ApplicationTest  {

    @Mock
    GetUsers getUsers;

    @Rule public IntentsTestRule<UserListActivity> activityRule =
            new IntentsTestRule<>(UserListActivity.class, true, false);

    @Test
    public void shouldShowRecyclerView()  {
        startActivity();

        onView(withId(R.id.users_list)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowAMockedListOfUsers() {
        List<User> list = givenSomeMockedUsers();

        //Mockito.when(getUsers.get()).thenReturn(list);

        startActivity();

        onView(withId(R.id.users_list)).check(matches(isDisplayed()));
    }

    private List<User> givenSomeMockedUsers() {
        List<User> list = new ArrayList<User>();

        list.add(createMockUser("Mrs", "Anne Morrison", "anne.morrison@example.com"));
        list.add(createMockUser("D", "Juan Carlos Guerrero", "jc.guerrero@example.com"));
        list.add(createMockUser("Dña", "Maria Medina", "maria.medina@example.com"));

        return list;
    }

    private User createMockUser(String title, String name, String email) {
        User u = new User();
        u.setId(email);
        u.setName(name);
        u.setFacebookId(String.format("%s%s", title.toLowerCase(), name.toLowerCase().replace(" ","")));
        return u;
    }

    private UserListActivity startActivity() {
        return activityRule.launchActivity(null);
    }
}