import java.awt.*;

/**
 * Created by Belogod on 12.03.2015.
 */
public class Wall extends Cell {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getX()*GameField.CELL_SIZE, getY()*GameField.CELL_SIZE, GameField.CELL_SIZE, GameField.CELL_SIZE);
    }
}
