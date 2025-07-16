/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ntp.quizapp;

import com.ntp.pojo.Category;
import com.ntp.pojo.Choice;
import com.ntp.pojo.Level;
import com.ntp.pojo.Question;
import com.ntp.services.CategoryServices;
import com.ntp.services.LevelServices;
import com.ntp.services.QuestionUpdateServices;
import com.ntp.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuestionsController implements Initializable {

    @FXML
    private ComboBox<Category> cbCates;
    @FXML
    private ComboBox<Level> cbLevels;

    @FXML
    private VBox VboxChoices;

    @FXML
    private TextArea txtContent;

    @FXML
    private ToggleGroup toggleChoices;

    private static final CategoryServices cateServices = new CategoryServices();
    private static final LevelServices levelServices = new LevelServices();
    private static final QuestionUpdateServices qUServices = new QuestionUpdateServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(cateServices.getCates()));
            this.cbLevels.setItems(FXCollections.observableList(levelServices.getLevels()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void handleAddChoice(ActionEvent event) {
        HBox h = new HBox();
        h.getStyleClass().add("Main");
        RadioButton rdo = new RadioButton();
        rdo.setToggleGroup(toggleChoices);
        TextField tf = new TextField();
        h.getChildren().addAll(rdo, tf);
        this.VboxChoices.getChildren().addAll(h);
    }

    public void handleAddQuestion(ActionEvent event) {
        try {
            Question.Builder qb = new Question.Builder(this.txtContent.getText(),
                    this.cbCates.getSelectionModel().getSelectedItem(),
                    this.cbLevels.getSelectionModel().getSelectedItem());
            for (var c : this.VboxChoices.getChildren()) {
                HBox h = (HBox) c;
                qb.addChoice(new Choice(((TextField) h.getChildren().get(1)).getText(),
                        ((RadioButton) h.getChildren().get(0)).isSelected()));
            }

            qUServices.addQuestion(qb.build());

            MyAlert.getInstance().showMsg("Thêm câu hỏi thành công!");
        } catch (SQLException ex) {
            MyAlert.getInstance().showMsg("Thêm câu hỏi thất baị, lý do: " + ex.getMessage());
        } catch (Exception ex) {
            MyAlert.getInstance().showMsg("Dữ liệu bị lỗi!");
        }
    }
}
