import java.awt.*;
import java.util.Random;

public class ColorGenerator {
    private Color randomLightColor, randomDarkColor;

    ColorGenerator() {
        Random rand = new Random();
        boolean notColor = true;

//        float r = rand.nextFloat();

        //float r = 1.0f;
//        System.out.println(r);
//        float g = 0.7f;
//        float b = 0.6f;

        int r = rand.nextInt(50) + 100;
        int g = 0;
        int b = 0;

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
