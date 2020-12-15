package daniel.netanel.expensemanager;

import android.database.Cursor;

/**
 * The Model Interface
 */
public interface ITransactionModel {

    public boolean insert(Transaction tx) throws TransactionException;

    public Cursor getAllTransaction() throws TransactionException;

    public Integer delete(String id) throws TransactionException;

    public Integer deleteAllTransactions() throws TransactionException;

    public Cursor sumExpenses(String category) throws TransactionException;

    public Cursor sumIncomes() throws TransactionException;
}
