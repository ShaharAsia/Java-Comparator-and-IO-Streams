import java.util.Iterator;

public class ContactIterator implements Iterator<Contact> {
	ContactList list;
	int index;
	
	public ContactIterator(ContactList list) {
		this.list = list;
		this.index = -1;
	}
	@Override
	public boolean hasNext() {
		if(index+1 < list.size()) return true;
		return false;
	}
	@Override
	public Contact next() {
		if(this.hasNext()) return list.get(++index);
		return null;
	}
	
	public void remove() {
		if(index<=0 && index < list.size()) list.remove(index);
	}
	
}
