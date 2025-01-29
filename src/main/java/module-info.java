module com.example.battleship {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.battleship to javafx.fxml;
    exports com.example.battleship;
    exports com.example.battleship.ship;
    opens com.example.battleship.ship to javafx.fxml;
    exports com.example.battleship.gameaction;
    opens com.example.battleship.gameaction to javafx.fxml;
    exports com.example.battleship.menu;
    opens com.example.battleship.menu to javafx.fxml;
}