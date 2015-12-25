import java.awt.*;

/**
 * Created by Eugeny on 12.03.2015.
 */
public class Box extends Cell {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0,0,255,127));
        g.fillRect(getX()*GameField.CELL_SIZE, getY()*GameField.CELL_SIZE, GameField.CELL_SIZE, GameField.CELL_SIZE);
        g.setColor(Color.YELLOW);
        g.drawRect(getX() * GameField.CELL_SIZE, getY() * GameField.CELL_SIZE, GameField.CELL_SIZE, GameField.CELL_SIZE);
    }
}
