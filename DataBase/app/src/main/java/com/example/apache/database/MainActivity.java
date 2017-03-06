package com.example.apache.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edInfo;
    Button Insert, Select;
    TextView Info;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(getApplicationContext(), "DataBase", null, 1);

        edInfo = (EditText) findViewById(R.id.edInfo);
        Insert = (Button) findViewById(R.id.insertButton);
        Select = (Button) findViewById(R.id.selectButton);
        Info = (TextView) findViewById(R.id.Info);
    }

    public void click(View view){
        switch (view.getId()) {
            case R.id.insertButton:
                dbHelper.insert(edInfo.getText().toString());
                break;
            case R.id.selectButton:
                Info.setText("");
                dbHelper.list(Info);
                break;
        }
    }
}
