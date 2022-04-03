package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FilePlay {

	/**
	 * Copies text file into a new text file at root of project.
	 * 
	 * @param fileIn  Path and name of file to be copied.
	 * @param fileOut Path and name of newly copied file.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void copyFile(String fileIn, String fileOut) throws FileNotFoundException, IOException {
		BufferedReader in = null;
		BufferedWriter out = null;

		in = new BufferedReader(new FileReader(fileIn));
		out = new BufferedWriter(new FileWriter(fileOut));

		String line = in.readLine();
		while (line != null) {
			String[] tokens = line.split("\\|");
			out.write(String.format("Name is %s %s of age %s\n", tokens[0], tokens[1], tokens[2]));
			line = in.readLine();

		}

		// cleanup closures
		try {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String args[]) {
		try {
			copyFile("InUsers.txt", "OutFile.txt");
			System.out.println("File was copied successfully");
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("File could not be opened (file was not found).");
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("File I/O error.");
		}
	}
}
