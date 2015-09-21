package com.example.mapa_miejsa;

import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqlHelper extends SQLiteOpenHelper{
	
	private static String nazwa_bazy = "lista_lokalizacji";
	private static int database_version = 1;
	public SqlHelper(Context context) {
		super(context, nazwa_bazy, null, database_version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE lokalizacje (id INTEGER PRIMARY KEY,title varchar(255),description varchar(255),x_position varchar(255),y_position varchar(255));");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > oldVersion) {
			//db.execSQL("Alter table products ADd column price varchar(255);");
		 }
		if (newVersion > oldVersion) {
			//db.execSQL("drop table products;");
			//db.execSQL("CREATE TABLE lokalizacje (id  INTEGER PRIMARY KEY,name varchar(255),price varchar(255),count_products int(12));");
		 }
		
		
	}
	
	public void addLoc(LokalizacjaEntity Product){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("title",Product.getTitle());
		values.put("description",Product.getDescription());
		values.put("x_position", Product.getX());
		values.put("y_position", Product.getY());
		db.insertOrThrow("lokalizacje", null, values);
		
	}
	
/*	public void editProduct(LokalizacjaEntity Product){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name",Product.getName());
		values.put("count_products", Product.getCount());
		values.put("price", Product.getPrice());
		db.update("products", values, "id = "+Product.getId(), null);
		
	}*/
	
	public void deleteProduct(int id){
		SQLiteDatabase db = getWritableDatabase();
		db.delete("lokalizacje",  "id = "+id, null);
		
	}
	
	public Cursor getProducts(){
		String[] columns = new String[]{"id","title","description","x_position","y_position"};
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query("lokalizacje", columns, null,null,null,null,null);
		return cursor;
	}

	
	public LokalizacjaEntity getProduct(int produkt_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM lokalizacje WHERE id = " + produkt_id;

        Log.d("query","query "+selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        LokalizacjaEntity pro = new LokalizacjaEntity();
        pro.setTitle((c.getString(c.getColumnIndex("title"))));
     //  pro.setX((c.getString(c.getColumnIndex("count_products"))));
     //   pro.setY(c.getString(c.getColumnIndex("price")));

        return pro;
    }
	
	

}
