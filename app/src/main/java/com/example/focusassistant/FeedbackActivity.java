package com.example.focusassistant;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        TextView textView=findViewById(R.id.feedback);

        String type=bundle.getString("type");
        boolean finished=bundle.getBoolean("finished");
        int duringTime=bundle.getInt("duringTime");

        int minute=duringTime/60;
        int second=duringTime%60;

        String finishExp=null;
        if(finished){
            finishExp="成功！已完成";
        }else{
            finishExp="失败！未完成";
        }

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("这次专注类型：\n");
        stringBuilder.append(type+"\n");
        stringBuilder.append("这次专注时间(秒)\n");
        stringBuilder.append(minute+"分钟"+second+"秒"+"\n");
        stringBuilder.append("是否完成？\n");
        stringBuilder.append(finished+"");

        textView.setText(stringBuilder.toString());


        HistoryDatabaseOpenHelper historyDatabaseOpenHelper=new HistoryDatabaseOpenHelper(this,"Records.db",null,1);
        SQLiteDatabase sqLiteDatabase=historyDatabaseOpenHelper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put("type",type);
        contentValues.put("time",minute+"分钟"+second+"秒");
        contentValues.put("finished",finishExp);
        sqLiteDatabase.insert("Record",null,contentValues);


        Button button=findViewById(R.id.returnFromFeedbackToHistory);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(FeedbackActivity.this,HistoryActivity.class);
                startActivity(intent1);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(FeedbackActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
