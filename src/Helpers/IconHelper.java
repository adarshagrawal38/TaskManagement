package Helpers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class IconHelper {

    public static String ICON_UP = "up";
    public static String ICON_DOWN = "down";
    public static String ICON_COMMENT = "comment";
    public static String ICON_COMPLETE = "complete";
    public static String ICON_EXPORT = "export";
    public static String ICON_VIEW = "eye";
    public static String ICON_EDIT = "edit";
    public static String ICON_DELETE = "delete";
    public static String ICON_ERROR = "error";
    public static String ICON_ADD = "add";
    public static String ICON_PASSWORD = "password";
    public static String ICON_FOLDER = "Folder_24px";
    public static String ICON_BACK = "return";
    public static String ICON_CHANGE = "change";

    public ImageView getIcon(String name) {
        Image image = new Image(getClass().getResourceAsStream("/icons/" + name +".png"));
        ImageView imageView = new javafx.scene.image.ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(Constants.IMAGE_ICON_BUTTON_HEIGHT);
        imageView.setFitWidth(Constants.IMAGE_ICON_BUTTON_WIDTH);

        return imageView;
    }
}
