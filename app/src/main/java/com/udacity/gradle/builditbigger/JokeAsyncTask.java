package com.udacity.gradle.builditbigger;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import com.katmitchell.builditbigger.backend.jokester.Jokester;
import com.katmitchell.builditbigger.backend.jokester.model.Joke;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Kat on 4/20/16.
 */
public class JokeAsyncTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = "JokeAsyncTask";

    private Listener mListener;

    public JokeAsyncTask(Listener listener) {
        mListener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {

        Jokester.Builder builder = new Jokester.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl(
                        "http://10.0.2.2:8080/_ah/api/") // 10.0.2.2 is localhost's IP address in Android emulator
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(
                            AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
                            throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

        Jokester jokester = builder.build();
        try {
            Jokester.GetJoke jokeRequest = jokester.getJoke();
            Joke joke = jokeRequest.execute();
            return joke.getJoke();
        } catch (IOException e) {
            Log.e(TAG, "Failed to get joke, har har har", e);
        }

        return "Failed to get joke, har har har";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (mListener != null) {
            mListener.displayJoke(s);
        }
    }

    public interface Listener {

        void displayJoke(String joke);
    }
}
