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
        private val KEY_NAME="nombre"
        private val KEY_TYPE="tipo" //0 plastico, 1 vidrio, 2 papel, 3 otro
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val SQLCommant="CREATE TABLE "+ TB_NAME +"("+ KEY_CODE +" TEXT NOT NULL,"+KEY_NAME+" TEXT NOT NULL,"+KEY_TYPE+" TEXT NOT NULL)"
        db!!.execSQL(
            SQLCommant
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS "+TB_NAME)
        onCreate(db)
    }

    fun addNewObject(code:String,name:String,type:String){
        val ObjectData= ContentValues()
        val dbwriter=this.writableDatabase

        ObjectData.put(KEY_CODE,code)
        ObjectData.put(KEY_NAME,name)
        ObjectData.put(KEY_TYPE,type)

        dbwriter.insert(TB_NAME,null,ObjectData)
        dbwriter.close()
    }

    @SuppressLint("Range")
    fun getName(code: String): String {
        var name=""
        val dbreader=this.readableDatabase
        val rs=dbreader.rawQuery(
            "SELECT "+KEY_NAME +" FROM "+TB_NAME +" WHERE "+KEY_CODE+"='"+code+"'",
            null
        )
        if(rs.moveToFirst()){
            name=rs.getString(rs.getColumnIndex(KEY_NAME))
            rs.close()
        }
        dbreader.close()
        return name
    }

    @SuppressLint("Range")
    fun getType(code:String): String {
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
        return when (type){
            "0" -> "AMARILLO"
            "1" -> "VERDE"
            "2" -> "AZUL"
            else -> "OTRO"
        }
    }

    fun addSampleData() {
        val values=ContentValues()
        val db=this.writableDatabase
        values.put(KEY_CODE,"8413402990503")
        values.put(KEY_NAME,"Agua San Joaquín")
        values.put(KEY_TYPE,"0")
        db.insert(TB_NAME,null,values)

        values.put(KEY_CODE,"8410128160319")
        values.put(KEY_NAME,"Agua Bezoya")
        values.put(KEY_TYPE,"0")
        db.insert(TB_NAME,null,values)

        values.put(KEY_CODE,"8423207210621")
        values.put(KEY_NAME,"Bicentury")
        values.put(KEY_TYPE,"0")
        db.insert(TB_NAME,null,values)
        db.close()
    }

    fun verifyItem(code: String):Boolean?{
        val dbreader=this.readableDatabase
        var exists:Boolean
        val rs=dbreader.rawQuery(
            "SELECT * FROM "+ TB_NAME +" WHERE "+ KEY_CODE +"='"+code+"'",
            null)
        if(rs.moveToFirst()){
            rs.close()
            exists=true
        }else{
            exists=false
        }
        dbreader.close()
        return exists
    }

}