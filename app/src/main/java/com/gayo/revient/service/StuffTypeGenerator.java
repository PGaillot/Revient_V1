package com.gayo.revient.service;
import com.gayo.revient.model.StuffType;

import java.util.Arrays;
import java.util.List;

public class StuffTypeGenerator {

    public static List<StuffType> mStuffTypes = Arrays.asList(
            new StuffType("0", "Vêtements"),
            new StuffType("1", "Outils"),
            new StuffType("2" , "Livres"),
            new StuffType("3", "Argent")
    );

    public static List<StuffType> GenerateStuffTypes(){return mStuffTypes;};

}
