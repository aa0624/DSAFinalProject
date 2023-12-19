import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

public class Main extends JFrame {
    private static final int GRID_SIZE = 32;
    private static final double WALL_PROBABILITY = 0.3; // Adjust this value to control wall density
    private static final Random random = new Random();

    private final GameEntity[][] map = new GameEntity[GRID_SIZE][GRID_SIZE];
    private int pacmanRow;
    private int pacmanCol;
    private final Timer timer;
    private boolean keyPressInProgress = false;

    public Main() {
        setTitle("Pacman Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        generateRandomMaze();
        placeGhosts();
        placePacman();

        addKeyListener(new PacmanKeyListener());
        setFocusable(true);

        addKeyListener(new PacmanKeyListener());
        setFocusable(true);

        // Set up a timer for game updates
        timer = new Timer(200, e -> {
            moveGhosts();
            repaint();
        });
        timer.start();
    }


    private void generateRandomMaze() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (i == 0 || i == GRID_SIZE - 1 || j == 0 || j == GRID_SIZE - 1) {
                    map[i][j] = new GameEntity(EntityType.WALL); // Create walls around the border
                } else {
                    map[i][j] = (random.nextDouble() < WALL_PROBABILITY) ? new GameEntity(EntityType.WALL) : new GameEntity(EntityType.DOT);
                }
            }
        }
    }

    private void placeGhosts() {
        // Place ghosts in specific locations (you can customize these positions)
        map[1][1] = new GameEntity(EntityType.GHOST);
        map[1][GRID_SIZE - 2] = new GameEntity(EntityType.GHOST);
        map[GRID_SIZE - 2][1] = new GameEntity(EntityType.GHOST);
        map[GRID_SIZE - 2][GRID_SIZE - 2] = new GameEntity(EntityType.GHOST);
    }


    private void placePacman() {
        // Place Pacman in the middle of the grid
        pacmanRow = GRID_SIZE / 2;
        pacmanCol = GRID_SIZE / 2;
        map[pacmanRow][pacmanCol] = new GameEntity(EntityType.PACMAN);
    }

    private void movePacman(int keyCode) {
        // Handle Pacman movement based on the pressed key
        int newPacmanRow = pacmanRow;
        int newPacmanCol = pacmanCol;

        switch (keyCode) {
            case KeyEvent.VK_UP -> newPacmanRow--;
            case KeyEvent.VK_DOWN -> newPacmanRow++;
            case KeyEvent.VK_LEFT -> newPacmanCol--;
            case KeyEvent.VK_RIGHT -> newPacmanCol++;
        }

        // Check if the new position is valid (not a wall)
        if (isValidMove(newPacmanRow, newPacmanCol)) {
            // Move Pacman
            map[pacmanRow][pacmanCol] = new GameEntity(EntityType.DOT);
            pacmanRow = newPacmanRow;
            pacmanCol = newPacmanCol;
            map[pacmanRow][pacmanCol] = new GameEntity(EntityType.PACMAN);
        }
    }

    private class PacmanKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (!keyPressInProgress) {
                keyPressInProgress = true;
                movePacman(e.getKeyCode());
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keyPressInProgress = false;
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < GRID_SIZE && col >= 0 && col < GRID_SIZE && map[row][col].getType() != EntityType.WALL;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Paint the grid
        int cellSize = 20;
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int x = j * cellSize;
                int y = i * cellSize;
                Color color = getColorForEntity(map[i][j]);
                g.setColor(color);
                g.fillRect(x, y, cellSize, cellSize);
            }
        }
    }

    private Color getColorForEntity(GameEntity entity) {
        return switch (entity.getType()) {
            case WALL -> Color.BLUE;
            case GHOST -> Color.RED;
            case PACMAN -> Color.YELLOW;
            default -> Color.WHITE;
        };
    }

    private void endGame(String message) {
        timer.stop(); // Stop the timer to halt game updates
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }


    private void moveGhosts() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (map[i][j].getType() == EntityType.GHOST) {
                    // Move each ghost
                    moveGhostTowardsPacman(i, j);
                }
            }
        }
        repaint();
    }

    private void moveGhostTowardsPacman(int currentRow, int currentCol) {
        int[] pacmanPosition = findPacmanPosition();
        if (pacmanPosition != null && currentRow == pacmanPosition[0] && currentCol == pacmanPosition[1]) {
            // Ghost and Pacman are in the same position, end the game
            endGame("Ghost caught Pacman! Game Over");
            return;
        }
        // A* search algorithm with PriorityQueue
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        boolean[][] visited = new boolean[GRID_SIZE][GRID_SIZE];
        Node[][] previousNode = new Node[GRID_SIZE][GRID_SIZE];

        priorityQueue.offer(new Node(currentRow, currentCol, 0, calculateHeuristic(currentRow, currentCol, pacmanPosition[0], pacmanPosition[1])));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            int row = currentNode.row;
            int col = currentNode.col;

            if (row == pacmanPosition[0] && col == pacmanPosition[1]) {
                // Found Pacman, reconstruct path
                List<Node> path = reconstructPath(previousNode, currentNode);

                // Move the ghost one square towards Pacman
                if (!path.isEmpty()) {
                    Node nextMove = path.get(path.size() - 1);
                    moveGhost(currentRow, currentCol, nextMove.row, nextMove.col);
                }
                return;
            }

            if (visited[row][col]) {
                continue;
            }

            visited[row][col] = true;

            // Explore neighbors
            int[][] neighbors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] neighbor : neighbors) {
                int newRow = row + neighbor[0];
                int newCol = col + neighbor[1];

                if (isValidMove(newRow, newCol) && !visited[newRow][newCol]) {
                    int cost = currentNode.cost + 1;
                    int heuristic = calculateHeuristic(newRow, newCol, pacmanPosition[0], pacmanPosition[1]);
                    Node nextNode = new Node(newRow, newCol, cost, heuristic);
                    priorityQueue.offer(nextNode);

                    // Store previous node for reconstructing the path
                    previousNode[newRow][newCol] = currentNode;
                }
            }
        }
    }

    private List<Node> reconstructPath(Node[][] previousNode, Node lastNode) {
        List<Node> path = new ArrayList<>();
        Node currentNode = lastNode;
        while (currentNode != null) {
            path.add(currentNode);
            currentNode = previousNode[currentNode.row][currentNode.col];
        }
        Collections.reverse(path);
        return path;
    }
    private void moveGhost(int currentRow, int currentCol, int newRow, int newCol) {
        // Move the ghost
        map[currentRow][currentCol] = new GameEntity(EntityType.DOT);
        map[newRow][newCol] = new GameEntity(EntityType.GHOST);
    }

    private int[] findPacmanPosition() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (map[i][j].getType() == EntityType.PACMAN) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // Pacman not found (should not happen in a valid game)
    }

    private int calculateHeuristic(int row, int col, int targetRow, int targetCol) {
        // A simple heuristic: Manhattan distance
        return Math.abs(row - targetRow) + Math.abs(col - targetCol);
    }

    private static class Node implements Comparable<Node> {
        int row;
        int col;
        int cost;
        int heuristic;

        public Node(int row, int col, int cost, int heuristic) {
            this.row = row;
            this.col = col;
            this.cost = cost;
            this.heuristic = heuristic;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(cost + heuristic, other.cost + other.heuristic);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.setSize(GRID_SIZE * 20, GRID_SIZE * 20);
            main.setVisible(true);
        });
    }
}
