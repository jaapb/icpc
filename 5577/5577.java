import java.io.*;
import java.util.*;

class Main
{ 
	static int b = 0;
	static int w = 0;
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
				b = Integer.parseInt (tk.nextToken ());
				w = Integer.parseInt (tk.nextToken ());
				maze = new boolean [w][b];
				for (int i = 0; i < b; i++)
				{
					String s = in.readLine ();
					if (s.length () != w)
						System.exit (1);
					for (int j = 0; j < w; j++) 
					{
						if (s.charAt (j) == '0') maze[j][i]	= false;
						else if (s.charAt (j) == '1') maze[j][i] = true;
						else System.exit (1);
					}
				}
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
