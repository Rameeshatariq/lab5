package com.example.meesh.lab5;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.GetChars;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Spinner sp=(Spinner)findViewById(R.id.spinner);
        ArrayList<String> abc= new ArrayList<>();
        abc.add("Option1");
        abc.add("Option2");
        abc.add("Option3");
        ArrayAdapter<String> adp= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,abc);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adp);*/

        final ListView list =(ListView)findViewById(R.id.list);
        Button btn=(Button)findViewById(R.id.btn);
        final EditText GetValue = (EditText) findViewById(R.id.text);

        String [] data = new String[]{"ab","os","cd"};
        final ArrayList<String> mylist= new ArrayList<>();
        for(int i=0; i < data.length; i++) {
            mylist.add(data[i]);
        }
        final ArrayAdapter<String> aadap = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mylist);
        list.setAdapter(aadap);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mylist.add(GetValue.getText().toString());
                aadap.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Item Added!!", Toast.LENGTH_SHORT).show();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,list.getItemAtPosition(i).toString()
                        , Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "This is Snackbar Demo", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });


        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { //remove item
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);//delete se phele pochy
                alert.setMessage("Are you sure you want to delete this");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {//yes ka button
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mylist.remove(pos);
                        aadap.notifyDataSetChanged();//update remove karne k bd
                        Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //code here if kuch karwana ho tou
                    }
                });
                alert.show();
                return true;
            }
        });

    }
}
