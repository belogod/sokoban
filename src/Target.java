import java.awt.*;

/**
 * Created by Belogod on 12.03.2015.
 */
public class Target extends Cell {
    public Target(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(getX()*GameField.CELL_SIZE, getY()*GameField.CELL_SIZE, GameField.CELL_SIZE, GameField.CELL_SIZE);
        g.setColor(Color.BLUE);
        g.drawRect(getX()*GameField.CELL_SIZE, getY()*GameField.CELL_SIZE, GameField.CELL_SIZE, GameField.CELL_SIZE);
    }
}
