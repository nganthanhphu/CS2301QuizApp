/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.ntp.utils.theme;

import javafx.scene.Scene;

/**
 *
 * @author admin
 */
public enum Theme {
    DEFAULT {
        @Override
        public void updateTheme(Scene scene) {
            ThemeManager.setTm(new DefaultThemeFactory());
            ThemeManager.updateTheme(scene);
        }
    },
    DARK {
        @Override
        public void updateTheme(Scene scene) {
            ThemeManager.setTm(new DarkThemeFactory());
            ThemeManager.updateTheme(scene);
        }
    },
    LIGHT {
        @Override
        public void updateTheme(Scene scene) {
            ThemeManager.setTm(new LightThemeFactory());
            ThemeManager.updateTheme(scene);
        }
    };

    public abstract void updateTheme(Scene scene);
}
