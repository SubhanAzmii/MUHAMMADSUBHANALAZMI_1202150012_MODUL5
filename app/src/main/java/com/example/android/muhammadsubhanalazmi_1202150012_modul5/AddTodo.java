package com.example.android.muhammadsubhanalazmi_1202150012_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTodo extends AppCompatActivity {
    //daftar variable
    EditText Nname,Ndesc,Npriority;
    Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo);
        //inisialisasi pada komponen R.id


        Nname = (EditText) findViewById(R.id.name);
        Ndesc = (EditText) findViewById(R.id.desc);
        Npriority = (EditText) findViewById(R.id.priority);
        send = (Button) findViewById(R.id.sumbit);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onclick listener untuk mengirim data
                Intent intent=new Intent();
                intent.putExtra("nama",Nname.getText().toString());
                intent.putExtra("deskripsi",Ndesc.getText().toString());
                intent.putExtra("priority",Npriority.getText().toString());
                setResult(1,intent);
                finish();//finishing activity

            }
        });
    }
}
