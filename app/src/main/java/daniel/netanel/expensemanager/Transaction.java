package daniel.netanel.expensemanager;

/**
 * Transaction Class
 */
public class Transaction {

    private int id;
    private String type;
    private String category;
    private String details;
    private String amount;
    private String month;

    /**
     * @param type     Transaction type
     * @param category Transaction Category
     * @param details  Transaction Details
     * @param amount   Transaction amount
     * @param month    Transaction month
     */
    public Transaction(String type, String category, String details, String amount, String month) {
        this.setType(type);
        this.setCategory(category);
        this.setDetails(details);
        this.setAmount(amount);
        this.setMonth(month);
    }

    /**
     * @return Return the transaction ID
     */
    public int getId() {
        return id;
    }

    /**
     * @return Return the transaction type
     */
    public String getType() {
        return type;
    }

    /**
     * set the transaction type
     *
     * @param type - string
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return Return the transaction category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set the transaction category
     *
     * @param category string
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return Return the transaction details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Set the transaction details
     *
     * @param details string
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * @return Return the transaction amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Set the transaction amount
     *
     * @param amount string
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return Return the transaction month
     */
    public String getMonth() {
        return month;
    }

    /**
     * Set the transaction month
     *
     * @param month string
     */
    public void setMonth(String month) {
        this.month = month;
    }
}
