import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

import java.net.Socket;
import java.net.ServerSocket;

// From https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html

public class ServerTest {
	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(
				Integer.parseInt(args[0]));
			Socket clientSocket = serverSocket.accept();

			PrintWriter out =
				new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream()));
		) {
			String inputLine, outputLine;


		} catch (Exception e) {}
	}
}