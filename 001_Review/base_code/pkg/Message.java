package pkg;

import java.util.*;
import java.io.*;

public class Message {
	String auth;
	String subj;
	String bod;
	int i;
	int messageID;
	String subject;
	User currentUser;
	ArrayList<Message> childsList = new ArrayList<Message>();

	// Default Constructor
	public Message() {
		auth = "";
		subj = "";
		bod = "";
		i = 0;
	}

	// Parameterized Constructor
	public Message(String auth, String subj, String bod, int i) {
		this.auth = auth;
		this.subj = subj;
		this.bod = bod;
		this.i = i + 1;
	}

	// This function is responsbile for printing the Message
	// (whether Topic or Reply), and all of the Message's "subtree" recursively:

	// After printing the Message with indentation n and appropriate format (see
	// output details),
	// it will invoke itself recursively on all of the Replies inside its childList,
	// incrementing the indentation value at each new level.

	// Note: Each indentation increment represents 2 spaces. e.g. if indentation ==
	// 1, the reply should be indented 2 spaces,
	// if it's 2, indent by 4 spaces, etc.
	public void print(int indentation) {
		int indent = indentation;
		String ind = "";

		for (int c = 0; c < indent; c++) {
			ind = (ind + "  ");
		}

		System.out.println(ind + "Message #" + i + ": Subject: " + subj);
		System.out.println(ind + "User: " + auth + " said: " + bod);

		for (int c = 0; c < childsList.size(); c++) {
			indent = indentation + 1;
			childsList.get(c).print(indent);
		}
	}

	// Default function for inheritance
	public boolean isReply() {
		return false;
	}

	// Returns the subject String
	public String getSubject() {
		return subject;
	}

	// Returns the ID
	public int getId() {
		return messageID;
	}

	// Adds a child pointer to the parent's childList.
	public void addChild(Message child) {
		childsList.add(child);
	}

}
