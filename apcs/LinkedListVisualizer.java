package apcs;
import java.util.LinkedList;

public class LinkedListVisualizer {
	public static LinkedList<VisualList> lists;

	public static void main(String[] args) {
		Window.size(600, 600);
		lists = new LinkedList<VisualList>();
		VisualList list = new VisualList();
		lists.add(list);
		list.add(1);
		list.add(2);
		
	}

	public static VisualList mergesort(VisualList list) {
		return merge(split(list),list);
	}

	public static VisualList split(VisualList list) {
		VisualList list2 = new VisualList(list.level + 1);
		lists.add(list2);
		int size = list.size();
		for (int i = size / 2; i < list.size();) {
			list2.add(list.remove(i));
		}
		return list2;
	}

	public static VisualList merge(VisualList list, VisualList list2) {
		if (list.size() == 0) {
			return list2;
		}
		if (list2.size() == 0) {
			return list;
		}
		if (list.size() == 1 && list2.size() == 1) {
			VisualList merged = new VisualList(list2.level + 1);
			if(list.get(0)>list2.get(0)){
				merged.add(list2.remove(0));
				merged.add(list.remove(0));
			}
			else{
				merged.add(list.remove(0));
				merged.add(list2.remove(0));
			}
			lists.add(merged);
			return merged;
		} else{
			return merge(mergesort(list),mergesort(list2));
		}
	}
}