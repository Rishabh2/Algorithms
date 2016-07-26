public class TreeTest {

	public static void main(String[] args) {
		BST tree = new BST();
		tree.add(5);
		tree.add(2);
		tree.add(1);
		tree.add(3);
		tree.add(7);
		tree.add(9);
		tree.add(8);
		tree.remove(5);
		System.out.println(tree.depth(9));
		System.out.println(tree.depth(8));
		System.out.println(tree.depth());
		tree.inOrderPrint();
	}

}
