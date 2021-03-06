import java.io.Serializable;
import java.util.Random;

/* 
 * @author Alyssa Romero 2019 
 */

public class FlashCard implements Serializable {
		private String front;
		private String back;
	
	public FlashCard(String front, String back) {
		this.front = front;
		this.back = back;
	}
	
	public String getFront() {
		return this.front;
	}
	
	public String getBack() {
		return this.back;
	}
	
	public void printCard() {
		System.out.println(this.front + " ==> " + this.back);
	}
	
	public String chooseSide() {
		Random random = new Random();
		double choice = random.nextDouble();
		if (choice < .5) {
			return this.front;
		}
		else {
			return this.back;
		}
	}
}
