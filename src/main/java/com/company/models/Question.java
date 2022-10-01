package com.company.models;

public class Question {
    int id;
    String question;
    Quize id_quizes;

    public Question() {
    }

    public Question(int id, String question, Quize id_quizes) {
        this.id = id;
        this.question = question;
        this.id_quizes = id_quizes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Quize getId_quizes() {
        return id_quizes;
    }

    public void setId_quizes(Quize id_quizes) {
        this.id_quizes = id_quizes;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", id_quizes=" + id_quizes +
                '}';
    }
}
