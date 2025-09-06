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
public abstract class ChoiceServicesDecorator extends BaseChoiceServices{
    protected BaseChoiceServices decorator;

    public ChoiceServicesDecorator(BaseChoiceServices decorator) {
        this.decorator = decorator;
    }
    
}
