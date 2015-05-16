package datastructures;

import java.util.Iterator;
import java.util.Set;

class TableIterator<ItemType> implements Iterator<ItemType> {
	
	final private Table<ItemType> table;
	final private String[] elementNames;
	private int index;
	
	public TableIterator(Table<ItemType> t, Set<String> elementNames) {
		table = t;
		this.elementNames = elementNames.toArray(new String[] {});
		index = 0;
	}
	
	public boolean hasNext() {
		return index < elementNames.length;
	}

	@SuppressWarnings("unchecked")
	public ItemType next() {
		return (ItemType) elementNames[index++];
	}

	public void remove() {
		table.remove(elementNames[index]);
	}
	
}