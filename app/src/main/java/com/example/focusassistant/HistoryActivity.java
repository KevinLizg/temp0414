package com.example.focusassistant;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ListView recordList;
    private HistoryDatabaseOpenHelper historyDatabaseOpenHelper;
    private List<record> recordsList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recordList=findViewById(R.id.recordsList);
        initRecords();

        RecordAdapter recordAdapter=new RecordAdapter(HistoryActivity.this,R.layout.record_item,recordsList);
        recordList.setAdapter(recordAdapter);

    }


    private void initRecords(){
        this.historyDatabaseOpenHelper=new HistoryDatabaseOpenHelper(this,"Records.db",null,1);
        SQLiteDatabase sqLiteDatabase=historyDatabaseOpenHelper.getWritableDatabase();

        Cursor cursor=sqLiteDatabase.query("Record",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String type=cursor.getString(cursor.getColumnIndex("type"));
                String time=cursor.getString(cursor.getColumnIndex("time"));
                String finished=cursor.getString(cursor.getColumnIndex("finished"));
                record newRecord=new record(type,time,finished);
                this.recordsList.add(newRecord);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }

}
