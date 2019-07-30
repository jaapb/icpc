public class Tree<T extends Comparable>
{
	private T node;
	private Tree<T> left;
	private Tree<T> right;

	public Tree (T node)
	{
		this.node = node;
		left = null;
		right = null;
	}

	public Tree (T node, Tree<T> left, Tree<T> right)
	{
		this.node = node;
		this.left = left;
		this.right = right;
	}

	public T getNode ()
	{
		return node;
	}	

	public void add (T value)
	{
		int res = node.compareTo (value);

		if (res < 0)
		{
			if (right == null)
				right = new Tree<T> (value);
			else
				right.add (value);
		}
		else if (res > 0)
		{
			if (left == null)
				left = new Tree<T> (value);
			else
				left.add (value);
		}
	}

	public int depth ()
	{
		int leftDepth = left == null ? 0 : left.depth ();
		int rightDepth = right == null ? 0 : right.depth ();

		return Math.max (leftDepth, rightDepth) + 1;
	}

	public void rebalance ()
	{
		int leftDepth = left == null ? 0 : left.depth ();
		int rightDepth = right == null ? 0 : right.depth ();
		int diff = leftDepth - rightDepth;

		if (diff > 1)
			rotateRight ();
		else if (diff < 1)	
			rotateLeft ();
	
		if (left != null)
			left.rebalance ();
		if (right != null)
							right.rebalance ();
				
	}

	public void rotateLeft ()
	{
		assert (right != null);
		left = new Tree<T> (node, left, right.left);	
		node = right.node;
		right = right.right;
	}

	public void rotateRight ()
	{
		assert (left != null);
		right = new Tree<T> (node, left.right, right);
		node = left.node;
		left = left.left;
	}

	public boolean find (T value)
	{
		int res = node.compareTo (value);

		if (res < 0)
			return right == null ? false : right.find (value);
		else if (res > 0)
			return left == null ? false : left.find (value);
		else
			return true;
	}
	
	public void print ()
	{
		System.out.print ("<");
		if (left != null)
			left.print ();
		System.out.print ("|");
		System.out.print (node.toString ());
		System.out.print ("|");
		if (right != null)
			right.print ();
		System.out.print (">");
	}
}

