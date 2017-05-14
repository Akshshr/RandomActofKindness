package com.first.akashshrivastava.randomactofkindness;

import android.util.Log;

import java.util.*;

/**
 * Created by akashshrivastava on 15-03-18.
 */
public class FactBook {


    private static final String TAG = "Factbook";


    List<String> facts;
    List<String> remainingFacts;

    public FactBook()
    {
        facts = new ArrayList<String>();

        Collections.addAll(facts,
                "Tell that special someone, you love them the first time you see them, mean it!",
                "Buy a homeless person a cup of coffee, Really!! ",
                "Let someone in line go past you, will earn you a smile! Promise! ",
                "Invite someone new to have lunch with you today at work or school.",
                "Give that special someone a extra long hug :) ",
                "Introduce yourself to your neighbors, if you haven't already",
                "Say 'Thank you' a lot more than you do",
                "Difficulty: HARD, adopt a pet from your local shelter",
                "That alone drunk person on the street, ask him if he/she is doing alright",
                "Please don't litter, pick up the trash when you see some",
                "Breakfast in bed is an AMAZING feeling! Do it for someone you care",
                "Connect different circles of people together, more the merrier ",
                "Bring donuts/a healthy treat to your friends, I know I would love it! ",
                "Offer your seat in the bus/subway to the person who needs it",
                "Take all those pennies lying around the house and give them away",
                "Make amends for the wrongs you have done in your life",
                "Have you discovered something that changed your life? Inspire others!",
                "Eat a really healthy meal today, you already do? Kudos!!",
                "End the day by reminding yourself that you are awesome! Cause duh! ",
                "Bring tea/coffee to a friend you know who is having a tough time this week",
                "Say good morning to at least 5 strangers today! DO IT! We are watching! ",
                "Cook a meal for your significant other or anyone for no reason",
                "Allow yourself a 10 minute break to relax and imagine bahamas in the summer",
                "Going old school : Give a handwritten thank you note to someone you know",
                "Smile at the first 3 strangers you see, not in a creepy way though :-/",
                "Open the door for the person behind you, its common in Sweden!",
                "Eat a little less bacon today, vegetarian already? Kudos!!",
                "Tip with generosity the next time you are out eating",
                "Think what the world would be like if everyone was vegetarian! Animals <3",
                "Give a tiny bit extra money to a homeless person/a person in need",
                "Send a happy message via your phone or an email to someone dear",
                "See if you have extra clothes to donate and then donate them",
                "You have been doing so well, nothing for today! :) ",
                "Listen to your heart today <3",
                "Time to buy more organic food, be nice to mother nature",
                "Today you write, yes write a letter to someone",
                "Don't get on facebook today, be nice to your eyes, mind and soul",
                "Donate blood this week! It really makes a difference! ",
                "Eco-drive today, be nice to the planet",
                "Take out the trash without being asked to, except now i.e. Take it out!",
                "Free choice! Do whatever you think is nice!",
                "Take some time remembering an old friend and then call them! :) ",
                "Replace all potty words with the word 'Muffin' today",
                "Thank someone for just being there, even if it was weeks ago",
                "Tell your parent/parents/guardian how much they mean to you",
                "Buy someone a coffee card, ok just a coffee then.",
                "Buy a kid you know some candy, cause who doesn't like candy! ",
                "Spend sometime looking through really old pictures of you/family",
                "Learn to make a new recipe today!",
                "Walk when you don't have to use the car",
                "Do some volunteering during the weekends",
                "Consider donating some money to charity, a tiny portion a month doesn't hurt",
                "Bring something nice to your colleagues at work, something sweet maybe? :)",
                "Help a kid understand something he/she is asking you, with patience",
                "Help remove the small pains in life for someone else, like replacing the batteries for the remote",
                "Reflect and connect two people that really would help each other out",
                "Connect with an old friend from highschool today, just to see how they are doing",
                "Today we donate clothes, we know you have an old pile sitting in your wardrobe",
                "Please don't buy fur and tell your friends off who do. Animals are cuter when alive");


        remainingFacts = new ArrayList<String>();
        remainingFacts.addAll(facts);

       // Log.d(TAG, "These are the facts" + facts);
    }

    public String getFact(){
        //The string that gets called from getFact()
        String junk = "";

        //Randomly have to add facts now!
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(remainingFacts.size());

        //Take a random number from the array and then makes it THEE string to display
        junk = remainingFacts.get(randomNumber);

        remainingFacts.remove(randomNumber);

        if (remainingFacts.size() == 0)
        {
            remainingFacts.addAll(facts);
        }

        return junk;
    }
}
