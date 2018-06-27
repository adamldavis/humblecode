package com.humblecode.humblecode.model;

import java.util.ArrayList;
import java.util.List;

/** Test user takes to complete the course. Questions are picked randomly out of given list of questions. */
public class Test {

    final List<Question> questions = new ArrayList<>();

    int numberOfQuestions;

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions.clear();
        this.questions.addAll(questions);
    }
}
