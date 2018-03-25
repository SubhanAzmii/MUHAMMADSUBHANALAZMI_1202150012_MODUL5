package com.example.android.muhammadsubhanalazmi_1202150012_modul5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    //list variable
    private LinkedList<todo> Mlist = new LinkedList<>();
    private RecyclerView MrecyclerView;
    private RecycleAdapter Madapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cek database
        databaseHelper = new DatabaseHelper(this);

        //inisialisasi untuk button 3 titik
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set layout recycleview
        RecViewSet();

        //listener untuk button add item
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTodo.class);
        //startActivity(intent);
               startActivityForResult(intent, 1);
            }
        });

//        todo todo1 = new todo();
//        todo1.setNama("Makan");
//        todo1.setDeskripsi("Nasi");
//        todo1.setPriority("3");
//        databaseHelper.tambahTodo(todo1);
//        Mlist = databaseHelper.bacatodo();
//        for (int i = 0; i < Mlist.size(); i++) {
//            Log.d("DB", Mlist.get(i).getNama());
//        }
//
//        RecViewSet();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.custom_color);
            dialog.setTitle("Change");
            dialog.setCancelable(true);

            //inisialisasi radio button
            final RadioButton rdRed = (RadioButton) dialog.findViewById(R.id.red);
            final RadioButton rdblue = (RadioButton) dialog.findViewById(R.id.blue);
            final RadioButton rdGreen = (RadioButton) dialog.findViewById(R.id.green);
            final Button cancel = (Button) dialog.findViewById(R.id.cancel);

            rdRed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MrecyclerView.setBackgroundResource(R.color.red); //set background
                    dialog.cancel();
                }
            });
            rdblue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MrecyclerView.setBackgroundResource(R.color.blue);
                    dialog.cancel();
                }
            });
            rdGreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MrecyclerView.setBackgroundResource(R.color.green);
                    dialog.cancel();
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }
    public void RecViewSet(){
        Mlist = databaseHelper.bacatodo();
        // Get a handle to the RecyclerView.
        MrecyclerView = (RecyclerView) findViewById(R.id.RecView);
        // Create an adapter and supply the data to be displayed.
        Madapter = new RecycleAdapter(this, Mlist);
        // Connect the adapter with the RecyclerView.
        MrecyclerView.setAdapter(Madapter);
        // Give the RecyclerView a default layout manager.
        MrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //attach untuk swipe
        ItemTouchHelper.Callback callback = new SwipeHelper(Madapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(MrecyclerView);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1){
            //log untuk check data yang barusaja di input
            Log.d("new name : ",data.getStringExtra("nama"));
            Log.d("new description : ",data.getStringExtra("deskripsi"));
            Log.d("new priority : ",data.getStringExtra("priority"));
            databaseHelper.tambahTodo(new todo(data.getStringExtra("nama"), data.getStringExtra("deskripsi"),
                    data.getStringExtra("priority")));
        }
        //set data pada Recycleview
        RecViewSet();
        Madapter.notifyDataSetChanged();
    }
}
