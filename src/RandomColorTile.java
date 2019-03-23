
import java.awt.*;
import java.util.Random;
public class RandomColorTile {
    private Color randomColor;

    RandomColorTile()
    {
        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        randomColor = new Color(r,g,b);
    }

    public Color getDarkColor()
    {
        return randomColor;
    }

    public Color getLightColor()
    {
        return randomColor.brighter();
    }
}
