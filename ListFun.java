import apcs.Window;

public class ListFun {

	public static void main(String[] args) {
		// MyArrayList list = new MyArrayList();
		MyLinkedList list = new MyLinkedList();
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			list.insert(10, i);
		}
		System.out.println(list);
	}

}
