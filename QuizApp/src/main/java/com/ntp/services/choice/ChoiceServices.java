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
public class ChoiceServices extends BaseChoiceServices{

    @Override
    public String getSQL(List<Object> params) {
        return "SELECT * FROM choice WHERE 1=1";
    }
    
}
