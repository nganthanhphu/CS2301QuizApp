/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntp.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Question {

    private int id;
    private String content;
    private String hint;
    private String image;
    private Category cate;
    private Level level;
    private List<Choice> choices = new ArrayList<>();

    private Question(Builder builder) {
        this.id = builder.id;
        this.content = builder.content;
        this.hint = builder.hint;
        this.image = builder.image;
        this.cate = builder.cate;
        this.level = builder.level;
    }

    public static class Builder {
        private int id;
        private String content;
        private String hint;
        private String image;
        private Category cate;
        private Level level;
        private List<Choice> choices = new ArrayList<>();
        
        public Builder(int id, String content, Category cate,Level level){
            this.id = id;
            this.content = content;
            this.cate = cate;
            this.level = level;
        }
        
        public Builder addHint(String hint){
            this.hint = hint;
            return this;
        }
        
        public Builder addImage(String image){
            this.image = image;
            return this;
        }
        
        public Builder addChoice(Choice c){
            this.choices.add(c);
            return this;
        }
        
        public Question build(){
            return new Question(this);
        }
    }

}
