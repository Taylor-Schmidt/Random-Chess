
import java.awt.*;
import java.util.Random;
public class RandomColorTile {
    private Color randomLightColor,randomDarkColor;

    RandomColorTile()
    {
        Random rand = new Random();
        boolean notColor = true;

        float hue = rand.nextFloat();
        //float hue = 1.0f;
        System.out.println(hue);
        float saturation = 0.7f;
        float luminance = 0.6f;

        randomLightColor = new Color(hue,saturation,luminance);
        saturation = 0.5f;
        luminance = 0.4f;
        randomDarkColor = new Color(hue,saturation,luminance);
    }

    public Color getDarkColor()
    {
        return randomDarkColor;
    }

    public Color getLightColor()
    {
        return randomLightColor;
    }
}
