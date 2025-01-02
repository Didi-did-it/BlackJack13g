module com.example.blackjack13g {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.blackjack13g to javafx.fxml;
    exports com.example.blackjack13g;
}