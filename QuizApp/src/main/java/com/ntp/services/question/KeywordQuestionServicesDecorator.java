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
public class KeywordQuestionServicesDecorator extends QuestionServicesDecorator{
    
    private String keyword;
    
    public KeywordQuestionServicesDecorator(BaseQuestionServices decorator, String kw) {
        super(decorator);
        this.keyword = kw;
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND content like concat('%', ?, '%')";
        params.add(keyword);
        return sql;
    }
    
}
