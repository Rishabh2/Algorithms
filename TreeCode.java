public class TreeCode {
	Node root;

	public void add(Integer value) {
		if (root == null) {
			root = new Node(value);
		} else {
			add(value, root);
		}
	}

	private void add(Integer value, Node current) {
		if (value < current.getValue()) {
			if (current.getLeft() == null) {
				current.setLeft(new Node(value));
			} else {
				add(value, current.getLeft());
			}
		} else {
			if (current.getRight() == null) {
				current.setRight(new Node(value));
			} else {
				add(value, current.getRight());
			}
		}

	}

	public void delete(Integer value) {
		root = delete(root, value);
	}

	private Node delete(Node x, Integer value) {
		if (x == null) {
			return null;
		}
		int cmp = value.compareTo(x.getValue());
		if (cmp < 0)
			x.setLeft(delete(x.getLeft(), value));
		else if (cmp > 0)
			x.setRight(delete(x.getRight(), value));
		else {
			if (x.getRight() == null)
				return x.getLeft();
			if (x.getLeft() == null)
				return x.getRight();
			Node t = x;
			x = min(t.getRight());
			x.setRight(deleteMin(t.getRight()));
			x.setLeft(t.getLeft());
		}
		return x;
	}

	private Node min(Node x) {
		if (x.getLeft() == null)
			return x;
		else
			return min(x.getLeft());
	}

	private Node deleteMin(Node x) {
		if (x.getLeft() == null)
			return x.getRight();
		x.setLeft(deleteMin(x.getLeft()));
		return x;
	}

	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(Node current) {
		if (current != null) {
			inOrder(current.getLeft());
			System.out.println(current.getValue());
			inOrder(current.getRight());
		}

	}

}
