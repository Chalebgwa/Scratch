package com.nest.scratch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by black on 2/13/17.
 */

public class DBLocal extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Linka59.db";
    private static final int DATABASE_VERSION = 1;

    public DBLocal(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public DBLocal(Context context,String DBNAME){
        super(context,DBNAME,null,DATABASE_VERSION);
    }

    public void addItem(Item item){
        ContentValues values = new ContentValues();

        values.put("userid",2);
        values.put("title",String.valueOf(item.getItem_title()));
        values.put("description",String.valueOf(item.getItem_description()));
        values.put("price",String.valueOf(item.getItem_price()));
        values.put("image1",Util.getImageBytes(item.getImages()[0]));
        values.put("image2",Util.getImageBytes(item.getImages()[1]));
        values.put("image3",Util.getImageBytes(item.getImages()[2]));

        SQLiteDatabase db = getWritableDatabase();
        db.insert("ITEMS",null,values);

        db.close();

    }

    public ArrayList<Item> getAllItems(){
        ArrayList<Item> items = new ArrayList<>();
        String[] columns = {"title","userid","description","price","image1","image2","image3"};
        SQLiteDatabase db = getReadableDatabase();
        //Cursor cursor = db.query(false, "ITEMS", null, null, null, null, null, "'DESC'",null);
        Cursor cursor = db.rawQuery("SELECT * FROM ITEMS",null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){

            String title=cursor.getString(cursor.getColumnIndex("title"));
            String description =cursor.getString(cursor.getColumnIndex("description"));
            String price =cursor.getString(cursor.getColumnIndex("price"));
            Bitmap image1 =Util.getImage(cursor.getBlob(cursor.getColumnIndex("image1")));
            Bitmap image2 =Util.getImage(cursor.getBlob(cursor.getColumnIndex("image2")));
            Bitmap image3 =Util.getImage(cursor.getBlob(cursor.getColumnIndex("image3")));


            Bitmap[] images = {image1,image2,image3};
            Item item = new Item(title,description,price,images);
            items.add(item);
            cursor.moveToNext();
        }


        //db.close();
        return items;
    }

    public void addMessage(Thread.Message message){
        ContentValues values = new ContentValues();
        values.put("senderID",message.getMessageID());
        values.put("messageText",message.getMessage());

        getWritableDatabase().insert("MESSAGES",null,values);
    }

    public ArrayList getMessages(int userId){
        ArrayList<Thread> messages = new ArrayList<>();


        return messages;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query =  "CREATE TABLE ITEMS (" +
                "id INTEGER PRIMARY KEY" +
                ",userid INTEGER NOT NULL " +
                ",description VACHAR " +
                ",price VARCHAR" +
                ",title VARCHAR " +
                ",image1 BLOB" +
                ",image2 BLOB" +
                ",image3 BLOB);";
        db.execSQL(query);
        query = "CREATE TABLE MESSAGES (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ",messageText VACHAR" +
                ",senderID INTEGER;";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
