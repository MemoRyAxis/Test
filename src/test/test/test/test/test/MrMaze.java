package test.test.test.test.test;

import java.util.Random;

public class MrMaze {

	public final static String TAB_MAKER = "┌┬┐├┼┤└┴┘";

	public final static String BRICK = "■ ";
	
	public final static String WAY = "  ";

	public void newMaze(int length, int width) {
		String[][] maze = new String[length][width];
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (i == 0 || j == 0 || i == length - 1 || j == width - 1) {
					maze[i][j] = BRICK;
				} else {
//					maze[i][j] = WAY;
					maze[i][j] = BRICK;
				}
			}
		}
		
		int startX = 0;
		int startY = 0;
		int locus = 0;
		
		
		
		while (startX != length && startY != width) {
			locus = new Random().nextInt(2);
			if (locus == 0) {
				maze[startX++][startY] = WAY;
			} else {
				maze[startX][startY++] = WAY;
			}
		}
		
		
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
			
		}
	}
	
	public static void main(String[] args) {
		MrMaze maze = new MrMaze();
		maze.newMaze(20, 20);
	}
}
