package com.example.mcnotes;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.icu.text.CaseMap;
import android.widget.Toast;

import androidx.annotation.Nullable;

class DatabasHelper extends SQLiteOpenHelper {

        private Context context;
        private static final String dbName = "Notebookdb";
        private static final int dbVer=1;
        private static final String TableName="userNotes";
        private static final String columnID="id";
        private static final String ColumnTitle="title";
        private static final String ColumnContent="contnet";

        public DatabasHelper(@Nullable Context context){

            super (context, dbName, null, dbVer );
            this.context= context;
        }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String query = "CREATE TABLE" + TableName +
                       "(" + columnID+ "INTEGER PRIMARY KEY AUTOINCREMENT,"  +
                       ColumnTitle+ "TEXT," +
                       ColumnContent+"TEXT;";

       db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TableName );
        onCreate(db);
    }


    void addNote(String title, String content){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues ContVal= new ContentValues();

            ContVal.put(ColumnTitle, title);
            ContVal.put(ColumnContent,content);
           long result=  db.insert(TableName,null,ContVal);
           if(result==-1){
               Toast.makeText(context, "adding to database failed", Toast.LENGTH_SHORT).show();
           }
           else {
               Toast.makeText(context, "Added successfully in database", Toast.LENGTH_SHORT).show();
           }

    }
}