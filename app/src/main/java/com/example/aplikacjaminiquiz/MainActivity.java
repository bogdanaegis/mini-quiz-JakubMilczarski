package com.example.aplikacjaminiquiz;

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

    private TextView tvQuestion, tvScore;
    private Button btnStart, btnReset, btnA, btnB, btnC;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);
        btnA = findViewById(R.id.btnOptA);
        btnB = findViewById(R.id.btnOptB);
        btnC = findViewById(R.id.btnOptC);


        prepareQuestions();

        btnStart.setOnClickListener(v -> startQuiz());
        btnReset.setOnClickListener(v -> resetQuiz());

        View.OnClickListener answerListener = v -> {
            Button b = (Button) v;
            checkAnswer(b.getText().toString());
        };

        btnA.setOnClickListener(answerListener);
        btnB.setOnClickListener(answerListener);
        btnC.setOnClickListener(answerListener);
    }

    private void prepareQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Question("Stolica Włoch to:", "Rzym", "Paryż", "Madryt", "Rzym"));
        questionList.add(new Question("2 + 2 * 2 to:", "8", "6", "4", "6"));
        questionList.add(new Question("Największa planeta:", "Ziemia", "Mars", "Jowisz", "Jowisz"));
        questionList.add(new Question("Symbol chemiczny złota:", "Au", "Ag", "Fe", "Au"));
        questionList.add(new Question("Rok bitwy pod Grunwaldem:", "1410", "1385", "1525", "1410"));

    }

    private void startQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        tvScore.setText("Wynik: 0");
        Collections.shuffle(questionList); // Losowanie kolejności

        btnStart.setVisibility(View.GONE);
        setQuizVisibility(View.VISIBLE);
        displayQuestion();
    }

    private void displayQuestion() {
        if (currentQuestionIndex < 5) {
            Question q = questionList.get(currentQuestionIndex);
            tvQuestion.setText(q.getText());
            btnA.setText(q.getOptA());
            btnB.setText(q.getOptB());
            btnC.setText(q.getOptC());
        } else {
            endQuiz();
        }
    }

    private void checkAnswer(String selected) {
        if (selected.equals(questionList.get(currentQuestionIndex).getCorrectAnswer())) {
            score++;
        }
        currentQuestionIndex++;
        tvScore.setText("Wynik: " + score);
        displayQuestion();
    }

    private void endQuiz() {
        Toast.makeText(this, "Koniec quizu! Twój wynik: " + score + " / 5", Toast.LENGTH_LONG).show();
        setQuizVisibility(View.GONE);
        btnStart.setVisibility(View.VISIBLE);
    }

    private void resetQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        tvScore.setText("Wynik: 0");
        setQuizVisibility(View.GONE);
        btnStart.setVisibility(View.VISIBLE);
    }

    private void setQuizVisibility(int visibility) {
        tvQuestion.setVisibility(visibility);
        btnA.setVisibility(visibility);
        btnB.setVisibility(visibility);
        btnC.setVisibility(visibility);
    }
}