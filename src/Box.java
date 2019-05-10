import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Box implements Serializable {

	private ArrayList<FlashCard> cards;
	private int index;
	
	public Box(int index) {
		this.cards = new ArrayList<FlashCard>();
		this.index = index;
	}
	
	public int getSize() {
		return this.cards.size();
	}
	
	public void printCards() {
		for (int i = 0; i < cards.size(); i++) {
			System.out.println(cards.get(i));
		}
	}
	
	public void addCard(FlashCard card) {
		cards.add(card);
	}
	
	public void removeCard(FlashCard card) {
		cards.remove(card);
	}
	
	public FlashCard chooseCard() {
		Random random = new Random();
		int index = random.nextInt(this.cards.size());
		return cards.get(index);
	}
	
	public boolean isEmpty() {
		if (this.cards.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
}
