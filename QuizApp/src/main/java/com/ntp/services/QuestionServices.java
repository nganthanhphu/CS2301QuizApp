/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntp.services;

import com.ntp.pojo.Choice;
import com.ntp.pojo.Question;
import com.ntp.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joon
 */
public class QuestionServices {

    public List<Question> getQuestions() throws SQLException {
        // Mở kết nối
        Connection conn = JdbcConnector.getInstance().connect();

        // Truy vấn
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM question");

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }

        return questions;
    }

    public List<Question> getQuestions(String kw) throws SQLException {
        // Mở kết nối
        Connection conn = JdbcConnector.getInstance().connect();

        // Truy vấn
        PreparedStatement stm = conn.prepareCall("SELECT * FROM question WHERE content like concat('%', ?, '%')");
        stm.setString(1, kw);

        ResultSet rs = stm.executeQuery();

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }

        return questions;
    }

    public List<Question> getQuestions(int num) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();

        PreparedStatement stm = conn.prepareCall("SELECT * FROM question ORDER BY rand() LIMIT ?");
        stm.setInt(1, num);

        ResultSet rs = stm.executeQuery();

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content"))
                    .addAllChoices(this.getChoiceByQuestionId(rs.getInt("id"))).build();
            questions.add(q);
        }

        return questions;
    }

    public List<Choice> getChoiceByQuestionId(int questionId) throws SQLException {
        // Mở kết nối
        Connection conn = JdbcConnector.getInstance().connect();

        // Truy vấn
        PreparedStatement stm = conn.prepareCall("SELECT * FROM choice WHERE question_id=?");
        stm.setInt(1, questionId);

        ResultSet rs = stm.executeQuery();

        List<Choice> choices = new ArrayList<>();
        while (rs.next()) {
            Choice c = new Choice(rs.getInt("id"), rs.getString("content"), rs.getBoolean("is_correct"));
            choices.add(c);
        }

        return choices;
    }

    public void addQuestion(Question q) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();

        conn.setAutoCommit(false);
        String sql = "INSERT INTO question(content, hint, image, category_id, level_id) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, q.getContent());
        stm.setString(2, q.getHint());
        stm.setString(3, q.getImage());
        stm.setInt(4, q.getCate().getId());
        stm.setInt(5, q.getLevel().getId());

        if (stm.executeUpdate() > 0) {
            int questionId = -1;
            ResultSet r = stm.getGeneratedKeys();
            if (r.next()) {
                questionId = r.getInt(1);
            }

            sql = "INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)";
            stm = conn.prepareCall(sql);

            for (var c : q.getChoices()) {
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isCorrect());
                stm.setInt(3, questionId);

                stm.executeUpdate();
            }

            conn.commit();
        } else {
            conn.rollback();
        }
    }
    
        
    public boolean deleteQuestion(int id) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();

        PreparedStatement stm = conn.prepareCall("DELETE FROM question WHERE id=?");
        stm.setInt(1, id);
        
       return stm.executeUpdate() > 0;
    }
}
