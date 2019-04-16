import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Objects;

class ImageManager {
    private ImageManager() {
        images = new HashMap<>();

        int headerSize = "assets/".length();
        int footerSize = ".png".length();

        File folder = new File("assets");
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.getName().contains(".png")) {
                ImageIcon icon = new ImageIcon(file.getPath());
                String key = file.getPath().toLowerCase();
                key = key.substring(headerSize, key.length() - footerSize);
                images.put(key, icon);
            }
        }

        System.out.println(images);
    }

    private static final ImageManager imageManager = new ImageManager();

    static ImageManager getInstance() {
        return imageManager;
    }

    private final HashMap<String, ImageIcon> images;

    ImageIcon getImage(String name) {
        return images.get(name.toLowerCase());
    }

    ImageIcon getScaledImage(String name) {
        return getScaledImage(getImage(name));
    }

    private ImageIcon getScaledImage(ImageIcon imageIcon) {
        return getScaledImage(imageIcon, 50, 50, 0);
    }

    ImageIcon getScaledImage(String name, int w, int h, double paddingRatio) {
        return getScaledImage(getImage(name), w, h, paddingRatio);
    }

    @SuppressWarnings("SameParameterValue")
    private ImageIcon getScaledImage(ImageIcon imageIcon, int w, int h, double paddingRatio) {
        Image fsImage = imageIcon.getImage();

        int width;
        int height;

        double widthToHeightRatio = imageIcon.getIconWidth() / (imageIcon.getIconHeight() * 1.0); //float div with int

        double internalSize = 1 - (2 * paddingRatio);
        if (widthToHeightRatio > 1) {//width is bigger
            width = (int) (w * internalSize);
            height = (int) (w / widthToHeightRatio);
        } else if (widthToHeightRatio < 1) {
            height = (int) (h * internalSize);
            width = (int) (h * widthToHeightRatio);
        } else {
            width = (int) (w * internalSize);
            height = (int) (h * internalSize);
        }

        return new ImageIcon(fsImage.getScaledInstance(width, height, 0));
    }
}
