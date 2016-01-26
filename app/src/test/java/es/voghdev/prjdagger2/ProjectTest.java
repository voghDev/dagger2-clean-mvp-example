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

public class ProjectTest {
    @Test
    public void shouldCompare1To1() throws Exception {
        assertEquals(1, 1);
    }
}
