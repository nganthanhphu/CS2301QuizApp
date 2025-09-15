/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntp.services.exam;

import com.ntp.pojo.Question;
import com.ntp.services.question.BaseQuestionServices;
import com.ntp.services.question.LevelQuestionServicesDecorator;
import com.ntp.services.question.LimitQuestionServicesDecorator;
import com.ntp.utils.Configs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class FixedStrategy extends ExamStrategy{

    @Override 
    public List<Question> getQuestion() throws SQLException {
        List<Question> questions = new ArrayList<>();
        
        for(int i =0;i<Configs.RATES.length;i++){
            BaseQuestionServices s = new LimitQuestionServicesDecorator(new LevelQuestionServicesDecorator(Configs.qServices, i+1), (int)(Configs.RATES[i]*Configs.NUM_QUES));
            questions.addAll(s.list());
        }
        return questions;
    }
    
}
