import java.awt.*;
import java.util.*;
public class Map {
   private ArrayList<Walls> maps;
   private int tileSize;
   private int mapWidth;
   private int mapHeight;
   public Map(int mapWidth, int mapHeight, int tileSize) {
       this.tileSize = tileSize;
       this.mapWidth = mapWidth;
       this.mapHeight = mapHeight;
       this.maps = new ArrayList<>();
   }
   // Represent tiles as 2D array (0 = empty, 1 = wall)
   public void generateMap(double wallChance) {
       int cols = mapWidth / tileSize;
       int rows = mapHeight / tileSize;
       int[][] grid;
       Point spawnA = new Point(1, 1);                        // player 1 spawn (near top-left)
       Point spawnB = new Point(cols - 2, rows - 2);          // player 2 spawn (near bottom-right)
       do {
           grid = new int[rows][cols];
           Random rand = new Random();
           // Generate random walls
           for (int row = 0; row < rows; row++) {
               for (int col = 0; col < cols; col++) {
                   if ((isInSpawnZone(col, row, spawnA) || isInSpawnZone(col, row, spawnB))) {
                       grid[row][col] = 0;  // clear spawn zone
                   } else {
                       grid[row][col] = rand.nextDouble() < wallChance ? 1 : 0;
                   }
               }
           }
       } while (!pathExists(grid, spawnA, spawnB));
       // Convert grid to walls
       Walls wallSet = new Walls();
       for (int row = 0; row < rows; row++) {
           for (int col = 0; col < cols; col++) {
               if (grid[row][col] == 1) {
                   wallSet.addWall(new Rectangle(col * tileSize, row * tileSize, tileSize, tileSize));
               }
           }
       }
       maps.add(wallSet);
   }
   private boolean isInSpawnZone(int col, int row, Point center) {
       return Math.abs(col - center.x) <= 1 && Math.abs(row - center.y) <= 1;
   }
   // BFS to check if a path exists from A to B
   private boolean pathExists(int[][] grid, Point start, Point end) {
       int rows = grid.length;
       int cols = grid[0].length;
       boolean[][] visited = new boolean[rows][cols];
       Queue<Point> queue = new LinkedList<>();
       queue.add(start);
       int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
       while (!queue.isEmpty()) {
           Point p = queue.poll();
           if (p.equals(end)) return true;
           for (int[] d : directions) {
               int nx = p.x + d[0];
               int ny = p.y + d[1];
               if (nx >= 0 && nx < cols && ny >= 0 && ny < rows && grid[ny][nx] == 0 && !visited[ny][nx]) {
                   visited[ny][nx] = true;
                   queue.add(new Point(nx, ny));
               }
           }
       }
       return false;
   }
   public void drawCurrentMap(Graphics g, int index) {
       if (index >= 0 && index < maps.size()) {
           maps.get(index).draw(g);
       }
   }
   public boolean checkCollision(Rectangle object, int index) {
       if (index >= 0 && index < maps.size()) {
           return maps.get(index).checkCollision(object);
       }
       return false;
   }
   public int getMapCount() {
       return maps.size();
   }
   public Walls getMap(int index) {
       if (index >= 0 && index < maps.size()) {
           return maps.get(index);
       }
       return null;
   }
   public Point getSpawnA() {
       return new Point(tileSize + 1, tileSize + 1);
   }
   public Point getSpawnB() {
       return new Point(mapWidth - 2 * tileSize, mapHeight - 2 * tileSize);
   }
}
