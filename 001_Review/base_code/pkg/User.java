package pkg;

import java.util.*;
import java.io.*;

public class User {
	private String username;
	private String password;

	// Creates a User with empty name and password.
	public User() {
		username = "";
		password = "";
	}

	// Creates a User with given username and password.
	public User(String usr, String pwd) {
		username = usr;
		password = pwd;
	}

	// Returns the username
	public String getUsername() {
		return username;
	}

	// Returns true if the stored username/password matches the parameters.
	// Otherwise returns false.
	// Note that, even with a User with empty name and password, this is actually a
	// valid User object (it is the default User),
	// This function must still return false if given an empty username string.
	public boolean check(String usr, String psd) {
		boolean hasLogin = false;
		if (usr != username || usr == "") {
			return hasLogin;
		} else {
			if (psd != password) {
				return hasLogin;
			} else {
				hasLogin = true;
				return hasLogin;
			}
		}
	}

	// Sets a new password.
	// This function should only set the password if the current (old) password is
	// passed in.
	// Also, a default User cannot have its password changed.
	// Return true if password changed, return false if not.
	public boolean setPassword(String oldPass, String newPass) {
		boolean hasLogin = false;
		if (oldPass != password || oldPass == "") {
			return hasLogin;
		} else {
			password = newPass;
			hasLogin = true;
			return hasLogin;
		}
	}

	public String toString() {
		return username;
	}
}
