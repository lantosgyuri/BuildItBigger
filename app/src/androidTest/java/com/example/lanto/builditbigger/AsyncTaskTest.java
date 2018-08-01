package com.example.lanto.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    private CountDownLatch signal = new CountDownLatch(1);

    @Test
    public void test() throws InterruptedException {

        GetJokeAsync async = new GetJokeAsync();
        async.setCallback(new GetJokeAsync.getJOkeAsyncCallback() {
            @Override
            public void jokeDone(String result, boolean failedToLoad) {
                assertNotNull(result);
                assertFalse(failedToLoad);
                signal.countDown();
            }
        });
        async.execute();
        signal.await();
    }
}
