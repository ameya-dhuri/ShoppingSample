package com.ameya.shopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ItemDB";
    private static final String TABLE_NAME = "Items";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "title";
    private static final String KEY_URL = "thumbnailUrl";
    private static final String[] COLUMNS = { KEY_ID, KEY_NAME, KEY_URL};

    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE Items ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "title TEXT, "
                + "thumbnailUrl TEXT)";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public Item getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Item item = new Item();
        item.setId(Integer.parseInt(cursor.getString(0)));
        item.setTitle(cursor.getString(1));
        item.setThumbnailUrl(cursor.getString(2));

        return item;
    }

    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getTitle());
        values.put(KEY_URL, item.getThumbnailUrl());

        db.insert(TABLE_NAME,null, values);
        db.close();
    }

}
