/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntp.utils.theme;

import com.ntp.quizapp.App;
import javafx.scene.Scene;

/**
 *
 * @author admin
 */
public class ThemeManager {

    private static ThemeFactory tm = new DefaultThemeFactory();

    /**
     * @param aTm the tm to set
     */
    public static void setTm(ThemeFactory aTm) {
        tm = aTm;
    }

    public static void updateTheme(Scene scene) {
        scene.getRoot().getStylesheets().clear();
        scene.getRoot().getStylesheets().add(tm.getStyleSheet());
    }
}
