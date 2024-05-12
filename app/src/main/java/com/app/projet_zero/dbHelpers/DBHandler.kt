package com.app.projet_zero.dbHelpers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.app.projet_zero.model.PdfModel

class DBHandler
// creating a constructor for our database handler.
    (context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    // below method is for creating a database by running a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // on below line we are creating an sqlite query and we are
        // setting our column names along with their data types.
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE + " TEXT,"
                + SIZE + " TEXT,"
                + URI + " TEXT)")
        // at last we are calling a exec sql method to execute above sql query
        db.execSQL(query)
    }

    // this method is use to add new course to our sqlite database.
    fun addPDF(
        pdfName: String?,
        pdfSize: String?,
        uri: String?
    ) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TITLE, pdfName)
        values.put(SIZE, pdfSize)
        values.put(URI, uri)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun pdfList(): ArrayList<PdfModel> {
        // on below line we are creating a database for reading our database.
        val db = this.readableDatabase

        // on below line we are creating a cursor with query to read data from database.
        val cursorPDF: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY ID DESC", null)

        // on below line we are creating a new array list.
        val pdfModelArrayList: ArrayList<PdfModel> = ArrayList()

        // moving our cursor to first position.
        if (cursorPDF.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                pdfModelArrayList.add(
                    PdfModel(
                        cursorPDF.getString(1),
                        cursorPDF.getString(2),
                        cursorPDF.getString(3),
                    )
                )
            } while (cursorPDF.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor and returning our array list.
        cursorPDF.close()
        return pdfModelArrayList
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val DB_NAME = "Bibliotheque"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "pdf"
        private const val ID_COL = "id"
        private const val TITLE = "title"
        private const val SIZE = "size"
        private const val URI = "uri"
    }
}
