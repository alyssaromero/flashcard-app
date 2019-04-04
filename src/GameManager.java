import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
	private static ArrayList<Box> boxset = new ArrayList<Box>();
	
	public static Box chooseBox(ArrayList<Box> boxset) {
		Random random = new Random();
		int index = random.nextInt(boxset.size());
		System.out.println(index);
		return boxset.get(index);
	}
	
	public static int findBoxNumber(Box box) {
		int index = 0;
		while (boxset.get(index) != box) {
			++index;
		}
		return index;
	}
	
	public static void moveForward(FlashCard card, Box box) {
		int currentLocation = findBoxNumber(box);
		if (currentLocation == 2) {
			//card stays in box 2
		}
		else {
			box.removeCard(card);
			boxset.get(currentLocation + 1).addCard(card);
		}
	}
	
	public static void moveBackward(FlashCard card, Box box) {
		int currentLocation = findBoxNumber(box);
		if (currentLocation == 0) {
			//card stays in box zero
		}
		else {
			box.removeCard(card);
			boxset.get(currentLocation - 1).addCard(card);
		}
	}
	
	public static int countCards() {
		int count = 0;
		for (int i = 0; i < boxset.size(); i++) {
			Box currentBox = boxset.get(i);
			count += currentBox.getSize();
		}
		return count;
	}
	
	public static void main(String[] args) {
				
		Scanner scanner = new Scanner(System.in);
		
		Box b1 = new Box(0);
		Box b2 = new Box(1);
		Box b3 = new Box(2);
		boxset.add(b1);
		boxset.add(b2);
		boxset.add(b3);
		
		FlashCard f1 = new FlashCard("der Hund", "dog");
		FlashCard f2 = new FlashCard("die Katze", "cat");
		FlashCard f3 = new FlashCard("die Leute", "people");
		FlashCard f4 = new FlashCard("das Fenster", "window");
		FlashCard f5 = new FlashCard("der Junge", "boy");
		FlashCard f6 = new FlashCard("der Baum", "tree");
		
		b1.addCard(f1);
		b1.addCard(f2);
		b1.addCard(f3);
		b1.addCard(f4);
		b1.addCard(f5);
		b1.addCard(f6);
		
		f1.printCard();
		f2.printCard();
		f3.printCard();
		f4.printCard();
		
		b1.printCards();
		
		System.out.println(countCards()); //tested and works
		
		while (boxset.get(2).getSize() != countCards()) {
			Box chosenBox = chooseBox(boxset);
			//make sure that the box that is picked actually contains a card
			while (chosenBox.isEmpty()) {
				chosenBox = chooseBox(boxset);
			}
			//continue as usual once guard is asserted...
			
			FlashCard chosenCard = chosenBox.chooseCard();
			System.out.println(chosenCard);
			String chosenSide = chosenCard.chooseSide();
			System.out.println(chosenSide);
			String answer = scanner.nextLine();
			if (chosenSide.compareTo(chosenCard.getFront()) == 0) {
				if (answer.compareTo(chosenCard.getBack()) == 0) {
					System.out.println("CORRECT");
					moveForward(chosenCard, chosenBox);
				}
				else {
					System.out.println("INCORRECT!");
					moveBackward(chosenCard, chosenBox);
				}
			}
			if (chosenSide.compareTo(chosenCard.getBack()) == 0) {
				if (answer.compareTo(chosenCard.getFront()) == 0) {
					System.out.println("CORRECT!");
					moveForward(chosenCard, chosenBox);
				}
				else {
					System.out.println("INCORRECT!");
					moveBackward(chosenCard, chosenBox);
				}
			}
			
			for (int i = 0; i < boxset.size(); i++) {
				System.out.println("Box " + i + " has " + boxset.get(i).getSize() + " cards");
			}
		}
		
		System.out.println("GAME OVER!! YOU WIN!!");
		
		scanner.close();
	}
}
