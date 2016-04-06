package com.example.english.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Button reset;
    public Button mode;
    public CustomView cstmView;
    public TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.txt1);
        cstmView = (CustomView) findViewById(R.id.custView);

        cstmView.txt = txt;

        reset = (Button) findViewById(R.id.button1);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cstmView.reset();
            }
        });

        mode = (Button) findViewById(R.id.button2);
        mode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cstmView.uncover = !cstmView.uncover;
                if (cstmView.uncover) {
                    mode.setText("Uncover");
                } else {
                    mode.setText("Marking");
                }
            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
