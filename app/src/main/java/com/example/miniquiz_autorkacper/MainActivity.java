package com.example.miniquiz_autorkacper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView bazapytan, wynik;
    private Button btnStart, btnA, btnB, btnC, btnReset;

    private String[][] questionsData = {
            {"Stolica Polski to:", "Kraków", "Warszawa", "Gdańsk", "Warszawa"},
            {"Ile bitów tworzy jeden bajt?", "4", "8", "16", "8"},
            {"Kolor nieba w dzień bezchmurny:", "Niebieski", "Zielony", "Czerwony", "Niebieski"},
            {"Który rok mamy obecnie?", "2024", "2025", "2026", "2026"},
            {"Zwierzę, które szczeka to:", "Kot", "Pies", "Chomik", "Pies"},
            {"Największy ocean to:", "Indyjski", "Atlantycki", "Spokojny", "Spokojny"},
            {"Język w tej aplikacji to:", "Java", "Python", "C++", "Java"}
    };

    private List<Integer> indeks = new ArrayList<>();
    private int postep = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bazapytan = findViewById(R.id.bazapytan);
        wynik = findViewById(R.id.wynik);
        btnStart = findViewById(R.id.btnStart);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnReset = findViewById(R.id.btnReset);


        btnStart.setOnClickListener(v -> startQuiz());
        btnReset.setOnClickListener(v -> resetQuiz());

        View.OnClickListener answerListener = v -> {
            Button clickedButton = (Button) v;
            checkAnswer(clickedButton.getText().toString());
        };

        btnA.setOnClickListener(answerListener);
        btnB.setOnClickListener(answerListener);
        btnC.setOnClickListener(answerListener);
    }

    private void startQuiz() {
        score = 0;
        postep = 0;
        wynik.setText("Wynik: 0");

        indeks.clear();
        for (int i = 0; i < questionsData.length; i++) indeks.add(i);
        Collections.shuffle(indeks);

        btnStart.setVisibility(View.GONE);
        updateUI(View.VISIBLE);
        showNextQuestion();
    }

    private void showNextQuestion() {
        if (postep < 5) {
            int qIdx = indeks.get(postep);
            bazapytan.setText(questionsData[qIdx][0]);
            btnA.setText(questionsData[qIdx][1]);
            btnB.setText(questionsData[qIdx][2]);
            btnC.setText(questionsData[qIdx][3]);
        } else {
            Toast.makeText(this, "Koniec quizu! Twój wynik: " + score + " / 5", Toast.LENGTH_LONG).show();
            updateUI(View.GONE);
            btnStart.setVisibility(View.VISIBLE);
        }
    }

    private void checkAnswer(String selected) {
        int qIdx = indeks.get(postep);
        if (selected.equals(questionsData[qIdx][4])) {
            score++;
            wynik.setText("Wynik: " + score);
        }
        postep++;
        showNextQuestion();
    }

    private void resetQuiz() {
        score = 0;
        postep = 0;
        wynik.setText("Wynik: 0");
        updateUI(View.GONE);
        btnStart.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Quiz zresetowany", Toast.LENGTH_SHORT).show();
    }

    private void updateUI(int visibility) {
        bazapytan.setVisibility(visibility);
        btnA.setVisibility(visibility);
        btnB.setVisibility(visibility);
        btnC.setVisibility(visibility);
    }
}