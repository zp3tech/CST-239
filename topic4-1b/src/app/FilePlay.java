package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FilePlay {

	private static int copyFile(String fileIn, String fileOut) {
		BufferedReader in = null;
		BufferedWriter out = null;

		try {
			in = new BufferedReader(new FileReader(fileIn));
			out = new BufferedWriter(new FileWriter(fileOut));

			int c = in.read();
			while (c != -1) {
				out.write(c);
				c = in.read();
			}
			in.close();
			out.close();
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
		int err = copyFile("InUsers.txt", "OutFile.txt");

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
