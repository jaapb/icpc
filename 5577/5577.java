import java.io.*;
import java.util.*;

class Main
{ 
	static int b = 0;
	static int w = 0;
	static boolean maze[][];
	static int visited[][];

	static final int NORTH = 0;
	static final int EAST = 1;
	static final int SOUTH = 2;
	static final int WEST = 3;

	static boolean wallOnRight (int x, int y, int dir)
	{
		// for each direction, we check a) if the outside wall is on our right hand b) if there is a maze wall on our right hand
		if (dir == NORTH)
			return (x == w - 1)	|| maze [x + 1][y];
		if (dir == EAST)
			return (y == b - 1) || maze [x][y + 1];
		if (dir == SOUTH)
			return (x == 0)	|| maze [x - 1][y];
		if (dir == WEST)
			return (y == 0) || maze [x][y - 1];
		// this should not happen, exit
		System.exit (1);
		return true;
	}

	static int turnLeft (int dir)
	{
		// System.err.println ("turning left");
		if (dir == NORTH) return WEST;
		if (dir == EAST) return NORTH;
		if (dir == SOUTH) return EAST;
		if (dir == WEST) return SOUTH;
		// this should not happen, exit
		System.exit (1);
		return NORTH;
	}

	static int turnRight (int dir)
	{
		// System.err.println ("turning right");
		if (dir == NORTH) return EAST;
		if (dir == EAST) return SOUTH;
		if (dir == SOUTH) return WEST;
		if (dir == WEST) return NORTH;
		// this should not happen, exit
		System.exit (1);
		return NORTH;
	}

	
	static void simulateRobot ()
	{
		// start at the bottom left corner, direction east	
		int x = 0, y = b - 1, dir = EAST;
		// if there is a wall on the right hand, proceed, otherwise turn left until one can proceed
		do
		{
			if (wallOnRight (x, y, dir))
			{
				// System.err.println ("wall on right");
				if (dir == NORTH)
				{
					if ((y == 0) || maze [x][y - 1])
						dir = turnLeft (dir);
					else
						visited [x][--y] += 1;
				}	
				else if (dir == EAST)
				{
					if ((x == w - 1) || maze [x + 1][y])
						dir = turnLeft (dir);
					else
						visited [++x][y] += 1;
				}	
				else if (dir == SOUTH)
				{
					if ((y == b - 1) || maze [x][y + 1])
						dir = turnLeft (dir);
					else
						visited [x][++y] += 1;
				}	
				else if (dir == WEST)
				{
					if ((x == 0) || maze [x - 1][y])
						dir = turnLeft (dir);
					else
						visited [--x][y] += 1;
				}	
			}
			else
			{
				// there is no wall on our right hand, which means we should turn right and proceed one case.
				// we can always do this, since if we are skirting the outside of the maze, there is a wall
				dir = turnRight (dir);
				if (dir == NORTH) visited [x][--y] += 1;
				else if (dir == EAST) visited [++x][y] += 1;
				else if (dir == SOUTH) visited [x][++y] += 1;
				else if (dir == WEST) visited [--x][y] += 1;
			}
			// System.err.println ("x: " + x + "; y: " + y + "; dir: " + dir);
		}
		while ((x != 0) || (y != b - 1));
	}

	public static String threeJustify (int x)
	{
		if (x < 10)
			return "  " + Integer.toString (x);
		else if (x < 100)
			return " " + Integer.toString (x);
		else
			return Integer.toString (x); 
	}

	public static void main (String args[])
	{ 
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		BufferedWriter out = new BufferedWriter (new OutputStreamWriter (System.out));
		try
		{
			String l = in.readLine ();
			while (!l.equals ("0 0"))
			{
				StringTokenizer tk = new StringTokenizer (l);
				b = Integer.parseInt (tk.nextToken ());
				w = Integer.parseInt (tk.nextToken ());
				maze = new boolean [w][b];
				visited = new int [w][b];
				for (int i = 0; i < b; i++)
				{
					String s = in.readLine ();
					if (s.length () != w)
						System.exit (1);
					for (int j = 0; j < w; j++) 
					{
						visited [j][i] = 0;
						if (s.charAt (j) == '0') maze[j][i]	= false;
						else if (s.charAt (j) == '1') maze[j][i] = true;
						else System.exit (1);
					}
				}
				simulateRobot ();
				int vis [] = new int [5];
				for (int i = 0; i < w; i++)
					for (int j = 0; j < b; j++)
						if ((!maze [i][j]) && (visited [i][j] <= 4))
							vis [visited [i][j]] += 1;
				out.write (threeJustify (vis [0]) + threeJustify (vis [1]) + threeJustify (vis [2]) + threeJustify (vis [3]) + threeJustify (vis [4]));
				out.newLine ();
				l = in.readLine ();
			}
			out.close ();
		}
		catch (IOException e)
		{
			System.exit (1);
		}	
	}
}
