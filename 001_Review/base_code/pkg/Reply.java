package pkg;

import java.util.*;
import java.io.*;

public class Reply extends Message {
	String auth;
	String subj;
	String bod;
	int i;

	// Default Constructor
	public Reply() {

	}

	// Parameterized constructor
	public Reply(String auth, String subj, String bod, int i) {
		super(auth, subj, bod, i + 1);
	}

	// Returns if this is a reply (true)
	public boolean isReply() {
		return true;
	}
}
