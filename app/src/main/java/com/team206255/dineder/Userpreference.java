package com.team206255.dineder;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by walter on 2017-11-03.
 */

public class Userpreference {
    ArrayList<Integer> likedID;

    public Userpreference()
    {
        likedID = new ArrayList<>();
    }

    public void setUpDummy()
    {}

    public void addID(int id)
    {
        likedID.add(id);
    }

    public int getRandomID()
    {
        if (likedID.size() <= 2) return -1;
        Random rand = new Random();
        return likedID.get(rand.nextInt(likedID.size() - 1) + 1);
    }
}
