package com.example.focusassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangePersonalInformationActivity extends AppCompatActivity {
    protected EditText myName;
    protected EditText myHobby;
    protected String nameInput;
    protected String hobbyInput;
    private Button savePersonalInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_personal_information);
        this.myName=findViewById(R.id.EditName);
        this.myHobby=findViewById(R.id.EditHobby);


        this.savePersonalInformation=findViewById(R.id.savePersonalInformation);
        savePersonalInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameInput=myName.getText().toString();
                hobbyInput=myHobby.getText().toString();

                SharedPreferences.Editor editor=getSharedPreferences("PersonalInformation",MODE_PRIVATE).edit();

                editor.putString("name",nameInput);
                editor.putString("hobby",hobbyInput);
                editor.apply();

                Intent intent=new Intent(ChangePersonalInformationActivity.this,PersonalInformationActivity.class);
                startActivity(intent);
            }
        });
    }

}
