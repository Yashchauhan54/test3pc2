module org.example.test3yashpc2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens org.example.test3yashpc2 to javafx.fxml;
    exports org.example.test3yashpc2;
}