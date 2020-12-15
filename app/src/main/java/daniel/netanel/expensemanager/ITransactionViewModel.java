package daniel.netanel.expensemanager;

/**
 * The View Model Interface
 */
public interface ITransactionViewModel {

    @android.webkit.JavascriptInterface
    public boolean insert(String type, String category, String details, String amount, String month) throws TransactionException;

    @android.webkit.JavascriptInterface
    public String getAllTransactions() throws TransactionException;

    @android.webkit.JavascriptInterface
    public int deleteAllTransactions() throws TransactionException;

    @android.webkit.JavascriptInterface
    public double sumExpenses(String category) throws TransactionException;

    @android.webkit.JavascriptInterface
    public double sumIncomes() throws TransactionException;

}