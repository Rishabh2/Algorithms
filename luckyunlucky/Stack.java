package luckyunlucky;

public class Stack<T> implements List<T> {
	private LinkedList<T> list;

	public Stack() {
		list = new LinkedList<T>();
	}

	@Override
	public void add(T object) {
		list.insert(object, 0);

	}

	@Override
	public T remove() {
		return list.remove(0);
	}

	@Override
	public T peek() {
		return list.get(0);
	}

	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

}
