import java.io.Serializable;
import java.lang.Comparable;

public class Contact implements Comparable<Contact>,Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String lastName;
	private String homeNumber;
	private String mobileNumber;
	
	public Contact(String name, String lastName, String homeNumber, String mobileNumber) throws Exception {
		
		setName(name);
		setLastName(lastName);
		setHomeNumber(homeNumber);
		setMobileNumber(mobileNumber);
		if(this.homeNumber== "-" && this.mobileNumber =="-") {
			throw new Exception(
					"ADD/UPDATE CONTACT EXCEPTION: You Must Enter At Least One Valid, Mobile Or Home Number!\n");
		}
	}
	
	public Contact() { //constructor for binary search
		super();
	}
	
	@Override
	public int compareTo(Contact c) {
		
		double res1 = this.name.compareTo(c.name);
		if(res1 < 0) {
			return -1;
		} else if (res1 > 0) {
			return 1;
		} else {
			double res2 = this.lastName.compareTo(c.lastName);
			if(res2 < 0) {
				return -1;
			} else if (res2 > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) throws Exception {  
		if (name.length() >= 3) {
			this.name = name.toLowerCase();
		} else {
			throw new Exception("ADD/UPDATE CONTACT EXCEPTION: First Name Can Not Be Less Than 3 Letters!");
		}
		
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws Exception {
		if (lastName.length() >= 3) {
			this.lastName = lastName.toLowerCase();
		} else {
			throw new Exception("ADD/UPDATE CONTACT EXCEPTION: Last Name Can Not Be Less Than 3 Letters!");
		}
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) throws Exception {
		if (homeNumber.length()>2 && homeNumber.charAt(2) == '-') {
			homeNumber = homeNumber.substring(0,2) + homeNumber.substring(3,homeNumber.length());
		}
		if (homeNumber.length() != 9 || !checkDigits(homeNumber)) {
			this.homeNumber = "-"; //null
		} else {
			this.homeNumber = homeNumber.substring(0, 2) + "-"
					+ homeNumber.substring(2, homeNumber.length());
		}
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) throws Exception {
		if (mobileNumber.length()>3 && mobileNumber.charAt(3) == '-') {
			mobileNumber = mobileNumber.substring(0,3) + mobileNumber.substring(4,mobileNumber.length());
		}
		if (mobileNumber.length()!=10 || !checkDigits(mobileNumber)) {
			this.mobileNumber = "-"; //null
		} else {
			this.mobileNumber = mobileNumber.substring(0, 3) +"-"
					+ mobileNumber.substring(3, mobileNumber.length());
		}
	}
	
	public boolean checkDigits (String num) {
		for (int i = 0; i < num.length(); i++) {
			char digit = num.charAt(i);
			if (digit > '9' || digit <'0') {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return name + " " + lastName + " : " + homeNumber + "  " + mobileNumber;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Contact) || obj == null) return false; //different type
		if(obj == this) return true;
		Contact c = (Contact)obj;
		if (this.name.compareTo(c.name) == 0 && this.lastName.compareTo(c.lastName) == 0 ) {
			return true;
		}
		return false;
	}
	
}
