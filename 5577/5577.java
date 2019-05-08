import java.io.*;
import java.util.*;

class Main
{ 
	static boolean maze[][];

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
				int b = Integer.parseInt (tk.nextToken ());
				int w = Integer.parseInt (tk.nextToken ());
				maze = new boolean [b][w];
				for (int i = 0; i < b; i++)
				{
					String s = in.readLine ();
					if (s.length () != w)
						System.exit (1);
					for (int j = 0; j < w; j++) 
					{
						if (s.charAt (j) == '0') maze[i][j]	= false;
						else if (s.charAt (j) == '1') maze[i][j] = true;
					}
				}
			}
		}
		catch (IOException e)
		{
			System.exit (1);
		}	
	}
}
