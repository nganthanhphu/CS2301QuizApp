module com.ntp.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires lombok;

    opens com.ntp.quizapp to javafx.fxml;
    exports com.ntp.quizapp;
    exports com.ntp.pojo;
}
