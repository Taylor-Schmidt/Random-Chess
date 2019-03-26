import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CompletableFuture;

/**
 * Acts as controller for this test program.
 */
public class MainFrame extends JFrame {


    public MainFrame() {
        super("Random Chess");

        GameSettings gameSettings = GameSettings.getInstance();
        gameSettings.initDefaults();

        Dimension dimension = new Dimension(1280, 800);

        setSize(dimension);
        setMinimumSize(new Dimension(700, 700));
        setLocationRelativeTo(null);//Centers the window in the middle of the main screen
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

        GamePanel gamePanel = new GamePanel(16, 16);

        options.getButton(0).addActionListener(e -> {
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(200);
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
            });

        });


        JButton fullScreenButton = new JButton();

        ImageIcon fsIcon = getScaledIcon(new ImageIcon("assets/full_screen_icon.png"));
        ImageIcon fsPush = getScaledIcon(new ImageIcon("assets/full_screen_icon_push.png"));
        ImageIcon exitFsIcon = getScaledIcon(new ImageIcon("assets/exit_full_screen_icon.png"));
        ImageIcon exitFsPush = getScaledIcon(new ImageIcon("assets/exit_full_screen_icon_push.png"));

        fullScreenButton.setIcon(fsIcon);
        fullScreenButton.setPressedIcon(fsPush);
        fullScreenButton.setBorderPainted(false);
        fullScreenButton.setFocusPainted(false);
        fullScreenButton.setContentAreaFilled(false);
        fullScreenButton.setPreferredSize(new Dimension(75, 75));
        fullScreenButton.setToolTipText("Full screen");

        fullScreenButton.addActionListener(e -> {
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
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.ipadx = 10;
        c.ipady = 10;
        add(fullScreenButton, c);

        setVisible(true);

    }

    private ImageIcon getScaledIcon(ImageIcon imageIcon) {
        Image fsImage = imageIcon.getImage();

        return new ImageIcon(fsImage.getScaledInstance(50, 50, 0));
    }



}
