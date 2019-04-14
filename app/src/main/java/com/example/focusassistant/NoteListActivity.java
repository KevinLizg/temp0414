package com.example.focusassistant;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NoteListActivity extends AppCompatActivity {
    //private TextView notes;
    private ListView notesLists;
    private NoteDatabaseOpenHelper noteDatabaseOpenHelper;
    private List<Note> noteList=new ArrayList<>();
    //private String notesDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //notesDetails=null;
        //notes=findViewById(R.id.notes);
        this.notesLists=findViewById(R.id.notesLists);

        initNote();

        NoteAdapter noteAdapter=new NoteAdapter(NoteListActivity.this,R.layout.note_item,noteList);
        notesLists.setAdapter(noteAdapter);
        //this.notes.setText(this.notesDetails);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText=new EditText(NoteListActivity.this);
                AlertDialog.Builder inputDialog=new AlertDialog.Builder(NoteListActivity.this);
                inputDialog.setTitle("请输入该笔记的标题").setView(editText);
                inputDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String title=editText.getText().toString();
                                Bundle bundle=new Bundle();
                                bundle.putString("title",title);
                                Intent intent=new Intent(NoteListActivity.this,WriteNoteActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                Toast.makeText(NoteListActivity.this, "写下自己的笔记", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(NoteListActivity.this,MainActivity.class);
        startActivity(intent);
    }


    private void initNote(){
        this.noteDatabaseOpenHelper=new NoteDatabaseOpenHelper(this,"Notes.db",null,2);
        SQLiteDatabase sqLiteDatabase=noteDatabaseOpenHelper.getWritableDatabase();

        Cursor cursor=sqLiteDatabase.query("writeNote",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                String title=cursor.getString(cursor.getColumnIndex("title"));
                Note newNote=new Note(title);
                this.noteList.add(newNote);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }
}
