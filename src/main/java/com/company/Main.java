package com.company;

import com.company.dao.DbButton;
import com.company.models.Answer;
import com.company.models.Question;
import com.company.models.Quize;
import com.company.models.User;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        DbButton dbButton = new DbButton();

        System.out.println("Enter Email :");
        String userEmail = in.next();
        List<User> users = dbButton.saveUserResult();

        boolean toContinue = false;
        for (int i = 0; i < users.size(); i++) {
            if (userEmail.equalsIgnoreCase(users.get(i).getEmail())) {
                i = users.size() + 1;
                toContinue = true;
            }
        }
        if (toContinue == false) {
            throw new RuntimeException("Email not found !");
        } else {
            //распечатать список тем
            System.out.println("\n");
            List<Quize> quizes = dbButton.showQuizes();

            List<Question> questions = dbButton.showQuestion(quizes);

            List<Answer> answers = dbButton.showAnswer(questions, questions.get(0).getId_quizes().getId());
            int countOfCorrectAnswer = 0;
            int countOfCorrectAnswerCopy = 0;
            int j = 0;
            for (int i = 0; i < questions.size(); i++) {
                countOfCorrectAnswerCopy = countOfCorrectAnswer;
                System.out.println("\t" + questions.get(i).getQuestion());
                int l = 1;
                System.out.print(answers.get(j).getId() + ")" + answers.get(j).getAnswer() + "\t");
                System.out.print(answers.get(j + l).getId() + ")" + answers.get(j + l).getAnswer() + "\t");
                l++;
                System.out.print(answers.get(j + l).getId() + ")" + answers.get(j + l).getAnswer() + "\t");
                l++;
                System.out.println(answers.get(j + l).getId() + ")" + answers.get(j + l).getAnswer());

                int userAnswerNum = in.nextInt();
                countOfCorrectAnswer += answers.get(userAnswerNum - 1).getIsCorrect();
                if (countOfCorrectAnswer == countOfCorrectAnswerCopy) {
                    System.out.println("false");
                } else {
                    System.out.println("true");
                }

            }
            System.out.println("you answered : " + countOfCorrectAnswer + " out of " + questions.size());

            double coefCorrect = (double) countOfCorrectAnswer * 100 / (double) questions.size();
            System.out.println("percent of correct answer = " + coefCorrect + "%");

            dbButton.saveUser(countOfCorrectAnswer,userEmail);

        }

    }
}
