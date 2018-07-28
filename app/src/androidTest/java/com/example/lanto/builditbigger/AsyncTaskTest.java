package com.example.lanto.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    private static final String LOG_TAG= AsyncTaskTest.class.getSimpleName();

    @Test
    public void test(){
        String result = null;

        GetJokeAsync async = new GetJokeAsync();
        async.execute();

        try {
            result = async.get();
        } catch (InterruptedException e) {
            Log.e(LOG_TAG, "Interrupted Exception");
        } catch (ExecutionException e) {
            Log.e(LOG_TAG, "Execution Exception");
        }

        assertNotNull(result);
    }
}
