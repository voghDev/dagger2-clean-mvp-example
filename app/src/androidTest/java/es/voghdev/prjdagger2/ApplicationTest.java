package es.voghdev.prjdagger2;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class) @LargeTest
public class ApplicationTest  {

    @Test
    public void shouldPassThisTest()  {
        assertEquals(1,1);
    }
}