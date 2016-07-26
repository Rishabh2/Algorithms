public class MyLinkedList implements MyList {

	Node head;

	// O(n)
	@Override
	public void add(Integer i) {
		if (head == null) {
			head = new Node();
			head.setValue(i);
		} else {
			Node current = head;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			Node newNode = new Node();
			newNode.setValue(i);
			current.setNext(newNode);
		}

	}

	// O(n)
	@Override
	public Integer remove(int index) {
		if (index == 0) {
			Integer removed = head.getValue();
			head = head.getNext();
			return removed;
		}
		Node current = head;
		int count = 0;
		while (count < index - 1) {
			current = current.getNext();
		}

		Integer removed = current.getNext().getValue();
		current.setNext(current.getNext().getNext());
		return removed;
	}

	// O(n)
	@Override
	public Integer get(int index) {
		Node current = head;
		int count = 0;
		while (count < index) {
			current = current.getNext();
			count++;
		}
		return current.getValue();
	}

	// O(n)
	@Override
	public int size() {
		Node current = head;
		int count = 0;
		while (current != null) {
			count++;
			current = current.getNext();
		}
		return count;
	}

	// O(n)
	@Override
	public boolean contains(Integer i) {
		Node current = head;
		while (current != null) {
			if (current.getValue() == i) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	// O(n)
	@Override
	public void insert(Integer i, int index) {
		if (index == 0) {
			Node newNode = new Node();
			newNode.setValue(i);
			newNode.setNext(head);
			head = newNode;
			return;
		}
		Node current = head;
		int count = 0;
		while (count < index - 1) {
			current = current.getNext();
			count++;
		}

		Node newNode = new Node();
		newNode.setValue(i);

		newNode.setNext(current.getNext());
		current.setNext(newNode);
	}

	// O(n)
	public String toString() {
		String s = "LinkedList:";

		// Node current = head;
		// while (current != null) {
		// s = s + " " + current.value;
		// current = current.next;
		// }
		for (Node current = head; current != null; current = current.getNext()) {
			s = s + " " + current.getValue();
		}

		return s;
	}
}
