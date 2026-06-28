package com.ice.radioquiz;

public class Answer {
    String number;
    String question;
    String a;
    String b;
    String c;
    String d;
    int correct;
    String figure;

    public Answer(String s1, String s2, String s3, String s4, String s5, String s6, int s7, String s8) {
        number = s1;
        question = s2;
        a = s3;
        b = s4;
        c = s5;
        d = s6;
        correct = s7;
        figure  = s8;
    }
    public Answer() {
        this.number = null;
        this.question = null;
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.correct = 0;
        this.figure = null;
    }


    public void clear() {
        number = null;
        question = null;
        a = null;
        b = null;
        c = null;
        d = null;
        correct = 0;
        figure = null;
    }
}

