package de.optimaxenergy;


import de.optimaxenergy.api.Bidder;
import de.optimaxenergy.service.StrategyChooser;
import de.optimaxenergy.service.StrategyChooserImpl;
import de.optimaxenergy.service.Trader;

import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Bidder bidder = new Trader();
        StrategyChooser strategyChooser = new StrategyChooserImpl(bidder);
        strategyChooser.choose();

        new TradingBot(bidder).run(20, 15);
    }


}