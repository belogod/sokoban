import java.awt.*;

/**
 * Created by Belogod on 12.03.2015.
 */
public class Man extends Cell {
    public Man(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(getX() * GameField.CELL_SIZE, getY() * GameField.CELL_SIZE, GameField.CELL_SIZE, GameField.CELL_SIZE);
        g.setColor(Color.BLACK);
        g.drawOval(getX()*GameField.CELL_SIZE, getY()*GameField.CELL_SIZE, GameField.CELL_SIZE, GameField.CELL_SIZE);
    }
}
