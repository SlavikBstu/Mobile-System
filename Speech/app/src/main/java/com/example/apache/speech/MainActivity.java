package com.example.apache.speech;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity implements
        TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private Button btnSpeak;
    private EditText txtText;
    private Spinner language;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this, this);

        btnSpeak = (Button) findViewById(R.id.btnSpeak);

        txtText = (EditText) findViewById(R.id.txtText);

        language = (Spinner)findViewById(R.id.languages);

        // button on click event
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                speakOut();
            }

        });
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(final int status) {

        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = language.getSelectedItem().toString();
                txtText.setText("");
                Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT).show();

                if (status == TextToSpeech.SUCCESS) {

                    int result;
                    switch(selected){
                        case "English": result = tts.setLanguage(Locale.US); break;
                        case "German": result = tts.setLanguage(Locale.GERMAN); break;
                        case "Chinese": result = tts.setLanguage(Locale.CHINESE); break;
                        default: result = 0;
                    }

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getApplicationContext(), "This Language is not supported", Toast.LENGTH_SHORT).show();
                        Log.e("TTS", "This Language is not supported");
                    } else {
                        btnSpeak.setEnabled(true);
                        speakOut();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Initilization Failed!", Toast.LENGTH_SHORT).show();
                    Log.e("TTS", "Initilization Failed!");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void speakOut() {

        String text = txtText.getText().toString();

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
