package com.company.models;

public class Answer {
    int id;
    String answer;
    int IsCorrect;
    Question id_question;

    public Answer() {
    }
    public Answer(int id, String answer, int isCorrect, Question id_question) {
        this.id = id;
        this.answer = answer;
        IsCorrect = isCorrect;
        this.id_question = id_question;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getIsCorrect() {
        return IsCorrect;
    }

    public void setIsCorrect(int isCorrect) {
        IsCorrect = isCorrect;
    }

    public Question getId_question() {
        return id_question;
    }

    public void setId_question(Question id_question) {
        this.id_question = id_question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                ", IsCorrect=" + IsCorrect +
                ", id_question=" + id_question +
                '}';
    }
}
