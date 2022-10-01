package com.company.dao;

import com.company.models.Answer;
import com.company.models.Question;
import com.company.models.Quize;
import com.company.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DbButton {


    public List<Quize> showQuizes(){
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            String selectQuery = "SELECT id,name from quizes";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            List<Quize> quizesList = new ArrayList<>();
            while (resultSet.next()){
                Quize quize = new Quize();
                quize.setId(resultSet.getInt("id"));
                quize.setName(resultSet.getString("name"));
                quizesList.add(quize);
            }
            System.out.println(Arrays.deepToString(quizesList.toArray()));
            return quizesList;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public List<Question> showQuestion(List<Quize> quizes){
        Scanner in = new Scanner(System.in);
        System.out.println("\n"+"Enter topic you want :" +"\n");
        int userChooseTopic = in.nextInt();

        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            String selectQuery ="SELECT id,question,id_quizes FROM questions WHERE id_quizes = "+userChooseTopic;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            List<Question> questionList = new ArrayList<>();
            while (resultSet.next()){
                Question question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setQuestion(resultSet.getString("question"));
                question.setId_quizes(quizes.get(userChooseTopic -1));
                questionList.add(question);

            }
            System.out.println(quizes.get(userChooseTopic - 1));
            return questionList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    if (connection!=null){
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }

    }
    public List<Answer> showAnswer(List<Question> questions, int id_quizes){
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            String selectQuery = "SELECT answ.id,answ.answer,answ.is_correct,answ.id_questions" +
                    " from answers answ JOIN questions quest ON answ.id_questions = quest.id WHERE quest.id_quizes ="+ id_quizes;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            List <Answer>answerList = new ArrayList<>();

            while(resultSet.next()){
            Answer answer = new Answer();
            answer.setId(resultSet.getInt("id"));
            answer.setAnswer(resultSet.getString("answer"));
            answer.setIsCorrect(resultSet.getInt("is_correct"));
            answer.setId_question(questions.get(id_quizes-1));
            answerList.add(answer);
            }
            return answerList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public List<User> saveUserResult(){
        Scanner in = new Scanner(System.in);


        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            String selectQuery = "SELECT id,email FROM users";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            List<User> userList = new ArrayList<>();

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                userList.add(user);
            }
            return userList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(int countOfCorrectAnswer,String userEmail) {
        Connection connection = null;
        try {
            connection=DbConnection.getConnection();
            String UpdateQuery = "UPDATE users SET result=? WHERE email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(UpdateQuery);
            preparedStatement.setInt(1,countOfCorrectAnswer);
            preparedStatement.setString(2,userEmail);
            preparedStatement.executeUpdate();
            System.out.println("Result has been update");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
