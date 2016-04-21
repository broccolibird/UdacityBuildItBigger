package com.udacity.gradle.builditbigger;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import com.katmitchell.builditbigger.backend.jokester.Jokester;
import com.katmitchell.builditbigger.backend.jokester.model.Joke;
import com.katmitchell.jokedisplay.JokeDisplayActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        new JokeAsyncTask().execute();

    }

    public void displayJoke(String joke) {
        Intent intent = new Intent(this, JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.EXTRA_JOKE, joke);
        startActivity(intent);
    }


    private class JokeAsyncTask extends AsyncTask<Void, Void, String> {

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

//            JokeService jokeService = NetworkManager.getJokeService();
//            Call<Joke> jokeCall = jokeService.getJoke();
//
//            try {
//                Response<Joke> jokeResponse = jokeCall.execute();
//                if (jokeResponse.isSuccessful()) {
//                    return jokeResponse.body().getJoke();
//                }
//            } catch (IOException e) {
//                Log.e(TAG, "Failed to get joke, har har har", e);
//            }
//
            return "Failed to get joke, har har har";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            displayJoke(s);
        }
    }


}
