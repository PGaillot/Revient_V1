package com.gayo.revient.service;
import com.gayo.revient.model.StuffType;

import java.util.Arrays;
import java.util.List;

public class StuffTypeGenerator {

    public static List<StuffType> mStuffTypes = Arrays.asList(
            new StuffType("0", "Vêtements", "ic_property_1_styler_fill0_wght400_grad0_opsz48"),
            new StuffType("1", "Outils", "ic_property_1_tools_power_drill_fill0_wght400_grad0_opsz48"),
            new StuffType("2" , "Livres", "ic_property_1_book_fill0_wght400_grad0_opsz48"),
            new StuffType("3", "Argent", "ic_property_1_account_balance_wallet_fill0_wght400_grad0_opsz48_1"),
            new StuffType("4", "Autre", "ic_property_1_science_fill0_wght400_grad0_opsz48")
    );


    public static List<StuffType> GenerateStuffTypes(){return mStuffTypes;};

}
