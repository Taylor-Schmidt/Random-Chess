import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;

/**
 * Container for all other GUI elements.
 */
class MainFrame extends JFrame {
    private GameSettings gameSettings = GameSettings.getInstance();
    private Dimension dimension = new Dimension(1280, 800);

    //TODO: add music
    //TODO:

    MainFrame() {
        super("Random Chess");

        gameSettings.initDefaults();


        setSize(dimension);
        setMinimumSize(new Dimension(1100, 800));
        setLocationRelativeTo(null); //Centers the window in the middle of the main screen
        getContentPane().setBackground(ColorGenerator.backgroundColor);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());

        ImageManager imageManager = ImageManager.getInstance();

        //Set window icon
        ImageIcon icon = imageManager.getScaledImage("pawn_blue");
        setIconImage(icon.getImage());

        OptionPanel mainMenuPanel = new MainMenuPanel();

        GridBagConstraints c = new GridBagConstraints();
//        new BorderLayout(); //Does nothing?
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        add(mainMenuPanel, c);

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
                            "\nand \nChristopher DeLuca.\n\n" +
                            "Audio Credits:\n" +
                            "\"Explosion, 8-bit, 01.wav\" by InspectorJ (www.jshaw.co.uk) of Freesound.org\n" +
                            "\"aiwha_explosion.wav\" by Aiwah of Freesound.org ", "About this game",
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

        GamePanel gamePanel = new GamePanel();
        gamePanel.setVisible(false);
        gamePanel.addTurnChangeListener(e -> infoPanel.toggleColor());
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        add(gamePanel, c);

        PausePanel pausePanel = new PausePanel();
        pausePanel.setVisible(false);
        pausePanel.getButton(0).addActionListener(e -> {
            gamePanel.setVisible(!gamePanel.isVisible());
            pausePanel.setVisible(!pausePanel.isVisible());
        });
        pausePanel.getButton(1).addActionListener(e -> {
            //TODO: update infoPanel
            gamePanel.newGame();
            gamePanel.setVisible(!gamePanel.isVisible());
            pausePanel.setVisible(!pausePanel.isVisible());
        });
        pausePanel.getButton(2).addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        add(pausePanel, c);

        PauseButton pauseButton = new PauseButton();
        pauseButton.addActionListener(e -> {
            gamePanel.setVisible(!gamePanel.isVisible());
            pausePanel.setVisible(!pausePanel.isVisible());
            pauseButton.toggle();
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

        mainMenuPanel.getButton(0).addActionListener(e -> CompletableFuture.runAsync(() -> {
            //TODO: add resume/save functionality
            try {
                Thread.sleep(80);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            mainMenuPanel.setVisible(false);
            pauseButton.setVisible(true);
            infoPanel.setVisible(true);
            gamePanel.setVisible(true);
            validate();
        }));
        mainMenuPanel.getButton(1).addActionListener(e -> {
            //TODO: update info panel here, too
            gamePanel.newGame();
            mainMenuPanel.setVisible(false);
            pauseButton.setVisible(true);
            infoPanel.setVisible(true);
            gamePanel.setVisible(true);
            validate();
        });
        mainMenuPanel.getButton(2).addActionListener(e -> {
            dispose();
            System.exit(0);
        });

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
