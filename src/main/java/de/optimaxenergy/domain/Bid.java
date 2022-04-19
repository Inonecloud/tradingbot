package de.optimaxenergy.domain;


/**
 * Statistics of each round
 */
public class Bid {
    /**
     * Amount of a bid
     */
    private int amount;

    /**
     * Current balance
     */
    private int balance;
    /**
     * Number of own products
     */
    private int quantityOfProducts;

    /**
     * If we won round then true, if draw or loose, then false
     */
    private boolean won;

    private Bid() {

    }

    public int getAmount() {
        return amount;
    }

    public int getBalance() {
        return balance;
    }

    public int getQuantityOfProducts() {
        return quantityOfProducts;
    }

    public boolean isWon() {
        return won;
    }

    public static Builder newBuilder() {
        return new Bid().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder withAmount(int amount) {
            Bid.this.amount = amount;
            return this;
        }

        public Builder withBalance(int balance) {
            Bid.this.balance = balance;
            return this;
        }

        public Builder withQuantityOfProducts(int quantityOfProducts) {
            Bid.this.quantityOfProducts = quantityOfProducts;
            return this;
        }

        public Builder isWon(boolean won) {
            Bid.this.won = won;
            return this;
        }

        public Bid build() {
            return Bid.this;
        }
    }
}
