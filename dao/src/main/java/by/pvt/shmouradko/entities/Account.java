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
public class Account implements Serializable {
	private int count;
	private int creditcardId;
	private int status;

	public int getCount() {
		return count;
	}

	public int getCreditCardId() {
		return creditcardId;
	}

	public int getStatus() {
		return status;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setCreditCardId(int creditcardId) {
		this.creditcardId = creditcardId;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
