import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Belogod on 12.03.2015.
 */
public class MainFrame extends JFrame {
    private GameField field;
    private JPanel statusPanel;
    private JLabel levelLabel;
    private JLabel movesLabel;
    private JLabel timeLabel;
    private JLabel pushLabel;
    private long startTime;

    private LevelManager levelManager;
    private int moves = 0;
    private int pushes = 0;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame("Sokoban").setVisible(true);
        });
    }

    public MainFrame(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        levelManager = new LevelManager();
        field = new GameField();
        field.setLevelManager(levelManager);
        field.setPreferredSize(new Dimension(600, 400));
        getContentPane().add(field);
        statusPanel = new JPanel();
        levelLabel = new JLabel(String.format("Level: %d", 1+levelManager.getLevel()));
        movesLabel = new JLabel("Moves: 000");
        pushLabel = new JLabel("Pushes: 000");
        timeLabel = new JLabel("Time: 00:00");
        statusPanel.add(levelLabel);
        statusPanel.add(movesLabel);
        statusPanel.add(pushLabel);
        statusPanel.add(timeLabel);
        getContentPane().add(statusPanel, BorderLayout.SOUTH);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long time = System.currentTimeMillis();
                long difference = (time - startTime) / 1000;
                int minutes = (int) (difference / 60);
                int seconds = (int) (difference % 60);
                timeLabel.setText(String.format("Time: %02d:%02d", minutes, seconds));
            }
        });
        startGame();
        timer.start();
        pack();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                GameEvent ge = GameEvent.NOTHING;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        ge = field.moveMan(-1, 0);
                        break;
                    case KeyEvent.VK_RIGHT:
                        ge = field.moveMan(1, 0);
                        break;
                    case KeyEvent.VK_UP:
                        ge = field.moveMan(0, -1);
                        break;
                    case KeyEvent.VK_DOWN:
                        ge = field.moveMan(0, 1);
                        break;
                }

                switch (ge) {
                    case PUSH:
                        pushLabel.setText(String.format("Pushes: %d", ++pushes));
                    case MOVE:
                        movesLabel.setText(String.format("Moves: %d", ++moves));
                        break;
                    case WIN:
                        movesLabel.setText(String.format("Moves: %d", moves=0));
                        pushLabel.setText(String.format("Pushes: %d", pushes=0));
                        changeLevel(levelManager.getLevel());
                        break;
                }
                repaint();
            }
        });

    }

    private void changeLevel(int level) {
        if (level == levelManager.getTotalLevels()-1) {
            JOptionPane.showMessageDialog(this, "Вся игра пройдена", "Победа", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        levelManager.setLevel(level + 1);
        levelLabel.setText(String.format("Level: %d", 1+levelManager.getLevel()));
        startGame();
    }

    private void startGame() {
        startTime = System.currentTimeMillis();
        field.startGame();
    }
}
