/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntp.services.question;

import java.util.List;

/**
 *
 * @author Joon
 */
public class CateQuestionServicesDecorator extends QuestionServicesDecorator{
    private int cateid;

    public CateQuestionServicesDecorator(BaseQuestionServices decorator, int cateid) {
        super(decorator);
        this.cateid = cateid;
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND category_id=?";
        params.add(cateid);
        return sql;
    }
    
}
