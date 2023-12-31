package com.example.myapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import com.example.myapplication.classes.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class UserDAO {
    private ManageDB manageDB;
    Context context;
    View view;
    User user;

    public UserDAO(Context context, View view){
        this.context = context;
        this.view = view;
        manageDB = new ManageDB(context);
    }
    public void insertUser(User myUser){
        try{
            SQLiteDatabase db =manageDB.getWritableDatabase();
            if(db != null){
                ContentValues values =new ContentValues();
                values.put("usu_document", myUser.getDocument());
                values.put("usu_user", myUser.getUser());
                values.put("usu_names", myUser.getNames());
                values.put("usu_last_names", myUser.getLastNames());
                values.put("usu_pass", myUser.getPass());
                long cod = db.insert("users",null,values);
                Snackbar.make(this.view,"The user register success:"+cod,Snackbar.LENGTH_LONG).show();
                }else{
                Snackbar.make(this.view,"The user not register ERROR:",Snackbar.LENGTH_LONG).show();
                }
            }catch (SQLException e){
                Log.i("ERROR",""+e);
            }
        }
    public ArrayList<User> getUserList() {
        SQLiteDatabase db = manageDB.getReadableDatabase();
        String query = "select * from users";
        ArrayList<User> userList = new ArrayList<User>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                user = new User();
                user.setDocument(cursor.getInt(0));
                user.setUser(cursor.getString(1));
                user.setNames(cursor.getString(2));
                user.setLastNames(cursor.getString(3));
                user.setPass(cursor.getString(4));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }
    public void updateUser(User myUser) {
        try {
            SQLiteDatabase db = manageDB.getWritableDatabase();
            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("usu_document", myUser.getDocument());
                values.put("usu_user", myUser.getUser());
                values.put("usu_names", myUser.getNames());
                values.put("usu_last_names", myUser.getLastNames());
                values.put("usu_pass", myUser.getPass());
                String whereClause = "usu_document = ?";
                String[] whereArgs = {String.valueOf(myUser.getDocument())};
                int rowsAffected = db.update("users", values, whereClause, whereArgs);
                Snackbar.make(this.view, "The user was updated successfully: " + rowsAffected, Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(this.view, "The user was not updated ERROR:", Snackbar.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            Log.i("ERROR", "" + e);
        }
    }
    public void deleteUser(User myUser) {
        try {
            SQLiteDatabase db = manageDB.getWritableDatabase();
            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("usu_status", "DELETED");
                String whereClause = "usu_document = ?";
                String[] whereArgs = {String.valueOf(myUser.getDocument())};
                int rowsAffected = db.update("users", values, whereClause, whereArgs);
                Snackbar.make(this.view, "The user was deleted successfully: " + rowsAffected, Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(this.view, "The user was not deleted ERROR:", Snackbar.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            Log.i("ERROR", "" + e);
        }
    }
    public User findUser(int document) {
        SQLiteDatabase db = manageDB.getReadableDatabase();
        String query = "select * from users where usu_document = ?";
        String[] selectionArgs = {String.valueOf(document)};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        User user = null;
        if (cursor.moveToFirst()) {
            user = new User();
            user.setDocument(cursor.getInt(0));
            user.setUser(cursor.getString(1));
            user.setNames(cursor.getString(2));
            user.setLastNames(cursor.getString(3));
            user.setPass(cursor.getString(4));
        }
        cursor.close();
        db.close();
        return user;
    }





}
