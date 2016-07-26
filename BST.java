import java.util.ArrayList;

public class BST {

	Node root;

	// Smaller to the left
	// Greater or equal to to the right
	public BST() {
		root = null;
	}

	public int[] toArray() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		toArray(root, list);
		int[] array = new int[list.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	private void toArray(Node current, ArrayList<Integer> list) {
		if (current != null) {
			toArray(current.getLeft(), list);
			list.add(current.getValue());
			toArray(current.getRight(), list);
		}
	}

	public void inOrderPrint() {
		System.out.print("In Order: ");
		inOrderPrint(root);
		System.out.println();
	}

	private void inOrderPrint(Node current) {
		if (current != null) {
			inOrderPrint(current.getLeft());
			System.out.print(current.getValue() + " ");
			inOrderPrint(current.getRight());
		}
	}

	public void postOrderPrint() {
		System.out.print("Post Order: ");
		postOrderPrint(root);
		System.out.println();
	}

	private void postOrderPrint(Node current) {
		if (current != null) {
			postOrderPrint(current.getLeft());
			postOrderPrint(current.getRight());
			System.out.print(current.getValue() + " ");
		}
	}

	public void preOrderPrint() {
		System.out.print("Pre Order: ");
		preOrderPrint(root);
		System.out.println();
	}

	private void preOrderPrint(Node current) {
		if (current != null) {
			System.out.print(current.getValue() + " ");
			preOrderPrint(current.getLeft());
			preOrderPrint(current.getRight());
		}
	}

	public void add(Integer value) {
		if (root == null) {
			root = new Node(value);
		} else {
			add(value, root);
		}
	}

	private void add(Integer value, Node current) {
		if (value < current.getValue()) {
			if (current.hasLeft()) {
				add(value, current.getLeft()); // recursive call
			} else {
				current.setLeft(new Node(value)); // base case
			}
		} else {
			if (current.hasRight()) {
				add(value, current.getRight()); // recursive call
			} else {
				current.setRight(new Node(value)); // base case
			}
		}
	}

	public boolean contains(Integer value) {
		return contains(value, root);
	}

	private boolean contains(Integer value, Node current) {
		if (current == null) {
			return false;
		} else if (current.getValue() == value) {
			return true;
		} else if (value < current.getValue()) {
			return contains(value, current.getLeft());
		} else {
			return contains(value, current.getRight());
		}
	}

	public boolean isEmpty() {
		return root == null;
	}

	public int depth(Integer value) {
		if (!contains(value)) {
			return -1;
		} else {
			return depth(value, root);
		}
	}

	private int depth(Integer value, Node current) {
		if (current.getValue() == value) {
			return 0;
		} else if (value < current.getValue()) {
			return 1 + depth(value, current.getLeft());
		} else {
			return 1 + depth(value, current.getRight());
		}
	}

	public int depth() {
		// return the maximum depth of my tree
		// return depth(root, -1);
		return depth(root);
	}

	private int depth(Node current, int depth) {
		if (current == null) {
			return depth;
		}
		return Math.max(depth(current.getLeft(), depth + 1),
				depth(current.getRight(), depth + 1));
	}

	private int depth(Node current) {
		if (current == null) {
			return -1;
		}
		return Math.max(depth(current.getLeft()), depth(current.getRight())) + 1;
	}

	public Integer min() {
		if (isEmpty()) {
			return null;
		}
		return min(root).getValue();
	}

	private Node min(Node current) {
		if (!current.hasLeft()) {
			return current;
		}
		return min(current.getLeft());
	}

	public void remove(Integer value) {
		if (contains(value)) {
			root = remove(value, root);
		}
	}

	private Node remove(Integer value, Node current) {
		if (value < current.getValue()) {
			current.setLeft(remove(value, current.getLeft()));
			return current;
		} else if (value > current.getValue()) {
			current.setRight(remove(value, current.getRight()));
			return current;
		} else {
			if (!current.hasRight()) {
				return current.getLeft();
			}
			if (!current.hasLeft()) {
				return current.getRight();
			}
			Node minimum = min(current.getRight());
			minimum.setRight(deleteMin(current.getRight()));
			minimum.setLeft(current.getLeft());
			return minimum;
		}
	}

	private Node deleteMin(Node current) {
		if (!current.hasLeft()) {
			return current.getRight();
		}
		current.setLeft(deleteMin(current.getLeft()));
		return current;
	}

}
