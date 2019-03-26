import java.util.ArrayList;

/**
 * A collections of MenuOptions and some helper methods to use them.
 * An Actuator will use the data to draw representations of the things in this class.
 */
abstract class  MenuPage {
    private ArrayList<MenuOption> options; //List of items to be listed on the page

    public ArrayList<MenuOption> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<MenuOption> options) {
        this.options = options;
    }
}
