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
import com.ntp.services.question.BaseQuestionServices;
import com.ntp.services.question.CateQuestionServicesDecorator;
import com.ntp.services.question.KeywordQuestionServicesDecorator;
import com.ntp.services.question.LevelQuestionServicesDecorator;
import com.ntp.services.question.QuestionServices;
import com.ntp.utils.Configs;
import com.ntp.utils.FlyweightFactory;
import com.ntp.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private ComboBox<Category> cbSeachCates;

    @FXML
    private ComboBox<Level> cbLevels;

    @FXML
    private ComboBox<Level> cbSearchLevels;

    @FXML
    private VBox VboxChoices;

    @FXML
    private TextArea txtContent;

    @FXML
    private ToggleGroup toggleChoices;

    @FXML
    private TableView<Question> tbQuestions;

    @FXML
    private TextField txtSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.cateServices, "categories")));
            this.cbSeachCates.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.cateServices, "categories")));
            this.cbLevels.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.levelServices, "levels")));
            this.cbSearchLevels.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.levelServices, "levels")));
            this.loadColumns();
            this.tbQuestions.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.qServices, "questions")));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        this.txtSearch.textProperty().addListener(e -> {
            try {
                BaseQuestionServices s = new KeywordQuestionServicesDecorator(Configs.qServices, this.txtSearch.getText());
                this.tbQuestions.getItems().clear();
                this.tbQuestions.setItems(FXCollections.observableList(s.list()));
            } catch (SQLException ex) {
                Logger.getLogger(QuestionsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        this.cbSeachCates.getSelectionModel().selectedItemProperty().addListener(e -> {
            try {
                BaseQuestionServices s = new CateQuestionServicesDecorator(Configs.qServices, this.cbSeachCates.getSelectionModel().getSelectedItem().getId());
                this.tbQuestions.getItems().clear();
                this.tbQuestions.setItems(FXCollections.observableList(s.list()));
            } catch (SQLException ex) {
                Logger.getLogger(QuestionsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });

        this.cbSearchLevels.getSelectionModel().selectedItemProperty().addListener(e -> {
            try {
                BaseQuestionServices s = new LevelQuestionServicesDecorator(Configs.qServices, this.cbSearchLevels.getSelectionModel().getSelectedItem().getId());
                this.tbQuestions.getItems().clear();
                this.tbQuestions.setItems(FXCollections.observableList(s.list()));
            } catch (SQLException ex) {
                Logger.getLogger(QuestionsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
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

            Configs.uQServices.addQuestion(qb.build());

            MyAlert.getInstance().showMsg("Thêm câu hỏi thành công!");
            this.tbQuestions.getItems().clear();
            this.tbQuestions.setItems(FXCollections.observableList(FlyweightFactory.updateData(Configs.qServices, "questions")));
        } catch (SQLException ex) {
            MyAlert.getInstance().showMsg("Thêm câu hỏi thất baị, lý do: " + ex.getMessage());
        } catch (Exception ex) {
            MyAlert.getInstance().showMsg("Dữ liệu bị lỗi!");
        }
    }

    private void loadColumns() {
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(100);

        TableColumn colContent = new TableColumn("Nội dung câu hỏi");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(250);

        TableColumn colAction = new TableColumn();
        colAction.setCellFactory(c -> {
            TableCell cell = new TableCell();

            Button btn = new Button("Xóa");
            btn.setOnAction(e -> {
                Optional<ButtonType> t = MyAlert.getInstance().showMsg("Xóa câu hỏi thì các đáp án cũng bị xóa theo. Bạn chắc chắn không?",
                        Alert.AlertType.CONFIRMATION);
                if (t.isPresent() && t.get().equals(ButtonType.OK)) {
                    Question q = (Question) cell.getTableRow().getItem();
                    try {
                        if (Configs.uQServices.deleteQuestion(q.getId()) == true) {
                            this.tbQuestions.getItems().remove(q);
                            MyAlert.getInstance().showMsg("Xóa câu hỏi thành công!", Alert.AlertType.INFORMATION);
                        } else {
                            MyAlert.getInstance().showMsg("Xóa câu hỏi thất bại!", Alert.AlertType.WARNING);
                        }
                    } catch (SQLException ex) {
                        MyAlert.getInstance().showMsg("Hệ thống bị lỗi, lý do: " + ex.getMessage(), Alert.AlertType.ERROR);
                    }
                }
            });

            cell.setGraphic(btn);

            return cell;
        });

        this.tbQuestions.getColumns().addAll(colId, colContent, colAction);
    }
}
