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
public class AdminInfo implements Serializable {
	private int count;
	private int status;
	private int securitycode;
	private String login;
	private String creditcard;

	public int getCount() {
		return count;
	}

	public int getStatus() {
		return status;
	}

	public int getSecuritycode() {
		return securitycode;
	}

	public String getLogin() {
		return login;
	}

	public String getCreditcard() {
		return creditcard;
	}

	public AdminInfo setCount(int count) {
		this.count = count;
		return this;
	}

	public AdminInfo setStatus(int status) {
		this.status = status;
		return this;
	}

	public AdminInfo setSecuritycode(int securitycode) {
		this.securitycode = securitycode;
		return this;
	}

	public AdminInfo setLogin(String login) {
		this.login = login;
		return this;
	}

	public AdminInfo setCreditcard(String creditcard) {
		this.creditcard = creditcard;
		return this;
	}

}
