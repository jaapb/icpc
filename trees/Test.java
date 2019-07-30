class Test
{
	public static void main (String args [])
	{
		Tree <Integer> blerp = new Tree <Integer> (5);
		blerp.add (73);
		blerp.print ();
		System.out.println ();
		blerp.rebalance ();
		blerp.print ();
		System.out.println ();
		blerp.add (9);
		blerp.print ();
		System.out.println ();
		blerp.rebalance ();
		blerp.print ();
		System.out.println ();
		blerp.add (1);
		blerp.print ();
		System.out.println ();
		blerp.rebalance ();
		blerp.print ();
		System.out.println ();
		blerp.add (3);
		blerp.print ();
		System.out.println ();
		blerp.rebalance ();
		blerp.print ();
		System.out.println ();
		blerp.add (42);
		blerp.print ();
		System.out.println ();
		blerp.rebalance ();
		blerp.print ();
		System.out.println ();
		System.out.println (blerp.depth ());
		blerp.rebalance ();
		System.out.println (blerp.depth ());
	}
}
