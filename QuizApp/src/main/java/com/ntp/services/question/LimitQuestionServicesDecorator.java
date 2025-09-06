/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntp.services.question;

import com.ntp.pojo.Choice;
import com.ntp.pojo.Question;
import com.ntp.services.choice.BaseChoiceServices;
import com.ntp.services.choice.QuestionIDChoiceServicesDecorator;
import com.ntp.utils.Configs;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Joon
 */
public class LimitQuestionServicesDecorator extends QuestionServicesDecorator{
    private int num;

    public LimitQuestionServicesDecorator(BaseQuestionServices decorator, int num) {
        super(decorator);
        this.num = num;
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " ORDER BY rand() LIMIT ?";
        params.add(num);
        return sql;
    }

    @Override
    public List<Question> list() throws SQLException {
        List<Question> question = super.list();
        
        for( var q: question){
            BaseChoiceServices s = new QuestionIDChoiceServicesDecorator(Configs.choiceServices, q.getId());
            List<Choice> choices = s.list();
            q.getChoices().addAll(choices);
        }
        return question;
    }
    
    
    
}
