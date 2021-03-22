
import java.util.Collections;
import java.util.Scanner;

public class Hw02_ShaharAsia { //ID - 316290345
	public static void main(String[] args) {
		
		char option=0;
		char o;
		Scanner scn = new Scanner(System.in);
		ContactList contacts = new ContactList(); //create new list
		do {
			printMenu();
			String str = scn.nextLine();
			if(str.length()>1) {
				break;
			}
			option =str.charAt(0);
			switch (option) {
			
			case '1': //add
				System.out.println("ADD/UPDATE CONTACT:");
				addContact(scn,contacts);
				break;
			case '2': //remove
				System.out.println("REMOVE CONTACT:");
				o = getOption(scn);
				removeContact(scn,contacts,o);
			break;
			case '3': //save
				contacts.saveToFile();
				break;
			case '4': //load
				contacts.loadFromFile();
				break;
			case '5': //sort by type
				o = getOption(scn);
				sortList(contacts,o);
				break;
			case '6': //search
				o = getOption(scn);
				searchContact(scn,contacts,o);
				break;
			case '7': //print
				printAllContacts(contacts);
				break;
			}
		} while (option >'0' && option < '8');
	}

	private static char getOption(Scanner scn) {
		char o = '0';
		String s = null;
		do {
			if(s != null) System.out.println("Wrong input!");
			System.out.print( "1. By Name\r\n" + 
					"2. By Home\r\n" + 
					"3. By Mobile\r\n" + 
					"Your Option: ");
			s = scn.nextLine();
			o = s.charAt(0);
		} while (o < '1' || o > '3' || s.length() != 1); //while wrong input
		return o;
}
	
	private static void printAllContacts(ContactList contacts) {
		System.out.println("Contacts:");
		int numOfcontacts = contacts.size();
		if(numOfcontacts == 0) {
			System.out.println("List is Empty!");
		} else {
			for (int i = 0; i < numOfcontacts; i++) 
				contacts.printContact(i);
		}
	}

	private static void printMenu() {
		System.out.print("- - - - - - - - - - - - - - -\r\n" + 
				"- - - Contact Creator : - - -\r\n" + 
				"- - - - - - - - - - - - - - -\r\n" + 
				"1. Add/Update Contact\r\n" + 
				"2. Remove Contact\r\n" + 
				"3. Save Contacts To File\r\n" + 
				"4. Load Contacts From File\r\n" + 
				"5. Sort Contacts\r\n" + 
				"6. Search Contact\r\n" + 
				"7. Print All Contacts\r\n" + 
				"- - - - - - - - - - - - - - -\r\n" + 
				"Choose your option or any other key to EXIT.\n"
				+ "Your Option: ");
	}

	private static void addContact(Scanner scn, ContactList list) {
		
		System.out.print("Enter First Name:");
		String name = scn.nextLine();
		System.out.print("Enter Last Name:");
		String lastName = scn.nextLine();
		System.out.print("Enter Home Number:");
		String Home = scn.nextLine();
		System.out.print("Enter Mobile Number:");
		String Mobile = scn.nextLine();
		
		try {
			Contact c = new Contact(name, lastName, Home, Mobile);
			int before = list.size();
			if (list.add(c)) { //if added successfully
				if(list.size() == before) System.out.println("Result: Contact Updated!");
				else System.out.println("Result: Contact Added!");
			} else {
				System.out.println("Failed created Contact!");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage()); //print the wrong action
		}

	}

	private static void removeContact(Scanner scn, ContactList list,char Type) {
		
		int index = searchContact(scn, list, Type);
		if (index >= 0) {
			System.out.print("Are You Sure? y/n: ");
			char option=scn.nextLine().toUpperCase().charAt(0);
			if(option == 'Y')
				list.remove(index);
				System.out.println("Result: Contact Removed!");
		}
	}
	
	private static int searchContact(Scanner scn, ContactList list,char Type) {
		int index = 0;
		sortList(list, Type);
		Contact c = new Contact();
		try {
			if(Type == '1') { //by name
				System.out.print("Enter First Name:");
				c.setName(scn.nextLine());
				System.out.print("Enter Last Name:");
				c.setLastName(scn.nextLine());

			} else if (Type == '2') { //by home
				System.out.print("Enter Home number:");
				c.setHomeNumber(scn.nextLine());
				
			} else { //by mobile
				System.out.print("Enter Mobile number:");
				c.setMobileNumber(scn.nextLine());
			}
			
			index = Collections.binarySearch(list,c,list.getComp()); //search contact c
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		if (index <0) System.out.println("Result: Contact Not Found!");
		else list.printContact(index);
		return index;
	}
	
	private static void sortList(ContactList list,char Type) {
		
		switch(Type) {
		 	case '1':
		 		list.setComp(new NameComparator());
		 		break;
		 	case '2':
		 		list.setComp(new HomePhoneComparator());
		 		break;
		 	case '3':
		 		list.setComp(new MobileComparator());
		 		break;
		}
		
		list.sort();
		
	}
}
