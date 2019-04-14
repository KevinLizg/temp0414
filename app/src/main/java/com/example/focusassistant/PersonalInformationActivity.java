package com.example.focusassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PersonalInformationActivity extends AppCompatActivity {
    private TextView name;
    private TextView hobby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        this.name=findViewById(R.id.name);
        this.hobby=findViewById(R.id.hobby);
        final Button changeInformation=findViewById(R.id.changePersonalInformation);

        changeInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PersonalInformationActivity.this,ChangePersonalInformationActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPreferences=getSharedPreferences("PersonalInformation",MODE_PRIVATE);
        String myName=sharedPreferences.getString("name","暂无相关数据");
        String myHobby=sharedPreferences.getString("hobby","暂无相关数据");

        name.setText(myName);
        hobby.setText(myHobby);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(PersonalInformationActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
