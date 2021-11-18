package com.example.guessword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String[] question = new String[]{
            "Sunday", "Monday", "Tuesday"
    };
    String que;
    Random random;
    TextView txtCorrectAnswer, txtRightAnswer, txtQuestionContainer,
            txtCauHoi, txtDiem, txtCauDung, txtCauSai, txtTime;

    EditText edYourAnswer;
    Button btnCheck, btnShow, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //Ẩn tên ứng dụng
//        getSupportActionBar().hide();
        //Ẩn thanh trạng thái
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        txtCorrectAnswer = findViewById(R.id.txtCorrectAnswer);
        txtQuestionContainer = findViewById(R.id.txtQuestionContainer);
        txtRightAnswer = findViewById(R.id.txtRightAnswer);
        txtCauHoi = findViewById(R.id.txtCauHoi);
        txtDiem = findViewById(R.id.txtDiem);
        txtCauDung = findViewById(R.id.txtSLCauDung);
        txtCauSai = findViewById(R.id.txtCauSai);
        txtTime = findViewById(R.id.txtTime);
        edYourAnswer = findViewById(R.id.edYourAnswer);
        btnCheck = findViewById(R.id.btnCheck);
        btnNext = findViewById(R.id.btnNext);
        btnShow = findViewById(R.id.btnShow);

        //random
        random = new Random();
        que = question[random.nextInt(question.length)];
        txtQuestionContainer.setText(mixWords(que));

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edYourAnswer.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa điền đáp án !!!", Toast.LENGTH_SHORT).show();
                }else if (edYourAnswer.getText().toString().equalsIgnoreCase(que)) {
                    Dialog dialog = new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.correct_dialog);
                    Button hide = dialog.findViewById(R.id.btnContinue);
                    dialog.show();
                    hide.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    edYourAnswer.setText("");
                    que = question[random.nextInt(question.length)];
                    txtQuestionContainer.setText(mixWords(que));
                    //ẩn các view
                    txtCorrectAnswer.setVisibility(View.INVISIBLE);
                    txtRightAnswer.setVisibility(View.INVISIBLE);
                } else {
                    Dialog dialog = new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.incorect_dialog);
                    Button hide = dialog.findViewById(R.id.btnContinue);
                    dialog.show();
                    hide.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    edYourAnswer.setText("");
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                que = question[random.nextInt(question.length)];
                txtQuestionContainer.setText(mixWords(que));

                edYourAnswer.setText("");
                txtCorrectAnswer.setVisibility(View.INVISIBLE);
                txtRightAnswer.setVisibility(View.INVISIBLE);
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCorrectAnswer.setVisibility(View.VISIBLE);
                txtRightAnswer.setVisibility(View.VISIBLE);
                txtRightAnswer.setText(que);
            }
        });
    }

    private String mixWords(String word) {
        List<String> words = Arrays.asList(word.split(""));
        Collections.shuffle(words);
        String mixed = "";
        for (String i : words) {
            mixed += i;
        }
        return mixed;
    }
}