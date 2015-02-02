package foo;

import java.util.Hashtable;

public class FormBean {
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
	private String password1;
	private String password2;
	private String zip;
	private String[] faveMusic;
	private String notify;
	private Hashtable errors;

	public boolean validate() {
		boolean allOk = true;
		if (firstName.equals("")) {
			errors.put("firstName", "Please enter your first name");
			firstName = "";
			allOk = false;
		}
		if (lastName.equals("")) {
			errors.put("lastName", "Please enter your last name");
			lastName = "";
			allOk = false;
		}
		if (email.equals("") || (email.indexOf('@') == -1)) {
			errors.put("email", "Please enter a valid email address");
			email = "";
			allOk = false;
		}
		if (userName.equals("")) {
			errors.put("userName", "Please enter a username");
			userName = "";
			allOk = false;
		}
		if (password1.equals("")) {
			errors.put("password1", "Please enter a valid password");
			password1 = "";
			allOk = false;
		}
		if (!password1.equals("") && (password2.equals("") || !password1.equals(password2))) {
			errors.put("password2", "Please confirm your password");
			password2 = "";
			allOk = false;
		}
		if (zip.equals("") || zip.length() != 5) {
			errors.put("zip", "Please enter a valid zip code");
			zip = "";
			allOk = false;
		} else {
			try {
				int x = Integer.parseInt(zip);
			} catch (NumberFormatException e) {
				errors.put("zip", "Please enter a valid zip code");
				zip = "";
				allOk = false;
			}
		}
		return allOk;
	}

	public String getErrorMsg(String s) {
		String errorMsg = (String) errors.get(s.trim());
		return (errorMsg == null) ? "" : errorMsg;
	}

	public FormBean() {
		firstName = "";
		lastName = "";
		email = "";
		userName = "";
		password1 = "";
		password2 = "";
		zip = "";
		faveMusic = new String[] { "1" };
		notify = "";
		errors = new Hashtable();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword1() {
		return password1;
	}

	public String getPassword2() {
		return password2;
	}

	public String getZip() {
		return zip;
	}

	public String getNotify() {
		return notify;
	}

	public String[] getFaveMusic() {
		return faveMusic;
	}

	public String isCbSelected(String s) {
		boolean found = false;
		if (!faveMusic[0].equals("1")) {
			for (int i = 0; i < faveMusic.length; i++) {
				if (faveMusic[i].equals(s)) {
					found = true;
					break;
				}
			}
			if (found)
				return "checked";
		}
		return "";
	}

	public String isRbSelected(String s) {
		return (notify.equals(s)) ? "checked" : "";
	}

	public void setFirstName(String fname) {
		firstName = fname;
	}

	public void setLastName(String lname) {
		lastName = lname;
	}

	public void setEmail(String eml) {
		email = eml;
	}

	public void setUserName(String u) {
		userName = u;
	}

	public void setPassword1(String p1) {
		password1 = p1;
	}

	public void setPassword2(String p2) {
		password2 = p2;
	}

	public void setZip(String z) {
		zip = z;
	}

	public void setFaveMusic(String[] music) {
		faveMusic = music;
	}

	public void setErrors(String key, String msg) {
		errors.put(key, msg);
	}

	public void setNotify(String n) {
		notify = n;
	}
}
