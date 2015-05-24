/**
 * Copyright � 2014 Android ATC.
 * 
 * Author: Android ATC Training Team.
 * 
 * Source code in this project is provided for trainers of  
 * course AND-401 titled "Android Application Development".
 * 
 * The is the source code for Lab 9: Creating SQLite Database.
 * 
 */
package com.oskrojas.elrefri;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBAdapter {

	Context context;
	private myDBHelper dbHelper;
	private SQLiteDatabase db;

	private String DATABASE_NAME = "data";
	private int DATABASE_VERSION = 1;

	public MyDBAdapter(Context context) {
		this.context = context;
		dbHelper = new myDBHelper(context, DATABASE_NAME, null,
				DATABASE_VERSION);
	}

	public void open() {
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * Insert a new student to DB.
	 * @param nombre
	 * @param precio
	 */
	public void insertProducto(String nombre, int precio) {
		ContentValues cv = new ContentValues();
		cv.put("nombre", nombre);
		cv.put("precio", precio);
		db.insert("productos", null, cv);
	}

	/**
	 *
	 * Deletes all students in the engineering faculty.
	 *
	 */
	public void deleteProducto() {
		db.delete("productos", "id=", null);
	}

	/**
	 * Returns a list of all students in the DB.
	 * @return A list of students.
	 */
	public ArrayList<String> selectAllProductos() {
		ArrayList<String> allProductos = new ArrayList<String>();
		Cursor cursor = db
				.query("productos", null, null, null, null, null, null);

		if (cursor != null && cursor.moveToFirst()) {
			do {
				allProductos.add(cursor.getString(1));
			} while (cursor.moveToNext());
		}

		return allProductos;
	}

	public ArrayList<Integer> selectAllPlacas(){

		ArrayList<Integer> allPlacas = new ArrayList<Integer>();
		Cursor cursor = db.query("autos", null , "digito", null, null, null, null);

		if (cursor != null && cursor.moveToFirst()) {
			do {
				allPlacas.add(cursor.getInt(2));
			} while (cursor.moveToNext());
		}

		return allPlacas;
	}

	public class myDBHelper extends SQLiteOpenHelper {
		public myDBHelper(Context context, String name, CursorFactory factory,
						  int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String query = "CREATE TABLE productos (id integer primary key autoincrement, nombre text, precio integer);";
			db.execSQL(query);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			String query = "DROP TABLE IF EXISTS autos;";
			db.execSQL(query);
			onCreate(db);
		}
	}
}
