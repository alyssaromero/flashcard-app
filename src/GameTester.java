import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameTester {
	
	private static Game game;
	
	public static Game getGame() {
		return game;
	}
		
	public static void main(String[] args) {
				
		String filename = "game.ser";

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
			game = (Game)ois.readObject();
			System.out.println("File Already Exists...Loading File");
			System.out.println(game.getCardset());
			System.out.println(game.getAppColor());
			ois.close();
		} catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			try {
				game = new Game();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
				oos.writeObject(game);
				System.out.println("File Did Not Exist...Creating File");
				oos.close();
			} catch (FileNotFoundException e2) {
				System.out.println(e2.getMessage());
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}

		} catch(IOException e) {
			System.out.println(e.getMessage());
		} catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		GameViewer viewer = new GameViewer();
		viewer.drawMainScreen(game);
	}
}
