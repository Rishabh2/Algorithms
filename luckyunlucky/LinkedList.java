package luckyunlucky;

public class LinkedList<T> {
	private Node<T> head;

	// O(n)
	public T remove(int index) {
		if (index == 0) {
			T data = head.getData();
			head = head.getNext();
			return data;
		} else {
			Node<T> current = head;
			int count = 0;
			while (count < index - 1) {
				current = current.getNext();
				count++;
			}

			T data = current.getNext().getData();
			current.setNext(current.getNext().getNext());
			return data;
		}
	}

	// O(n)
	public void insert(T object, int index) {
		if (index == 0) {
			Node<T> newNode = new Node<T>();
			newNode.setData(object);
			newNode.setNext(head);
			head = newNode;
		} else {
			Node<T> current = head;
			int count = 0;
			while (count < index - 1) {
				current = current.getNext();
				count++;
			}

			Node<T> newNode = new Node<T>();
			newNode.setData(object);

			newNode.setNext(current.getNext());
			current.setNext(newNode);

		}
	}

	// O(n)
	public T get(int index) {
		Node<T> current = head;
		int count = 0;
		while (count < index) {
			current = current.getNext();
			count++;
		}
		return current.getData();
	}

	// O(n)
	public void add(T object) {
		if (head == null) {
			head = new Node<T>();
			head.setData(object);
		} else {
			Node<T> current = head;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			Node<T> newNode = new Node<T>();
			newNode.setData(object);
			current.setNext(newNode);
		}
	}

	// O(n)
	public int size() {
		Node<T> current = head;
		int count = 0;
		while (current != null) {
			current = current.getNext();
			count++;
		}
		return count;
	}

	public String toString() {
		String s = "LinkedList: ";
		Node<T> current = head;
		while (current != null) {
			s += current.getData() + " ";
			current = current.getNext();
		}
		return s;
	}

}
