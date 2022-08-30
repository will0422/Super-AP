package pkg;

import java.util.*;
import java.io.*;

public class Topic extends Message {
	String auth;
	String subj;
	String bod;
	int i;

	// Default Constructor
	public Topic() {

	}

	// Parameterized constructor
	public Topic(String auth, String subj, String bod, int i) {
		super(auth, subj, bod, i + 1);
	}

	// Returns if it's a reply (false)
	public boolean isReply() {
		return false;
	}
}
