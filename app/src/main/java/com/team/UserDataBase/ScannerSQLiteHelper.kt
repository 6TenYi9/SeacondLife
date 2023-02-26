package com.team.UserDataBase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ScannerSQLiteHelper (context: Context): SQLiteOpenHelper(context, DB_NAME,null,VERSION){
    companion object{
        private val VERSION=1
        private val DB_NAME="ScannerDataBase"
        private val TB_NAME="ObjectInfo"
        private val KEY_CODE="codigo"
        private val KEY_TYPE="tipo"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val SQLCommant="CREATE TABLE "+ TB_NAME +"("+ KEY_CODE +" TEXT NOT NULL,"+KEY_TYPE+" TEXT NOT NULL)"
        db!!.execSQL(
            SQLCommant
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS "+TB_NAME)
        onCreate(db)
    }

    fun addNewObject(code:String,type:String){
        val ObjectData= ContentValues()
        val dbwriter=this.writableDatabase

        ObjectData.put(KEY_CODE,code)
        ObjectData.put(KEY_TYPE,type)

        dbwriter.insert(TB_NAME,null,ObjectData)
        dbwriter.close()
    }

    @SuppressLint("Range")
    fun SearchObject(code:String): String {
        var type=""
        val dbreader=this.readableDatabase
        val rs=dbreader.rawQuery(
            "SELECT "+KEY_TYPE +" FROM "+TB_NAME +" WHERE "+KEY_CODE+"='"+code+"'",
            null
        )
        if(rs.moveToFirst()){
            type=rs.getString(rs.getColumnIndex(KEY_TYPE))
            rs.close()
        }
        dbreader.close()
        return type
    }


}