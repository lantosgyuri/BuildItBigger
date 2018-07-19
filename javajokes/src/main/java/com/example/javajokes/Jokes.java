package com.example.javajokes;

import java.util.Random;

public class Jokes {

    private String[] jokes = {"Can a kangaroo jump higher than a house? \n" +
            "-\n" +
            "Of course, a house doesn’t jump at all.\n" +
            "\n",
            "Doctor: \"I'm sorry but you suffer from a terminal illness and have only 10 to live.\"\n" +
            "\n" +
            "Patient: \"What do you mean, 10? 10 what? Months? Weeks?!\"\n" +
            "\n" +
            "Doctor: \"Nine.\"\n" +
            "\n",
            "Anton, do you think I’m a bad mother?\n" +
            "\n" +
            "My name is Paul.\n" +
            "\n"};

    public String getJoke() {
        Random random = new Random();
        int randomNumb = random.nextInt(2);

        return jokes[randomNumb];
    }
}

