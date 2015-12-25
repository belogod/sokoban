import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Eugeny on 12.03.2015.
 */
public class LevelManager {
    private int level;
    private int totalLevels;

    private String[][] lines;

    public LevelManager() {
        try (BufferedReader in = new BufferedReader(new FileReader("soko.txt"))){
            String s = in.readLine().split(": ")[1];
            int totalLevels = Integer.valueOf(s);
            level = 0;
            lines = new String[totalLevels][];
            for (int i=0; i<totalLevels; i++) {
                in.readLine(); in.readLine(); in.readLine();
                String str = in.readLine().split(": ")[1];
                int y = Integer.valueOf(str);
                lines[i] = new String[y];
                in.readLine(); in.readLine();
                for (int j=0; j<y; j++) {
                    lines[i][j] = in.readLine();
                }
                in.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LevelManager(int level) {
        this.level = level;
    }

    public String[] getLevelLines() {
        return lines[level];
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTotalLevels() {
        return totalLevels;
    }
}
