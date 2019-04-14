package com.example.focusassistant;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.antonzorin.dottedprogressbar.DottedProgressBar;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView lvLeftMenu;
    private String[] lvs = {"个人信息", "历史记录", "会议笔记", "产品有关"};
    private ArrayAdapter arrayAdapter;
    private ImageView ivRunningMan;

    private Button start;
    private boolean isExit=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
//        mProgressBar = (DottedProgressBar) findViewById(R.id.progress);
//        mProgressBar.setClockWiseDots(true);
        //clock wise direction true/false
       // https://github.com/AntonZorin/dotted-progressbar
        start=findViewById(R.id.startButton);
        start.setOnClickListener(new PopupMenuListener());



    //dont dim the main content when open nevigation
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
//
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);



        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs);
        lvLeftMenu.setBackgroundColor(Color.BLACK);
        lvLeftMenu.setAdapter(arrayAdapter);
        lvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent=new Intent(MainActivity.this,PersonalInformationActivity.class);
                    startActivity(intent);
                }
                if(i==1){
                    Intent intent=new Intent(MainActivity.this,HistoryActivity.class);
                    startActivity(intent);
                }
                if(i==2){
                    Intent intent=new Intent(MainActivity.this,NoteListActivity.class);
                    startActivity(intent);
                }
                if(i==3){
                    Intent intent=new Intent(MainActivity.this,ProductionActivity.class);
                    startActivity(intent);
                }
            }
        });
        setSupportActionBar(toolbar);
    }
    //  make drawer layout clickable
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void findView(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        lvLeftMenu = (ListView) findViewById(R.id.drawerList);
        toolbar=findViewById(R.id.toorbar);
    }




    public class PopupMenuListener implements View.OnClickListener{
        protected int type=1;
        protected int focusTime=15*60;
        @Override
        public void onClick(View view) {
            AlertDialog.Builder aBuilder=new AlertDialog.Builder(MainActivity.this);
            aBuilder.setTitle("请选择自己的专注类型");
            final String[] types={"专注学习","专注运动","专注会议"};
            //
            aBuilder.setSingleChoiceItems(types, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(types[i].equals("专注学习")){
                        type=1;
                    }
                    if(types[i].equals("专注运动")){
                        type=2;
                    }
                    if(types[i].equals("专注会议")){
                        type=3;
                    }
                }
            });

            //
            aBuilder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this, "你取消了这一次专注", Toast.LENGTH_SHORT).show();
                }
            });
            //
            aBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //
                    if(type==1){
                        Toast.makeText(MainActivity.this, "你选择了专注学习", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder tBuilder=new AlertDialog.Builder(MainActivity.this);
                        tBuilder.setTitle("请选择自己的专注时间");
                        final String[] timePick={"15分钟","30分钟","45分钟","60分钟"};
                        tBuilder.setSingleChoiceItems(timePick, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(timePick[i].equals("15分钟")){
                                    focusTime=15*60;
                                }
                                if(timePick[i].equals("30分钟")){
                                    focusTime=30*60;
                                }
                                if(timePick[i].equals("45分钟")){
                                    focusTime=45*60;
                                }
                                if(timePick[i].equals("60分钟")){
                                    focusTime=60*60;
                                }
                            }
                        });

                        tBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        tBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Bundle bundle=new Bundle();
                                bundle.putInt("timeSet",focusTime);
                                Intent intent=new Intent(MainActivity.this,FocusOnStudyActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                        tBuilder.show();

                    }

                    //
                    if(type==2){
                        Toast.makeText(MainActivity.this, "你选择了专注运动", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder tBuilder=new AlertDialog.Builder(MainActivity.this);
                        tBuilder.setTitle("请选择自己的专注时间");
                        final String[] timePick={"15分钟","30分钟","45分钟","60分钟"};
                        tBuilder.setSingleChoiceItems(timePick, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(timePick[i].equals("15分钟")){
                                    focusTime=15*60;
                                }
                                if(timePick[i].equals("30分钟")){
                                    focusTime=30*60;
                                }
                                if(timePick[i].equals("45分钟")){
                                    focusTime=45*60;
                                }
                                if(timePick[i].equals("60分钟")){
                                    focusTime=60*60;
                                }
                            }
                        });
                        tBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        tBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Bundle bundle=new Bundle();
                                bundle.putInt("timeSet",focusTime);
                                Intent intent=new Intent(MainActivity.this,FocusOnSportsActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                        tBuilder.show();
                    }

                    //
                    if(type==3){
                        Toast.makeText(MainActivity.this, "你选择了专注会议", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,FocusOnMeetingActivity.class);
                        startActivity(intent);
                    }
                }
            });
            aBuilder.show();

        }

    }

    @Override
    public void onBackPressed() {
        if (isExit) {
            this.finish();

        } else {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            isExit= true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit= false;
                }
            }, 2000);
        }

    }
}
