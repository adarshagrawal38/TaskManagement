package Helpers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class IconHelper {

    public static String ICON_UP = "up";
    public static String ICON_DOWN = "down";
    public static String ICON_COMMENT = "comment";
    public static String ICON_COMPLETE = "complete";

    public ImageView getIcon(String name) {
        Image image = new Image(getClass().getResourceAsStream("/icons/" + name +".png"));
        ImageView imageView = new javafx.scene.image.ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(Constants.IMAGE_ICON_BUTTON_HEIGHT);
        imageView.setFitWidth(Constants.IMAGE_ICON_BUTTON_WIDTH);

        return imageView;
    }
}
