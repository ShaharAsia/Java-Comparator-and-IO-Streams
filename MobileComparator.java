import java.util.Comparator;

public class MobileComparator implements Comparator<Contact> {

	@Override
	public int compare(Contact o1, Contact o2) {
		return o1.getMobileNumber().compareTo(o2.getMobileNumber());
	}

}
