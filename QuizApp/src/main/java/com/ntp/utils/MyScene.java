/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntp.utils;

import com.ntp.quizapp.App;
import com.ntp.utils.theme.ThemeManager;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class MyScene {

    private static MyScene instance;
    private static Scene scene;
    private final Stage stage;

    private MyScene() {
        stage = new Stage();
        stage.setTitle("QuizApp");
    }

    public static MyScene getInstance() {
        if (MyScene.instance == null) {
            MyScene.instance = new MyScene();
        }
        return MyScene.instance;
    }

    public void showStage(String fxml) throws IOException {
        if (scene == null) {
            scene = new Scene(new FXMLLoader(App.class.getResource(fxml)).load());
        } else {
            scene.setRoot(new FXMLLoader(App.class.getResource(fxml)).load());
        }
        
        ThemeManager.updateTheme(scene);
        this.stage.setScene(scene);
        this.stage.show();
    }
}
