/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.katmitchell.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import com.katmitchell.Joker;

import java.util.Random;

/** An endpoint class we are exposing */
@Api(
        name = "jokester",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.katmitchell.com",
                ownerName = "backend.builditbigger.katmitchell.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "getJoke")
    public Joke getJoke() {

        String jokeString = Joker.getAJoke();

        Joke joke = new Joke();
        joke.setJoke(jokeString);

        return joke;

    }
}
