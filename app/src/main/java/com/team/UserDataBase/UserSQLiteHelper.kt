package com.team.UserDataBase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AlertDialog

class UserSQLiteHelper (context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    companion object{
        private val DATABASE_VERSION=3
        private val DATABASE_NAME="MasterDataBase"
        private val TABLE_NAME="UserDataBase"
        private val KEY_USERNAME="username"
        private val KEY_EMAIL="email"
        private val KEY_PSWD="password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val SQLCommant="CREATE TABLE "+TABLE_NAME+"("+KEY_USERNAME+" TEXT NOT NULL,"+KEY_EMAIL+" TEXT NOT NULL,"+KEY_PSWD+" TEXT NOT NULL,UserId INTEGER PRIMARY KEY AUTOINCREMENT)"
        db!!.execSQL(
            SQLCommant
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME)
        onCreate(db)
    }

    fun addNewUser(username:String,email:String,password:String){
        val UsersData=ContentValues()
        val dbwriter=this.writableDatabase

        UsersData.put(KEY_USERNAME,username)
        UsersData.put(KEY_EMAIL,email)
        UsersData.put(KEY_PSWD,password)

        dbwriter.insert(TABLE_NAME,null,UsersData)
        dbwriter.close()
    }

    fun addMasterUser (){
        val values=ContentValues()
        val dbwriter=this.writableDatabase

        values.put(KEY_USERNAME,"admin")
        values.put(KEY_EMAIL,"admin@gmail.com")
        values.put(KEY_PSWD,"admin")

        dbwriter.insert(TABLE_NAME,null,values)
        dbwriter.close()
    }

    @SuppressLint("Range")
    fun VerifyUser(username:String, password:String):Boolean?{
        val dbreader=this.readableDatabase
        var exist:Boolean
        val rs=dbreader.rawQuery(
            "SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_USERNAME+"='"+username+"' AND "+KEY_PSWD+"='"+password+"'",
            null)
        if(rs.moveToFirst()){
            val name=rs.getString(rs.getColumnIndex(KEY_USERNAME))
            rs.close()
            exist=true
        }else{
            exist=false
        }
        return exist
    }
}