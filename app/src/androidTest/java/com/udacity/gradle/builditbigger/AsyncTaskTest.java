package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

/**
 * Created by Kat on 4/20/16.
 */
public class AsyncTaskTest extends AndroidTestCase {
    public void testVerifyNonNullResponse() {
        assertNotNull(new JokeAsyncTask(null).doInBackground());
    }

}
