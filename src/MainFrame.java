import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.concurrent.CompletableFuture;

/**
 * Container for all other GUI elements.
 */
class MainFrame extends JFrame {
    private GameSettings gameSettings = GameSettings.getInstance();
    private Dimension dimension = new Dimension(1280, 800);


    MainFrame() {
        super("Random Chess");

        gameSettings.initDefaults();


        setSize(dimension);
        setMinimumSize(new Dimension(1100, 800));
        setLocationRelativeTo(null); //Centers the window in the middle of the main screen
        getContentPane().setBackground(Color.CYAN);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setLayout(new GridBagLayout());

        ImageManager imageManager = ImageManager.getInstance();

        //Set window icon
        ImageIcon icon = imageManager.getScaledImage("pawn_blue");
        setIconImage(icon.getImage());

        OptionPanel options = new MainMenuPanel();

        GridBagConstraints c = new GridBagConstraints();
//        new BorderLayout(); //Does nothing?
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        add(options, c);

        GamePanel gamePanel = new GamePanel();
        gamePanel.addTurnChangeListener(e -> {
        });




        ImageButton fullScreenButton = new FullScreenButton(e -> toggleFullScreen());

        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.ipadx = 10;
        c.ipady = 10;
        c.anchor = GridBagConstraints.NORTHEAST;
        add(fullScreenButton, c);

        ImageButton infoButton = new HelpButton();
        infoButton.addActionListener(e -> {
            ImageIcon logoIcon = ImageManager.getInstance().getScaledImage("pawn_blue", 35, 35, 0);
            JOptionPane.showMessageDialog(this, "Random Chess is made by:\nJack Xiao\nTaylor" +
                            " Schmidt\nBrandon Cecchini\nRyan Byrnes\nMackenzie Dahlem\nBenjamin Phillips" +
                            "\nand \nChristopher DeLuca.", "About this game",
                    JOptionPane.INFORMATION_MESSAGE, logoIcon);
        });

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.ipadx = 10;
        c.ipady = 10;
        c.anchor = GridBagConstraints.NORTHWEST;
        add(infoButton, c);


        PauseButton pauseButton = new PauseButton();
        pauseButton.addActionListener(e -> {
        });
        pauseButton.setVisible(false);

        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.ipadx = 10;
        c.ipady = 0;
        c.anchor = GridBagConstraints.NORTHEAST;
        add(pauseButton, c);

        InfoPanel infoPanel = new InfoPanel("white");
        infoPanel.setVisible(false);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.ipadx = 10;
        c.ipady = 10;
        c.anchor = GridBagConstraints.NORTHWEST;
        add(infoPanel, c);

        options.getButton(0).addActionListener(e -> CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(115);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            options.setVisible(false);
            pauseButton.setVisible(true);
            infoPanel.setVisible(true);

            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 1;
            c.weighty = 1;
            c.anchor = GridBagConstraints.CENTER;
            add(gamePanel, c);
            validate();
        }));

        setVisible(true);

    }

    private void toggleFullScreen() {
        dispose();
        if ((boolean) gameSettings.get(GameSettings.FULLSCREEN)) {
            setUndecorated(false);
            setExtendedState(JFrame.NORMAL);
            setSize(dimension);
            setLocationRelativeTo(null);
        } else {
            //Stores size of window so that it can be reloaded if the window is no longer full screen.
            dimension.height = getHeight();
            dimension.width = getWidth();

            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setUndecorated(true);
        }
        setVisible(true);
    }

    public class FullScreenButton extends ImageButton {
        FullScreenButton(ActionListener actionListener) {
            super();
            ImageManager m = ImageManager.getInstance();
            icon = m.getScaledImage("full_screen_icon");
            iconPush = m.getScaledImage("full_screen_icon_push");
            altIcon = m.getScaledImage("exit_full_screen_icon");
            altIconPush = m.getScaledImage("exit_full_screen_icon_push");
            toolTipText = "Full screen";
            altToolTipText = "Exit full screen";
            hasAlt = true;

            updateIcon();

            GameSettings gameSettings = GameSettings.getInstance();

            addActionListener(e -> {
                AudioManager.getInstance().playClick();
                if (gameSettings.get(GameSettings.FULLSCREEN) != null) {
                    actionListener.actionPerformed(new ActionEvent(this, 1,
                            Boolean.toString((boolean) gameSettings.get(GameSettings.FULLSCREEN))));

                    toggle();
                    gameSettings.put(GameSettings.FULLSCREEN, !(boolean) gameSettings.get(GameSettings.FULLSCREEN));
                }
            });
        }
    }

    private class HelpButton extends ImageButton {
        HelpButton() {
            super();
            ImageManager m = ImageManager.getInstance();
            icon = m.getScaledImage("question_mark_icon");
            iconPush = m.getScaledImage("question_mark_icon_push");
            toolTipText = "Info";

            updateIcon();

            addActionListener(e -> AudioManager.getInstance().playClick());
        }
    }

    private class PauseButton extends ImageButton {
        PauseButton() {
            super();
            ImageManager m = ImageManager.getInstance();
            icon = m.getScaledImage("pause_button");
            iconPush = m.getScaledImage("pause_button_push");
            toolTipText = "Pause";
            altIcon = m.getScaledImage("play_button");
            altIconPush = m.getScaledImage("play_button_push");
            altToolTipText = "Resume";
            hasAlt = true;

            updateIcon();

            addActionListener(e -> AudioManager.getInstance().playClick());
        }
    }
}
