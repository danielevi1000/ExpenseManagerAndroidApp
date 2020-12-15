package daniel.netanel.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * The model of the project that implements the interface ITransactionModel
 * and inherit from SQLiteOpenHelper object
 */
public class TransactionModel extends SQLiteOpenHelper implements ITransactionModel {
    public static final String DATABASE_NAME = "transactions.db";
    public static final String TABLE_NAME = "transaction_table";
    public static final String col_1 = "ID";
    public static final String col_2 = "TYPE";
    public static final String col_3 = "CATEGORY";
    public static final String col_4 = "DETAILS";
    public static final String col_5 = "AMOUNT";
    public static final String col_6 = "MONTH";

    /**
     * @param context
     * @throws TransactionException The specific exception of the project
     */
    public TransactionModel(Context context) throws TransactionException {
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * This method creates our data base in Sqlite
     *
     * @param sqLiteDatabase Reference to SQLiteDatabase object
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" + col_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + col_2 + " TEXT, "
                + col_3 + " TEXT , " + col_4 + " TEXT , " + col_5 + " TEXT , " + col_6 + " TEXT)");
    }

    /**
     *
     * @param sqLiteDatabase Reference to SQLiteDatabase object
     * @param i              Describe old version of database
     * @param i1             Describe old version of database
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /**
     * This method insert a new transaction to the database
     *
     * @param tx Reference to Transaction object
     * @return Returns true if transaction successfully inserted and false if not
     * @throws TransactionException The specific exception of the project
     */
    public boolean insert(Transaction tx) throws TransactionException {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2, tx.getType());
        contentValues.put(col_3, tx.getCategory());
        contentValues.put(col_4, tx.getDetails());
        contentValues.put(col_5, tx.getAmount());
        contentValues.put(col_6, tx.getMonth());
        long res = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return res != -1;
    }

    /**
     * This method Retrieves All the transaction from the data base
     *
     * @return Returns Cursor var with all the transaction
     * @throws TransactionException The specific exception of the project
     */
    public Cursor getAllTransaction() throws TransactionException {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
    }

/**
 public boolean update(Transaction tx) {
 SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
 ContentValues contentValues = new ContentValues();
 contentValues.put(col_1, tx.getId());
 contentValues.put(col_2, tx.getType());
 contentValues.put(col_3, tx.getCategory());
 contentValues.put(col_4, tx.getDetails());
 contentValues.put(col_5, tx.getAmount());
 contentValues.put(col_6, tx.getMonth());
 sqLiteDatabase.update(TABLE_NAME, contentValues, "ID = ?", new String[]{String.valueOf(tx.getId())});
 return true;
 }
 **/

    /**
     * this method delete a transaction by id
     *
     * @param id - Transaction ID
     * @return Returns the number of deleted transaction
     * @throws TransactionException - the specific exception of the project
     */
    public Integer delete(String id) throws TransactionException {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME= '" + TABLE_NAME + "'");
        return sqLiteDatabase.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    /**
     * this method delete all the transaction from data base
     *
     * @return return number of deleted transaction
     * @throws TransactionException - the specific exception of the project
     */
    public Integer deleteAllTransactions() throws TransactionException {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME= '" + TABLE_NAME + "'");
        return sqLiteDatabase.delete(TABLE_NAME, null, null);
    }

    /**
     * this method calculate the sum of all expenses by category
     *
     * @param category - The category we want to calculate
     * @return - returns a Cursor var that we have to extract the sum from it
     * @throws TransactionException - the specific exception of the project
     */
    public Cursor sumExpenses(String category) throws TransactionException {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(AMOUNT) as total FROM " + TABLE_NAME
                + " WHERE TYPE='Expense' and CATEGORY=" + "'" + category + "'", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * this method calculate the sum of all incomes from the data base
     *
     * @return - returns a Cursor var that we have to extract the sum from it
     * @throws TransactionException - the specific exception of the project
     */
    public Cursor sumIncomes() throws TransactionException {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(AMOUNT) as total FROM " + TABLE_NAME
                + " WHERE TYPE='Income'", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
