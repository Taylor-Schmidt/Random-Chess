import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.concurrent.CompletableFuture;

/**
 * Container for all other GUI elements.
 */
class MainFrame extends JFrame {


    MainFrame() {
        super("Random Chess");

        GameSettings gameSettings = GameSettings.getInstance();
        gameSettings.initDefaults();

        Dimension dimension = new Dimension(1280, 800);

        setSize(dimension);
        setMinimumSize(new Dimension(1100, 800));
        setLocationRelativeTo(null); //Centers the window in the middle of the main screen
        getContentPane().setBackground(Color.CYAN);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setLayout(new GridBagLayout());

        //Set window icon
        ImageIcon icon = new ImageIcon("assets/pawn_blue.png");
        setIconImage(icon.getImage());

        OptionPanel options = new MainMenuPanel();

        GridBagConstraints c = new GridBagConstraints();
//        new BorderLayout(); //Does nothing?
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        add(options, c);

        GamePanel gamePanel = new GamePanel();

        options.getButton(0).addActionListener(e -> CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(115);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            options.setVisible(false);

            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 1;
            c.weighty = 1;
            add(gamePanel, c);
            validate();
        }));


        JButton fullScreenButton = getFullScreenButton(gameSettings, dimension);

        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.ipadx = 10;
        c.ipady = 10;
        add(fullScreenButton, c);

        JButton helpButton = getHelpButton(icon);

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.ipadx = 10;
        c.ipady = 10;
        add(helpButton, c);

        setVisible(true);

    }

    private JButton getFullScreenButton(GameSettings gameSettings, Dimension dimension) {
        JButton fullScreenButton = new JButton();

        ImageIcon fsIcon = getScaledIcon(new ImageIcon("assets/full_screen_icon.png"));
        ImageIcon fsPush = getScaledIcon(new ImageIcon("assets/full_screen_icon_push.png"));
        ImageIcon exitFsIcon = getScaledIcon(new ImageIcon("assets/exit_full_screen_icon.png"));
        ImageIcon exitFsPush = getScaledIcon(new ImageIcon("assets/exit_full_screen_icon_push.png"));

        applyIcons(fullScreenButton, fsIcon, fsPush);
        fullScreenButton.setToolTipText("Full screen");

        fullScreenButton.addActionListener(e -> {
            AudioManager.getInstance().playClick();
            if (gameSettings.get(GameSettings.FULLSCREEN) != null) {
                dispose();
                if ((boolean) gameSettings.get(GameSettings.FULLSCREEN)) {
                    setUndecorated(false);
                    setExtendedState(JFrame.NORMAL);
                    setSize(dimension);
                    setLocationRelativeTo(null);

                    fullScreenButton.setIcon(fsIcon);
                    fullScreenButton.setPressedIcon(fsPush);
                } else {
                    //Stores size of window so that it can be reloaded if the window is no longer full screen.
                    dimension.height = getHeight();
                    dimension.width = getWidth();

                    setExtendedState(JFrame.MAXIMIZED_BOTH);
                    setUndecorated(true);
                    fullScreenButton.setIcon(exitFsIcon);
                    fullScreenButton.setPressedIcon(exitFsPush);
                }
                gameSettings.put(GameSettings.FULLSCREEN, !(boolean) gameSettings.get(GameSettings.FULLSCREEN));
                setVisible(true);
            }
            validate();
        });
        return fullScreenButton;
    }

    private void applyIcons(JButton fullScreenButton, ImageIcon fsIcon, ImageIcon fsPush) {
        fullScreenButton.setIcon(fsIcon);
        fullScreenButton.setPressedIcon(fsPush);
        fullScreenButton.setBorderPainted(false);
        fullScreenButton.setFocusPainted(false);
        fullScreenButton.setContentAreaFilled(false);
        fullScreenButton.setPreferredSize(new Dimension(75, 75));
    }

    private JButton getHelpButton(ImageIcon icon) {
        JButton helpButton = new JButton();
        ImageIcon helpIcon = getScaledIcon(new ImageIcon("assets/question_mark_icon.png"));
        ImageIcon helpIconPressed = getScaledIcon(new ImageIcon("assets/question_mark_icon_push.png"));
        applyIcons(helpButton, helpIcon, helpIconPressed);
        helpButton.setToolTipText("Info");
        helpButton.addActionListener(e -> {
            AudioManager.getInstance().playClick();
            JOptionPane.showMessageDialog(this, "Random Chess is made by:\nJack Xiao\nTaylor" +
                    " Schmidt\nBrandon Cecchini\nRyan Byrnes\nMackenzie Dahlem\nBenjamin Phillips" +
                    "\nand \nChristopher DeLuca.", "About this game",
                    JOptionPane.INFORMATION_MESSAGE, getScaledIcon(icon, 35, 35, 0));
        });
        return helpButton;
    }

    private ImageIcon getScaledIcon(ImageIcon imageIcon){
        return getScaledIcon(imageIcon, 50, 50, 0);
    }

    @SuppressWarnings("SameParameterValue")
    private ImageIcon getScaledIcon(ImageIcon imageIcon, int w, int h, double paddingRatio) {
        Image fsImage = imageIcon.getImage();

        int width;
        int height;

        double widthToHeightRatio = imageIcon.getIconWidth() / (imageIcon.getIconHeight() * 1.0); //float div with int

        double internalSize = 1 - (2 * paddingRatio);
        if (widthToHeightRatio > 1){//width is bigger
            width = (int) (w * internalSize);
            height = (int) (w / widthToHeightRatio);
        } else if (widthToHeightRatio < 1){
            height = (int) (h * internalSize);
            width = (int) (h * widthToHeightRatio);
        } else {
            width = (int) (w * internalSize);
            height = (int) (h * internalSize);
        }

        return new ImageIcon(fsImage.getScaledInstance(width, height, 0));
    }


}
