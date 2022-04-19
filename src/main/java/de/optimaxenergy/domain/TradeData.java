package de.optimaxenergy.domain;

/**
 * Data that necessary for bid calculation
 */
public class TradeData {
    /**
     * Money at the start of a game (Monetary units)
     */
    private final int startMoney;
    /**
     * Number of rounds necessary to win
     */
    private final int roundsToWin;
    /**
     * Number of products at the start of a game (Quantity units)
     */
    private final int allProducts;
    /**
     * Money than we have in current round
     */
    private int currentMoney;

    /**
     * Number of rounds that we won
     */
    private int wonRounds;

    public TradeData(int startMoney, int allProducts) {
        this.startMoney = startMoney;
        this.allProducts = allProducts;
        this.roundsToWin = allProducts / 4 + 1; // all products div 2 products per round and div to 2, because we should win int half + 1 rounds
        this.wonRounds = 0;
        this.currentMoney = startMoney;
    }

    public TradeData(int startMoney, int currentMoney, int allProducts) {
        this.startMoney = startMoney;
        this.currentMoney = currentMoney;
        this.allProducts = allProducts;
        this.roundsToWin = allProducts / 4 + 1; // all products div 2 products per round and div to 2, because we should win int half + 1 rounds
        this.wonRounds = 0;
    }

    public TradeData(int startMoney, int currentMoney, int allProducts, int wonRounds) {
        this.startMoney = startMoney;
        this.currentMoney = currentMoney;
        this.allProducts = allProducts;
        this.roundsToWin = allProducts / 4 + 1; // all products div 2 products per round and div to 2, because we should win int half + 1 rounds
        this.wonRounds = wonRounds;
    }

    public int getStartMoney() {
        return startMoney;
    }

    public int getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(int currentMoney) {
        this.currentMoney = currentMoney;
    }

    public int getAllProducts() {
        return allProducts;
    }

    public int getRoundsToWin() {
        return roundsToWin;
    }

    public int getWonRounds() {
        return wonRounds;
    }

    public void wonRound() {
        this.wonRounds++;
    }
}
