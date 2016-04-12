/**
 * 
 */
package by.pvt.shmouradko.entities;

import java.io.Serializable;

/**
 * @author Shmouradko Sergey
 *
 */
@SuppressWarnings("serial")
public class Person implements Serializable {
	private String name;
	private String surname;
	private String login;
	private String password;
	private int role;

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public int getRole() {
		return role;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(int role) {
		this.role = role;
	}
}
