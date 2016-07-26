package luckyunlucky;

public class Queue<T> implements List<T> {
	private LinkedList<T> list;

	public Queue() {
		list = new LinkedList<T>();
	}

	@Override
	public void add(T object) {
		list.add(object);
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
