import java.awt.*;
import java.util.Random;

public class ColorGenerator {
    private Color randomLightColor, randomDarkColor;

    static Color backgroundColor = Color.CYAN;

    ColorGenerator() {
        Random rand = new Random();

//        float r = rand.nextFloat();

        //float r = 1.0f;
//        System.out.println(r);
//        float g = 0.7f;
//        float b = 0.6f;

        int r = rand.nextInt(45) + 210;
        int g = 190 + rand.nextInt(50);
        int b = 140 + rand.nextInt(40);

        randomLightColor = new Color(r, g, b);
//        g = 0.5f;
////        b = 0.4f;
//        g = 50;
//        b = 50;
        randomDarkColor = randomLightColor.darker();
    }

    public Color getDarkColor() {
        return randomDarkColor;
    }

    public Color getLightColor() {
        return randomLightColor;
    }
}
