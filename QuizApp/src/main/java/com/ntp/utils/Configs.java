/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntp.utils;

import com.ntp.services.CategoryServices;
import com.ntp.services.LevelServices;
import com.ntp.services.choice.BaseChoiceServices;
import com.ntp.services.choice.ChoiceServices;
import com.ntp.services.question.BaseQuestionServices;
import com.ntp.services.question.QuestionServices;
import com.ntp.services.question.UpdateQuestionServices;

/**
 *
 * @author Joon
 */
public class Configs {

    public static final CategoryServices cateServices = new CategoryServices();
    public static final LevelServices levelServices = new LevelServices();
    public static final UpdateQuestionServices uQServices = new UpdateQuestionServices();
    public static final BaseQuestionServices qServices = new QuestionServices();
    public static final BaseChoiceServices choiceServices = new ChoiceServices();
    
    public static final int NUM_QUES = 10;
    public static final double[] RATES = {0.4, 0.4 ,0.2};
}
