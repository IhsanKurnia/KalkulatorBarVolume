package com.ihsan_kurnia.kalkulatorbarvolume;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editWidth, editHeight, editLenght;
    private Button btn_calculate;
    private TextView tvResult;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Inisialisasi
        editWidth = findViewById(R.id.edt_width);
        editHeight = findViewById(R.id.edt_height);
        editLenght = findViewById(R.id.edt_lenght);
        btn_calculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);


        btn_calculate.setOnClickListener(this);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }

    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_calculate) {
            String inputLenght = editLenght.getText().toString().trim();
            String inputWidth = editWidth.getText().toString().trim();
            String inputHeight = editHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;


            if (TextUtils.isEmpty(inputLenght)) {
                isEmptyFields = true;
                editLenght.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true;
                editWidth.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true;
                editHeight.setError("Field ini tidak boleh kosong");
            }

            Double lenght = toDouble(inputLenght);
            Double width = toDouble(inputWidth);
            Double height = toDouble(inputHeight);

            if (lenght == null) {
                isInvalidDouble = true;
                editLenght.setError("Field ini harus nomor yang valid");
            }
            if (width == null) {
                isInvalidDouble = true;
                editWidth.setError("Field ini harus nomor yang valid");
            }
            if (height == null) {
                isInvalidDouble = true;
                editHeight.setError("Field ini harus nomor yang valid");
            }


            if (!isEmptyFields && !isInvalidDouble) {
                double volume = lenght * width * height;
                tvResult.setText(String.valueOf(volume));
            }

        }

    }

    private Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }


}