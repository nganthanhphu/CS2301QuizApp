/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntp.services.exam;

import com.ntp.pojo.Question;
import com.ntp.services.question.BaseQuestionServices;
import com.ntp.services.question.LimitQuestionServicesDecorator;
import com.ntp.utils.Configs;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class SpecificExam extends ExamStrategy{
    private int num;

    public SpecificExam(int num) {
        this.num = num;
    }

    @Override
    public List<Question> getQuestion() throws SQLException {
        BaseQuestionServices s = new LimitQuestionServicesDecorator(Configs.qServices, num);
        return s.list();
    }
    
}
