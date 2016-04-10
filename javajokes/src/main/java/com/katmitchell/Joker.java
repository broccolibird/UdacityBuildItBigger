package com.katmitchell;

import java.util.Random;

public class Joker {

    public static final String[] JOKES = {
            "Q: How do you tell an introverted computer scientist from an extroverted computer scientist?\n"
                    + "A: An extroverted computer scientist looks at your shoes when he talks to you.",
            "Knock knock.\n"
                    + "Race condition.\n"
                    + "Who's there?",
            "Q: How many programmers does it take to screw in a light bulb?\n"
                    + "A: None. It's a hardware problem."
    };

    public static String getAJoke() {
        Random random = new Random();
        int index = random.nextInt(JOKES.length);
        return JOKES[index];
    }
}
