//This class will handle the payment process, integrating with a payment gateway to process credit cards or other payment methods. Credit cards must be validated with the following 1) Number of digits, 2) expiration date 3) first digit must match with the type of card 4 for visa 5 master card 6 discover .... Credit card validation will return true or false. Cash payment verification returns a negative number or positive number or the exact amount.

public class Payment {
    private double cash;
    private String cardNumber;
    private String expirationDate;
    private String ccv;

    /**
     * Constructor for cash payment
     * 
     * @param cash
     */
    public Payment(double cash) {
        this.cash = cash;
    }

    /**
     * Constructor for credit card payment
     * 
     * @param cardNumber
     * @param expirationDate
     * @param ccv
     */
    public Payment(String cardNumber, String expirationDate, String ccv) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.ccv = ccv;
    }

    /**
     * Verify credit card info to see if its usable
     * 
     * @return true or false
     */
    public boolean verifyCreditCard() {
        char firstDigit = cardNumber.charAt(0);
        if (cardNumber.length() == 16 && expirationDate.length() == 4 && ccv.length() == 3) {
            if (firstDigit == '4' || firstDigit == '5' || firstDigit == '6') {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if cash is equal to the total cost
     * 
     * @param totalCost
     * @return true or false
     */
    public boolean isCashEqual(double totalCost) {
        return cash >= totalCost;
    }

    /**
     * Check if cash is over/under the total cost
     * 
     * @param totalCost
     * @return true or false
     */
    public double overOrUnder(double totalCost) {
        return cash - totalCost;
    }

    /**
     * Give back change
     * 
     * @param totalCost
     * @return change
     */
    public double giveChange(double totalCost) {
        return overOrUnder(totalCost);
    }

}
