package com.oskrojas.elrefri;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;



public class MyPreferences {
	
	// Shared Preferences
	SharedPreferences pref;

	// Editor for Shared preferences
	Editor editor;

	// Context
	Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Sharedpref file name
	private static final String PREF_NAME = "Tienda";

	
	private static final String IS_FIRSTTIME = "IsFirstTime";

	public static final String UserName = "nombre";
	


	
	// Constructor
	public MyPreferences(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

//Getters y Setters "USUARIO"	
	public boolean isFirstTime() {
			return pref.getBoolean(IS_FIRSTTIME, true);
		}
	
	public void setOld(boolean b){
		if(b){
		editor.putBoolean(IS_FIRSTTIME, false);
		editor.commit();}
	}

	public String getUserName(){
		return pref.getString(UserName, "");
	}
	public void setUserName(String name){
		editor.putString(UserName, name);
		editor.commit();
	}



}
