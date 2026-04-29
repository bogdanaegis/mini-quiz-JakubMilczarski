package com.example.aplikacjaminiquiz;

public class Question {
    private String text;
    private String optA, optB, optC;
    private String correctAnswer;

    public Question(String text, String optA, String optB, String optC, String correctAnswer) {
        this.text = text;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.correctAnswer = correctAnswer;
    }


    public String getText() { return text; }
    public String getOptA() { return optA; }
    public String getOptB() { return optB; }
    public String getOptC() { return optC; }
    public String getCorrectAnswer() { return correctAnswer; }
}
