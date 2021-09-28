package com.example.wonderengpractical.dbhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.wonderengpractical.model.Shop;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WonderEngPractical";

    private static final String TABLE_SHOP = "shop";
    private static final String SHOP_ID = "shopId";
    private static final String SHOP_NAME = "shopName";
    private static final String SHOP_DESCRIPTION = "shopDesc";
    private static final String SHOP_ADDRESS = "shopAddress";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_SHOP_TABLE = "CREATE TABLE " + TABLE_SHOP + "("
                + SHOP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SHOP_NAME + " STRING," +
                SHOP_DESCRIPTION + " STRING," +
                SHOP_ADDRESS + " STRING" +
               /* STREET + " STRING," +
                SUITS + " STRING," +
                CITY + " STRING," +
                ZIP_CODE + " STRING," +
                LAT + " STRING," +
                LNG + " STRING," +
                COMPANYNAME + " STRING," +
                BS + " STRING," +
                PHONE + " STRING," +
                WEB_SITE + " STRING"+ */")";
        sqLiteDatabase.execSQL(CREATE_SHOP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOP);
        onCreate(sqLiteDatabase);
    }

    public void addShop(String shopName, String details, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SHOP_NAME, shopName);
        values.put(SHOP_DESCRIPTION, details);
        values.put(SHOP_ADDRESS, address);
        db.insert(TABLE_SHOP, null, values);
        db.close();
    }

    public ArrayList<Shop> getAllShops() {
        ArrayList<Shop> postList = new ArrayList<>();
        String query = "Select * from " + TABLE_SHOP;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Shop postResponse = new Shop();
                postResponse.setShopId(cursor.getInt(0));
                postResponse.setShopName(cursor.getString(1));
                postResponse.setShopDesc(cursor.getString(2));
                postResponse.setShopAddress(cursor.getString(3));
                postList.add(postResponse);
            } while (cursor.moveToNext());
        }
        return postList;
    }
}
