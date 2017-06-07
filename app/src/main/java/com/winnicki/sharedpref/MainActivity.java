package com.winnicki.sharedpref;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPlay, btnReset;
    TextView tvHighNumber, tvGenerated;

    SharedPreferences prefs;
    private static final String HIGH_NUMBER = "HIGH_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnReset = (Button)findViewById(R.id.btnReset);
        tvHighNumber = (TextView)findViewById(R.id.tvHighNumber);
        tvGenerated = (TextView)findViewById(R.id.tvGenerated);

        btnPlay.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        prefs = getPreferences(MODE_PRIVATE);
        int highNumber = prefs.getInt(HIGH_NUMBER, 0);
        tvHighNumber.setText(String.valueOf(highNumber));
        tvGenerated.setText("0");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay:
                Random r = new Random();
                int number = r.nextInt(1000);
                tvGenerated.setText(String.valueOf(number));
                if(number > prefs.getInt(HIGH_NUMBER, 0)) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt(HIGH_NUMBER, number);
                    editor.apply();
                    tvHighNumber.setText(String.valueOf(number));
                }
                break;
            case R.id.btnReset:
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt(HIGH_NUMBER, 0);
                editor.apply();
                tvHighNumber.setText("0");
                break;
        }
    }
}
