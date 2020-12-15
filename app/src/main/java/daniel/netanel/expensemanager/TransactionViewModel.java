package daniel.netanel.expensemanager;

import android.database.Cursor;
import android.webkit.WebView;

/**
 * The View Model Class of the project that implements the ITransactionViewModel interface
 */
public class TransactionViewModel implements ITransactionViewModel {
    private WebView view;
    private TransactionModel txModel;

    /**
     * This method set the web view
     *
     * @param view Reference of WebView object
     */
    public void setView(WebView view) throws TransactionException {
        this.view = view;
    }

    /**
     * This method set the Model
     *
     * @param txModel Reference of TransactionModel object
     */
    public void setTxModel(TransactionModel txModel) throws TransactionException {
        this.txModel = txModel;
    }

    /**
     * This method get a transaction details from the view and send them to the model that insert
     * the transaction to the data base. this method is accessible in the view
     *
     * @param type     Transaction Type
     * @param category Transaction Category
     * @param details  Transaction Details
     * @param amount   Transaction Amount
     * @param month    Transaction Month
     * @return Return true if the transaction successfully inserted and false if not
     * @throws TransactionException The specific exception of the project
     */
    @android.webkit.JavascriptInterface
    public boolean insert(String type, String category, String details, String amount, String month) throws TransactionException {
        final Transaction tx = new Transaction(type, category, details, amount, month);
        return txModel.insert(tx);
    }

    /**
     * This method Retrieves all the transactions from the data base and send them to the model that insert
     * the transaction to the data base. this method is accessible in the view
     *
     * @return Return a string that prints all thr transaction in html file
     * @throws TransactionException The specific exception of the project
     */
    @android.webkit.JavascriptInterface
    public String getAllTransactions() throws TransactionException {
        Cursor allTX = txModel.getAllTransaction();
        StringBuilder st = new StringBuilder();
        st.append("<ul data-filter=\"true\" data-filter-placeholder=\"Search transactions...\" data-role=\"listview\" data-inset=\"true\">");
        while (allTX.moveToNext()) {
            st.append("<li>");
            if(allTX.getString(1).equals("Expense")) {
                st.append("<div class='right-pos'><img src='images/red.png' id='red'></div>");
            }
            else
                st.append("<div class='right-pos'><img src='images/green.png' id='green'></div>");
            st.append("<h2>").append(allTX.getString(1)).append("</h2>");
            st.append("<h2>Transaction #").append(allTX.getString(0)).append("</h2>");
            if(allTX.getString(1).equals("Expense")) {
                st.append("<h2>Category: ").append(allTX.getString(2)).append("</h2>");
            }
            st.append("<h2>Details: ").append(allTX.getString(3)).append("</h2>");
            st.append("<h2>Amount: ").append(allTX.getString(4)).append("</h2>");
            st.append("<h2>Month: ").append(allTX.getString(5)).append("</h2></li>");
        }
        st.append("</ul>");
        return st.toString();
    }

    /**
     * This method delete all the transactions from the data base and this method is accessible in the view
     *
     * @return Returns the number of deleted transaction
     * @throws TransactionException The specific exception of the project
     */
    @android.webkit.JavascriptInterface
    public int deleteAllTransactions() throws TransactionException {
        return txModel.deleteAllTransactions();
    }

    /**
     * This method delete the transactions from the data base by id and this method is accessible in the view
     *
     * @param id Transaction ID
     * @return Returns the number of deleted transaction
     * @throws TransactionException
     */
    @android.webkit.JavascriptInterface
    public int delete(String id) throws TransactionException {
        return txModel.delete(id);
    }

    /**
     * This method calculate the sum of expenses by category and this method is accessible in the view
     *
     * @param category The category we want to calculate
     * @return Returns a Cursor var that we have to extract the sum from it
     * @throws TransactionException The specific exception of the project
     */
    @android.webkit.JavascriptInterface
    public double sumExpenses(String category) throws TransactionException {
        Cursor cursor = txModel.sumExpenses(category);
        return cursor.getDouble(cursor.getColumnIndex("total"));
    }

    /**
     * This method calculate the sum of all incomes from the data base and this method is accessible in the view
     *
     * @return Returns a Cursor var that we have to extract the sum from it
     * @throws TransactionException The specific exception of the project
     */
    @android.webkit.JavascriptInterface
    public double sumIncomes() throws TransactionException {
        Cursor cursor = txModel.sumIncomes();
        return cursor.getDouble(cursor.getColumnIndex("total"));
    }

}