module graphics {
    requires javafx.controls;

    opens graphics to javafx.graphics;

    exports graphics;
}