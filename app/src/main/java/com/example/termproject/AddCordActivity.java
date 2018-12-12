package com.example.termproject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.termproject.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import static com.example.termproject.db.DatabaseHelper.COL_ID;
import static com.example.termproject.db.DatabaseHelper.COL_IMAGE;
import static com.example.termproject.db.DatabaseHelper.COL_NAME;
import static com.example.termproject.db.DatabaseHelper.TABLE_NAME;

class AddCordActivity extends AppCompatActivity {

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cord);

        mHelper = new DatabaseHelper(this);
        mDb = mHelper.getWritableDatabase();

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doInsertCord();
            }
        });
    }

    private void doInsertCord() {
        EditText cordnameEditText = findViewById(R.id.cordname_edit_text);

        String title = cordnameEditText.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, title);
        mDb.insert(TABLE_NAME, null, cv);

        finish();
    }
}
