import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugeny on 12.03.2015.
 */
public class GameField extends JPanel {
    public static final int CELL_SIZE = 20;

    private Man man;
    private List<Box> boxes;
    private List<Target> targets;
    private List<Wall> walls;
    private LevelManager manager;

    public GameField() {
    }

    public void setLevelManager(LevelManager levelManager) {
        manager = levelManager;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        for (Wall w : walls) {
//            w.draw(g);
//        }
        walls.stream().forEach(w -> w.draw(g));
        targets.stream().forEach(t -> t.draw(g));
        boxes.stream().forEach(b -> b.draw(g));
        man.draw(g);
    }

    public void startGame() {
//        man = new Man();
        boxes = new ArrayList<>();
        targets = new ArrayList<>();
        walls = new ArrayList<>();
        String[] levelLines = manager.getLevelLines();
        for (int i = 0; i < levelLines.length; i++) {
            for (int j = 0; j < levelLines[i].length(); j++) {
                switch (levelLines[i].charAt(j)) {
                    case 'X':
                        walls.add(new Wall(j, i));
                        break;
                    case '*':
                        boxes.add(new Box(j, i));
                        break;
                    case '.':
                        targets.add(new Target(j, i));
                        break;
                    case '@':
                        man = new Man(j, i);
                        break;
                    case '&':
                        boxes.add(new Box(j, i));
                        targets.add(new Target(j, i));
                        break;
                }
            }
        }
    }

    public GameEvent moveMan(int dx, int dy) {
        int x = man.getX() + dx;
        int y = man.getY() + dy;
        if (hasWall(x,y)) {
            return GameEvent.FAIL;
        }
        if (hasBox(x,y) && (hasWall(x+dx,y+dy) || hasBox(x + dx, y + dy))) {
            return GameEvent.FAIL;
        }
        man.move(dx,dy);
        GameEvent ge = GameEvent.MOVE;
        if (hasBox(x,y)) {
            boxes.stream().filter(b -> b.getX()==x && b.getY()==y).findAny().get().move(dx,dy);
            ge = GameEvent.PUSH;
        }
        if (win()) {
            ge = GameEvent.WIN;
        }
        return ge;
    }

    private boolean win() {
        return boxes.stream().allMatch(b -> targets.contains(b));
    }

    private boolean hasBox(int x, int y) {
        return boxes.stream().anyMatch(b -> b.getX()==x && b.getY()==y);
    }

    private boolean hasWall(int x, int y) {
        return walls.stream().anyMatch(w -> w.getX()==x && w.getY()==y);
    }
}
