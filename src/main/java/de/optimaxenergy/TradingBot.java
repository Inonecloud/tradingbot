package de.optimaxenergy;

import de.optimaxenergy.api.Bidder;
import de.optimaxenergy.service.StrategyChooser;
import de.optimaxenergy.service.StrategyChooserImpl;
import de.optimaxenergy.service.Trader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;

public class TradingBot {
    private static final Logger log = LoggerFactory.getLogger(TradingBot.class);

    private int ownProducts = 0;
    private int otherProducts = 0;

    private int ownMoney;
    private int otherMoney;
    private final Bidder ownBidder;
    private final Bidder otherBidder;

    private final StrategyChooser strategyChooser;


    public TradingBot(Bidder bidder) {
        otherBidder = bidder;
        ownBidder = new Trader();

        try {
            this.strategyChooser = new StrategyChooserImpl(ownBidder);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Run a trade game
     *
     * @param quantityUnits number of products to trade
     * @param monetaryUnits number of money at the start
     */
    public void run(int quantityUnits, int monetaryUnits) {
        ownMoney = monetaryUnits;
        otherMoney = monetaryUnits;

        ownBidder.init(quantityUnits, monetaryUnits);
        otherBidder.init(quantityUnits, monetaryUnits);

        while (quantityUnits > 0) {
            strategyChooser.choose();

            int ourBid = ownBidder.placeBid();
            int theirBid = otherBidder.placeBid();

            ownBidder.bids(ourBid, theirBid);
            otherBidder.bids(theirBid, ourBid);

            quantityUnits = quantityUnits - 2;
            calculateRoundWinner(ourBid, theirBid);

            ownMoney = ownMoney - ourBid;
            otherMoney = otherMoney - theirBid;

        }

        calculateGameWinner();
    }

    /**
     * Calculate a winner of trade game
     */
    private void calculateGameWinner() {
        log.info("own products: {}, other products {}", ownProducts, otherProducts);
        log.info("own money: {}, other money {}", ownMoney, otherMoney);
        if (ownProducts == otherProducts) {
            if (ownMoney > otherMoney) {
                log.error("We win!!!");
            }
            if (ownMoney == otherMoney) {
                log.error("Draw!!!");
            }
            if (ownMoney < otherMoney) {
                log.error("We loose!!!");
            }
        } else if (ownProducts > otherProducts) {
            log.error("We win!");
        } else {
            log.error("We loose :.(");
        }
    }

    /**
     * Calculate a winner in trade round
     *
     * @param ownBid   a bid from our bot
     * @param otherBid a bit from other bot
     */
    private void calculateRoundWinner(int ownBid, int otherBid) {
        if (ownBid > otherBid) {
            ownProducts = ownProducts + 2;
            log.info("We win! \n own bid {}, other bid {} \n own products {}, other products {}", ownBid, otherBid, ownProducts, otherProducts);
        }
        if (ownBid == otherBid) {
            ownProducts++;
            otherProducts++;
            log.info("Draw! \n own bid {}, other bid {} \n own products {}, other products {}", ownBid, otherBid, ownProducts, otherProducts);
        }
        if (ownBid < otherBid) {
            otherProducts = otherProducts + 2;
            log.info("We loose :( \n own bid {}, other Bid {} \n own products {}, other products {}", ownBid, otherBid, ownProducts, otherProducts);
        }
    }

}