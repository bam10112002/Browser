package Singleton;

import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.NonNull;

import java.io.File;

@Getter
public enum Theme { LIGHT("Light"), DARK("Dark");
    private String buttonStyle;
    private String tubButtonStyle;
    private String anchorPaneStyle;
    private ImageView prevImage;
    private ImageView nextImage;
    private ImageView likeImage;
    private ImageView reloadImage;
    private ImageView saveImage;
    private ImageView addTabImage;
    private ImageView findImage;
    private ImageView historyImage;
    private ImageView theme;


    Theme(@NonNull String name) {
        String ThemePath;
        if (name.equals("Light")) {
            anchorPaneStyle = "-fx-background-color: white;";
            tubButtonStyle = """
                    -fx-background-radius: 0;
                    -fx-background-color:\s
                        linear-gradient(#EDEDED 0%, #EDEDED 7%, black 7%, black 92%, #EDEDED 92%),
                        linear-gradient(#EDEDED 0%, #EDEDED 100%);
                    -fx-padding: 3 30 3 30;
                    -fx-text-fill: #242d35;
                    -fx-font-size: 14px;
                     """;
            buttonStyle = """
                    -fx-background-radius: 0;
                    -fx-background-color: #EDEDED;
                    -fx-padding: 3 30 3 30;
                    -fx-text-fill: #242d35;
                    -fx-font-size: 14px;
                     """;

            ThemePath = "src/main/resources/light-theme/";
        }
        else if (name.equals("Dark")) {
            anchorPaneStyle = "-fx-background-color: #1E2029;";
            tubButtonStyle = """
                            -fx-background-radius: 0;
                            -fx-background-color:\s
                                linear-gradient(#14151A 0%, #14151A 7%, white 7%, white 92%, #14151A 92%),
                                linear-gradient(#14151A 0%, #14151A 100%);
                            -fx-padding: 3 30 3 30;
                            -fx-font-size: 14px;
                            -fx-text-fill: white;
                             """;
            buttonStyle = """
                            -fx-background-radius: 0;
                            -fx-background-color: #14151A;
                            -fx-padding: 3 30 3 30;
                            -fx-font-size: 14px;
                            -fx-text-fill: white;
                             """;

            ThemePath = "src/main/resources/dark-theme/";
        }
        else {
            System.err.println("Error theme not found");
            return;
        }
        LoadImages(ThemePath);
    }
    private void LoadImages(String path) {
        addTabImage = new ImageView(new File(path + "add-tab.png").toURI().toString());
        addTabImage.setScaleX(0.5);
        addTabImage.setScaleY(0.5);

        prevImage   = new ImageView(new File(path + "next-arrow.png").toURI().toString());
        prevImage.setScaleX(0.5);
        prevImage.setScaleY(0.5);

        nextImage   = new ImageView(new File(path + "prev-arrow.png").toURI().toString());
        nextImage.setScaleX(0.5);
        nextImage.setScaleY(0.5);

        reloadImage = new ImageView(new File(path + "reload.png").toURI().toString());
        reloadImage.setScaleX(0.5);
        reloadImage.setScaleY(0.5);

        saveImage   = new ImageView(new File(path + "load.png").toURI().toString());
        saveImage.setScaleX(0.5);
        saveImage.setScaleY(0.5);

        findImage   = new ImageView(new File(path + "find.png").toURI().toString());
        findImage.setScaleX(0.5);
        findImage.setScaleY(0.5);

        likeImage   = new ImageView(new File(path + "like.png").toURI().toString());
        likeImage.setScaleX(0.5);
        likeImage.setScaleY(0.5);

        historyImage   = new ImageView(new File(path + "history.png").toURI().toString());
        historyImage.setScaleX(0.5);
        historyImage.setScaleY(0.5);

        theme = new ImageView(new File(path + "theme.png").toURI().toString());
        theme.setScaleX(0.5);
        theme.setScaleY(0.5);


    }
}