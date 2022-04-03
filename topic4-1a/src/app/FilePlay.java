package app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FilePlay {

	private static int copyFile(String fileIn, String fileOut) {
		FileReader fr = null;
		FileWriter fw = null;

		try {
			fr = new FileReader(fileIn);
			fw = new FileWriter(fileOut);

			int c = fr.read();
			while (c != -1) {
				fw.write(c);
				c = fr.read();
			}
			fr.close();
			fw.close();
			return 0; // this returns 0 if fileIn read completely and successfully.
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		} catch (IOException ex) {
			ex.printStackTrace();
			return -2;
		}
	}

	public static void main(String args[]) {
		int err = copyFile("InFile.txt", "OutFile.txt");

		switch (err) {
		case 0:
			System.out.println("File was copied successfully");
			break;
		case -1:
			System.out.println("File could not be opened.");
			break;
		case -2:
			System.out.println("File I/O error.");
			break;
		}
	}
}
