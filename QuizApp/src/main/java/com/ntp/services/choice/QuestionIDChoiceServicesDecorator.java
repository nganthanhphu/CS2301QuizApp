/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntp.services.choice;

import java.util.List;

/**
 *
 * @author Joon
 */
public class QuestionIDChoiceServicesDecorator extends ChoiceServicesDecorator{
    private int qid;

    public QuestionIDChoiceServicesDecorator(BaseChoiceServices decorator, int qid) {
        super(decorator);
        this.qid = qid;
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND question_id=?";
        params.add(this.qid);
        return sql;
    }
}
