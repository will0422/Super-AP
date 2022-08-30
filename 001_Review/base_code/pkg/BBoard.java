package pkg;

import java.util.Scanner;
import java.util.*;
import java.io.*;

public class BBoard { // This is your main file that connects all classes.
	Scanner sc = new Scanner(System.in);

	User currentUser;

	ArrayList<String> username = new ArrayList<String>();

	ArrayList<String> password = new ArrayList<String>();

	ArrayList<Message> messageList = new ArrayList<Message>();

	String empty = "empty";

	int zero = 0;

	String userPassword;

	public void Quit() {
		System.out.println("Bye!");
		System.exit(0);
	}
	// Think about what your global variables need to be.

	// Default constructor that creates a board with a defaulttitle, empty user and
	// message lists,
	// and no current user
	public BBoard() {

	}

	// Same as the default constructor except it sets the title of the board
	public BBoard(String ttl) {
	}

	// Gets a filename of a file that stores the user info in a given format
	// (users.txt)
	// Opens and reads the file of all authorized users and passwords
	// Constructs a User object from each name/password pair, and populates the
	// userList ArrayList.
	public void loadUsers(String inputFile) throws FileNotFoundException {
		File users = new File(inputFile);

		Scanner fileReader = new Scanner(users);

		while (fileReader.hasNextLine()) {
			String line = fileReader.nextLine();
			int lineLength = line.length();
			int spacePos = line.indexOf(" ");
			username.add(line.substring(0, spacePos));
			spacePos++;
			password.add(line.substring(spacePos, lineLength));
		}
	}

	// Asks for and validates a user/password.
	// This function asks for a username and a password, then checks the userList
	// ArrayList for a matching User.
	// If a match is found, it sets currentUser to the identified User from the list
	// If not, it will keep asking until a match is found or the user types 'q' or
	// 'Q' as username to quit
	// When the users chooses to quit, sayu "Bye!" and return from the login
	// function
	public void login() {
		System.out.println("Welcome to William's Awesome BBoard");
		while (true) {
			boolean hasLogin = false;
			System.out.println("Enter your username ('Q' or 'q' to quit):");
			String userUsername = sc.nextLine();
			if (userUsername.equals("q") || userUsername.equals("Q")) {
				Quit();
			}
			System.out.println("Enter your password:");
			userPassword = sc.nextLine();
			for (int c = 0; c < username.size(); c++) {
				if (userUsername.equals(username.get(c)) && userPassword.equals(password.get(c))) {
					System.out.println("Welcome back " + userUsername);
					currentUser = new User(userUsername, userPassword);
					hasLogin = true;
					return;
				}
			}
			if (hasLogin == false) {
				System.out.println("Invalid username or password, please try again or quit('Q' or 'q')");
				System.out.println(" ");
			}
		}
	}

	// Contains main loop of Bulletin Board
	// IF and ONLY IF there is a valid currentUser, enter main loop, displaying menu
	// items
	// --- Display Messages ('D' or 'd')
	// --- Add New Topic ('N' or 'n')
	// --- Add Reply ('R' or 'r')
	// --- Change Password ('P' or 'p')
	// --- Quit ('Q' or 'q')
	// With any wrong input, user is asked to try again
	// Q/q should reset the currentUser to 0 and then end return
	// Note: if login() did not set a valid currentUser, function must immediately
	// return without showing menu
	public void run() {
		System.out.println("Menu:");
		System.out.println(" --- Display Messages ('D' or 'd')");
		System.out.println(" --- Add New Topic ('N' or 'n')");
		System.out.println(" --- Add Reply ('R' or 'r')");
		System.out.println(" --- Change Password ('P' or 'p')");
		System.out.println(" --- Quit ('Q' or 'q')");
		System.out.println("Choose an action:");
		String input = sc.nextLine();
		if (input.equals("d") || input.equals("D")) {
			display();
		}
		if (input.equals("n") || input.equals("N")) {
			addTopic();
		}
		if (input.equals("R") || input.equals("r")) {
			addReply();
		}
		if (input.equals("P") || input.equals("p")) {
			setPassword();
		}
		if (input.equals("Q") || input.equals("q")) {
			Quit();
		}
		if (!input.equals("d") || !input.equals("D") || !input.equals("n") || !input.equals("N") || !input.equals("R")
				|| !input.equals("r") || !input.equals("P") || !input.equals("p") || !input.equals("Q")
				|| !input.equals("q")) {
			System.out.println("Enter a valid option.");
		}
		run();
	}

	// Traverse the BBoard's message list, and invote the print function on Topic
	// objects ONLY
	// It will then be the responsibility of the Topic object to invoke the print
	// function recursively on its own replies
	// The BBoard display function will ignore all reply objects in its message list
	private void display() {
		Message msg = new Message();

		System.out.println("-----------------------------------------------");
		for (int c = 0; c < messageList.size(); c++) {
			msg = messageList.get(c);

			if (msg.isReply() == false) {
				msg.print(0);
			}
		}
		System.out.println("-----------------------------------------------");
	}

	// This function asks the user to create a new Topic (i.e. the first message of
	// a new discussion "thread")
	// Every Topic includes a subject (single line), and body (single line)

	/*
	 * Subject: "Thanks"
	 * Body: "I love this bulletin board that you made!"
	 */

	// Each Topic also stores the username of currentUser; and message ID, which is
	// (index of its Message + 1)

	// For example, the first message on the board will be a Topic who's index will
	// be stored at 0 in the messageList ArrayList,
	// so its message ID will be (0+1) = 1
	// Once the Topic has been constructed, add it to the messageList
	// This should invoke your inheritance of Topic to Message
	private void addTopic() {
		System.out.println("Subject: ");

		String sub = sc.nextLine();

		System.out.println("Body: ");

		String bod = sc.nextLine();

		int c = messageList.size();

		String auth = currentUser.getUsername();

		Topic t = new Topic(auth, sub, bod, c);
		messageList.add(t);
	}

	// This function asks the user to enter a reply to a given Message (which may be
	// either a Topic or a Reply, so we can handle nested replies).
	// The addReply function first asks the user for the ID of the Message to which
	// they are replying;
	// if the number provided is greater than the size of messageList, it should
	// output and error message and loop back,
	// continuing to ask for a valid Message ID number until the user enters it or
	// -1.
	// (-1 returns to menu, any other negative number asks again for a valid ID
	// number)

	// If the ID is valid, then the function asks for the body of the new message,
	// and constructs the Reply, pushing back the Reply on to the messageList.
	// The subject of the Reply is a copy of the parent Topic's subject with the
	// "Re: " prefix.
	// e.g., suppose the subject of message #9 was "Thanks", the user is replying to
	// that message:

	/*
	 * Enter Message ID (-1 for Menu): 9
	 * Body: It was a pleasure implementing this!
	 */

	// Note: As before, the body ends when the user enters an empty line.
	// The above dialog will generate a reply that has "Re: Thanks" as its subject
	// and "It was a pleasure implementing this!" as its body.

	// How will we know what Topic this is a reply to?
	// In addition to keeping a pointer to all the Message objects in BBoard's
	// messageList ArrayList
	// Every Message (wheather Topic or Reply) will also store an ArrayList of
	// pointers to all of its Replies.
	// So whenever we build a Reply, we must immediately store this Message in the
	// parent Message's list.
	// The Reply's constructor should set the Reply's subject to "Re: " + its
	// parent's subject.
	// Call the addChild function on the parent Message to push back the new Message
	// (to the new Reply) to the parent's childList ArrayList.
	// Finally, push back the Message created to the BBoard's messageList.
	// Note: When the user chooses to return to the menu, do not call run() again -
	// just return fro mthis addReply function.
	private void addReply() {
		System.out.println("Enter Message ID: ");
		int replyID = sc.nextInt();
		replyID++;

		if (replyID > messageList.size() || replyID < 0) {
			System.out.println("Invalid ID");
			addReply();
		} else {
			System.out.println("Body: ");
			String bod = sc.nextLine();

			int c = messageList.size();
			String user = currentUser.getUsername();

			String sub = "Re: " + messageList.get(replyID - 1).getSubject();

			Reply r = new Reply(user, sub, bod, c);

			messageList.get(replyID - 1).addChild(r);

			messageList.add(r);
		}
		return;
	}

	// This function allows the user to change their current password.
	// The user is asked to provide the old password of the currentUser.
	// If the received password matches the currentUser password, then the user will
	// be prompted to enter a new password.
	// If the received password doesn't match the currentUser password, then the
	// user will be prompted to re-enter the password.
	// The user is welcome to enter 'c' or 'C' to cancel the setting of a password
	// and return to the menu.
	// Any password is allowed except 'c' or 'C' for allowing the user to quit out
	// to the menu.
	// Once entered, the user will be told "Password Accepted." and returned to the
	// menu.
	private void setPassword() {
		System.out.println("Enter your old password: ");
		String newPas;
		String oldPas = sc.nextLine();
		if (oldPas.equals("C") || oldPas.equals("c")) {
			run();
		} else if (currentUser.check(currentUser.getUsername(), oldPas)) {
			System.out.println("Enter your new password: ");
			newPas = sc.nextLine();
			if (newPas.equals(oldPas)) {
				System.out.println("Your password cannot be the same as your old password.");
				setPassword();
			}
			if (newPas.equals("")) {
				System.out.println("Enter a valid password.");
				setPassword();
			}
			if (currentUser.setPassword(oldPas, newPas)) {
				System.out.println("Password accepted.");
				currentUser = new User(currentUser.getUsername(), newPas);
				run();
			}
		} else {
			System.out.println("Wrong password, try again or return to menu ('C' or 'c')");
			setPassword();
		}
	}
}
