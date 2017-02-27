package com.example.apache.shell_linux;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LinuxActivity extends AppCompatActivity {

    EditText edCommand;
    Button execute;
    TextView info;
    String command;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linux);

        edCommand = (EditText)findViewById(R.id.editCommand);
        execute = (Button)findViewById(R.id.executeButton);
        info = (TextView)findViewById(R.id.infoView);

        execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShellExecuter shellExecuter = new ShellExecuter();
                command = edCommand.getText().toString();
                if (command.equals("cls")){
                    info.setText("");
                }
                String output = shellExecuter.Executer(command);
                info.setText(info.getText() + "\n" + output);
                Log.d("Output", output);
            }
        });

    }
}
