module facade {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens facade to javafx.fxml;

    exports facade;

    opens controllers to javafx.fxml;

    exports controllers;

    opens model to javafx.fxml;

    exports model;
}
