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
		auth = "";
		subj = "";
		bod = "";
		i = 0;
	}

	// Parameterized constructor
	public Topic(String auth, String subj, String bod, int i) {
		this.auth = auth;
		this.subj = subj;
		this.bod = bod;
		this.i = i;
	}

	// Returns if it's a reply (false)
	public boolean isReply() {
		return false;
	}
}
