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
public class CreditCard implements Serializable {
	private String name;
	private int securitycode;
	private int personId;

	public String getName() {
		return name;
	}

	public int getSecuritycode() {
		return securitycode;
	}

	public int getPersonId() {
		return personId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSecuritycode(int securitycode) {
		this.securitycode = securitycode;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

}
