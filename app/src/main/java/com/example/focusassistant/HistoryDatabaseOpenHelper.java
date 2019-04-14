package com.example.focusassistant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class HistoryDatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String CREATE_RECORD="create table Record ("+
                                                "id integer primary key autoincrement, "+
                                                "type String, "+
                                                "time String, "+
                                                "finished String )";
    private Context context;

    public HistoryDatabaseOpenHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_RECORD);
        //Toast.makeText(context, "Successfully Create Databases", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
