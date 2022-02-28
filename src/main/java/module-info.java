module com.example.wordmasterwithgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;
    requires junit;


    opens App to javafx.fxml;
    exports App;
    exports Controller;
    opens Controller to javafx.fxml;
}