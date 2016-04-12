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
public class UserInfo implements Serializable {
	private int count;
	private int status;
	private int securitycode;
	private String name;

	public int getCount() {
		return count;
	}

	public int getStatus() {
		return status;
	}

	public int getSecuritycode() {
		return securitycode;
	}

	public String getName() {
		return name;
	}

	public UserInfo setCount(int count) {
		this.count = count;
		return this;
	}

	public UserInfo setStatus(int status) {
		this.status = status;
		return this;
	}

	public UserInfo setSecuritycode(int securitycode) {
		this.securitycode = securitycode;
		return this;
	}

	public UserInfo setName(String name) {
		this.name = name;
		return this;
	}

}
