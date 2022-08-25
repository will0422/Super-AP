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
		auth = "";
		subj = "";
		bod = "";
		i = 0;
	}

	// Parameterized constructor
	public Reply(String auth, String subj, String bod, int i) {
		this.auth = auth;
		this.subj = subj;
		this.bod = bod;
		this.i = i;
	}

	// Returns if this is a reply (true)
	public boolean isReply() {
		return true;
	}
}
