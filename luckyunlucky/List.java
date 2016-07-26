package luckyunlucky;

public interface List<T> {
	public void add(T object);

	public T remove();

	public T peek();

	public boolean isEmpty();
}
