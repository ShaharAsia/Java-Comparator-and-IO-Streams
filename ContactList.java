import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class ContactList extends LinkedList<Contact> implements Iterable<Contact> {
	
	private static final long serialVersionUID = 1L;
	private Comparator<Contact> Comp ; //name/home/mobile
	
	public Iterator<Contact> iterator() {
		return new ContactIterator(this);
	}
	
	public ContactList() {
		Comp = new NameComparator();
	}

	
	public Comparator<Contact> getComp() {
		return Comp;
	}

	public void setComp(Comparator<Contact> comp) {
		Comp = comp;
	}

	public void sort() { //sort list by Comparator type
		Collections.sort(this,Comp);
	}

	@Override
	public boolean add(Contact c) {
		if(c == null) return false;
		int index = Collections.binarySearch(this,c,this.Comp);
		if(index >= 0) { //if c already exists
			this.remove(index);
			super.add(index,c);
		} else {
			super.add(-index-1,c);
		}
		return true;
	}

	@Override
	public Contact remove(int index) {
		if (index >= this.size() || index <0) return null;
		else {
			return super.remove(index);
		}
	}

	public Contact get(int index) {
		if (index >= this.size() || index <0) return null;
		else {
			return super.get(index);
		}
	}
	
	public void saveToFile() {
		if(this.size()==0) {
			System.out.println("List Is Empty.");
			return;
		}
		try (ObjectOutputStream Out = new ObjectOutputStream
				(new BufferedOutputStream(new FileOutputStream("contacts.obj")))) {
				Out.writeInt(this.size());
				for (Contact c : this) {
					Out.writeObject(c);
				}
					System.out.println("Contacts Saved To File: " + this.size());
				
		} catch(IOException e) {
		}
	}
	
	public void loadFromFile() {
		int num = 0;
		try (ObjectInputStream in = new ObjectInputStream
				(new BufferedInputStream(new FileInputStream("contacts.obj")))) {
				num = in.readInt();
				int previousContacts = this.size();
				for (int i = 0; i < num; i++) {
					add((Contact)in.readObject());
				}
				int addedContacts = this.size()-previousContacts;
				System.out.print("Contacts Loaded From File: ");
				System.out.println(" Added: " + addedContacts +
							" Updated: " + (num-addedContacts));
				
		} catch(IOException e) {
			if (num ==0) System.out.println("File is empty");
		} catch(ClassNotFoundException ex) {
		}		
	}
	
	public void printContact(int index) {
		Contact c = this.get(index);
		System.out.println(c.toString());
	}	
	
}
