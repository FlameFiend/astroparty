import java.awt.*;

public class Map {
    private Walls map;
    private int tileSize;
    private int mapWidth;
    private int mapHeight;
    private int[][] grid;
    private Point spawnA;
    private Point spawnB;

    public Map(int mapWidth, int mapHeight, int tileSize) {
        this.tileSize = tileSize;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.map = new Walls();
    }

    public void generateMap(double wallChance) {
        int cols = mapWidth / tileSize - 1;
        int rows = mapHeight / tileSize - 1;
        spawnA = new Point(1, 1);
        spawnB = new Point(cols - 2, rows - 2);
        boolean valid = false;

        while (!valid) {
            grid = new int[rows][cols];
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (isInSpawnZone(col, row, spawnA) || isInSpawnZone(col, row, spawnB)) {
                        grid[row][col] = 0;
                    } else {
                        grid[row][col] = Math.random() < wallChance ? 1 : 0;
                    }
                }
            }
            boolean[][] visited = new boolean[rows][cols];
            valid = pathExists(spawnA.x, spawnA.y, visited);
        }

        map = new Walls();  // Clear previous map
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    map.addWall(new Rectangle(col * tileSize, row * tileSize, tileSize, tileSize));
                }
            }
        }
    }

    private boolean isInSpawnZone(int col, int row, Point center) {
        return Math.abs(col - center.x) <= 1 && Math.abs(row - center.y) <= 1;
    }

    // maze solving
    private boolean pathExists(int x, int y, boolean[][] visited) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (x < 0 || x >= cols || y < 0 || y >= rows) return false;
        if (visited[y][x] || grid[y][x] == 1) return false;
        if (x == spawnB.x && y == spawnB.y) return true;

        visited[y][x] = true;

        return pathExists(x + 1, y, visited) ||
               pathExists(x - 1, y, visited) ||
               pathExists(x, y + 1, visited) ||
               pathExists(x, y - 1, visited);
    }

    public void draw(Graphics g) {
        map.draw(g);
    }

    public boolean checkCollision(Rectangle object) {
        return map.checkCollision(object);
    }

    public Point getSpawnA() {
        return new Point(tileSize + 1, tileSize + 1);
    }

    public Point getSpawnB() {
        return new Point(mapWidth - 2 * tileSize, mapHeight - 2 * tileSize);
    }
}

