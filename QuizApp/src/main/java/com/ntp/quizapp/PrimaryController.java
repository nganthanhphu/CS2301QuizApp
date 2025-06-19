package com.ntp.quizapp;

import com.ntp.utils.MyAlert;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {

    public void handleQuestionManagementButton(ActionEvent e) throws IOException {
        Scene scene = new Scene(new FXMLLoader(App.class.getResource("questions.fxml")).load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quiz APP");
        stage.show();
    }

    public void handlePraticeButton(ActionEvent e) {
        MyAlert.getInstance().showMsg("Coming soon!");
    }

    public void handleTestButton(ActionEvent e) {
        MyAlert.getInstance().showMsg("Coming soon!");
    }
}
