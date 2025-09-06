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
public class LevelQuestionServicesDecorator extends QuestionServicesDecorator{
    private int lvlid;

    public LevelQuestionServicesDecorator(BaseQuestionServices decorator, int lvlid) {
        super(decorator);
        this.lvlid = lvlid;
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND level_id=?";
        params.add(this.lvlid);
        return sql;
    }
    
    
}
