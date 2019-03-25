import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OptionPanel extends JPanel {

    protected ArrayList<MenuButton> options;
    protected GridBagConstraints c;

    public OptionPanel() {
        options = new ArrayList<>();
        setBackground(Color.CYAN);

        c = new GridBagConstraints();
        setLayout(new GridBagLayout());
    }


    void addComponent(int yPos, JComponent jComponent){
        System.out.println("yPos = " + yPos);
        c.gridy = yPos;
        add(jComponent, c);
    }

    public void addButton(MenuButton button) {
        options.add(button);
        addComponent(options.size(), button);
    }

    public void addButton(int pos, MenuButton button) {
        options.add(pos, button);
        addComponent(pos + 1, button);
    }

    public MenuButton getButton(int i) {
        return options.get(i);
    }

    public void removeButton(int pos) {
        remove(options.remove(pos));
    }

    public void setMenuButton(int pos, MenuButton button) {
        remove(options.set(pos, button));
        addComponent(pos, button);
    }
}
