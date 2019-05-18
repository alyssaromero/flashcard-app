import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.UIManager;

/* 
 * @author Alyssa Romero 2019 
 */

public class GameTester {
	
	private static Game game;
	
	public static Game getGame() {
		return game;
	}
		
	public static void main(String[] args) {
				
		String filename = "game.ser";

		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
			game = (Game)ois.readObject();
			System.out.println("File Already Exists...Loading File");
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
		try {
			viewer.playBackgroundMusic(game);
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			System.out.println(e.getMessage());
		}
	}
}
