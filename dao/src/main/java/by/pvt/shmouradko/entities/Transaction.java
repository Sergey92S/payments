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
public class Transaction implements Serializable {
	private int sum;
	private int accountId;

	public int getSum() {
		return sum;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

}
