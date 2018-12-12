package com.example.termproject;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.termproject.Model.Corditem;
import com.example.termproject.adapter.Cordlistadapter;
import com.example.termproject.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import static com.example.termproject.db.DatabaseHelper.COL_ID;
import static com.example.termproject.db.DatabaseHelper.COL_IMAGE;
import static com.example.termproject.db.DatabaseHelper.COL_NAME;
import static com.example.termproject.db.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<Corditem> mCordItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(MainActivity.this);
        mDb = mHelper.getWritableDatabase();

        Button addCordButton = findViewById(R.id.add_cord_button);
        addCordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCordActivity.class);
                startActivity(intent);
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadCordData();
        setupListView();
    }

    private void loadCordData() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

        mCordItemList = new ArrayList<>();
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));
            String name = c.getString(c.getColumnIndex(COL_NAME));
            String cord = c.getString(c.getColumnIndex(TABLE_NAME));
            String image = c.getString(c.getColumnIndex(COL_IMAGE));

            Corditem item = new Corditem(id,cord, name, image);
            mCordItemList.add(item);
        }
        c.close();
    }

    private void setupListView() {
        Cordlistadapter adapter = new Cordlistadapter(
                MainActivity.this,
                R.layout.item_cord,
                mCordItemList
        );
        ListView lv = findViewById(R.id.result_list_view);
        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Corditem item = mPhoneItemList.get(position);
//
//                Toast t = Toast.makeText(MainActivity.this, item.number, Toast.LENGTH_SHORT);
//                t.show();
//
//                Intent intent = new Intent(
//                        Intent.ACTION_DIAL,
//                        Uri.parse("tel:" + item.number)
//                );
//                startActivity(intent);
//
//            }
//        });
//        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
//                String[] items = new String[]{
//                        "Edit",
//                        "Delete"
//                };
//
//                new AlertDialog.Builder(MainActivity.this)
//                        .setItems(items, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                final PhoneItem phoneItem = mPhoneItemList.get(position);
//
//                                switch (i) {
//                                    case 0: // Edit
//                                        Intent intent = new Intent(MainActivity.this, EditPhoneItemActivity.class);
//                                        intent.putExtra("title", phoneItem.title);
//                                        intent.putExtra("number", phoneItem.number);
//                                        intent.putExtra("id", phoneItem._id);
//                                        startActivity(intent);
//                                        break;
//                                    case 1: // Delete
//                                        new AlertDialog.Builder(MainActivity.this)
//                                                .setMessage("ต้องการลบข้อมูลเบอร์โทรนี้ ใช่หรือไม่")
//                                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                                        mDb.delete(
//                                                                TABLE_NAME,
//                                                                COL_ID + " = ?",
//                                                                new String[]{String.valueOf(phoneItem._id)}
//                                                        );
//                                                        loadPhoneData();
//                                                        setupListView();
//                                                    }
//                                                })
//                                                .setNegativeButton("No", null)
//                                                .show();
//                                        break;
//                                }
//                            }
//                        })
//                        .show();
//
//                return true;
//            }
//        });
    }
}
