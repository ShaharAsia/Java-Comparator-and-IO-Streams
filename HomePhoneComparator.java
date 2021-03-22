import java.util.Comparator;

public class HomePhoneComparator implements Comparator<Contact> {

	@Override
	public int compare(Contact o1, Contact o2) {
		return o1.getHomeNumber().compareTo(o2.getHomeNumber());
	}

}
