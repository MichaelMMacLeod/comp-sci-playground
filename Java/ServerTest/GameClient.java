// From http://cs.lmu.edu/~ray/notes/javanetexamples/

import java.util.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

public class GameClient {

    private BufferedReader in;
    private PrintWriter out;
    private Scanner scan;

    public static void main(String[] args) throws Exception {
        GameClient client = new GameClient();
        client.connectToServer();
    }

    public void connectToServer() throws IOException {
        scan = new Scanner(System.in);

        System.out.print("Enter IP address of server: ");
        String serverAddress = scan.nextLine();

        Socket socket = new Socket(serverAddress, 9898);
        in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        System.out.println(in.readLine());
        
        while (true) {
            if (scan.hasNextLine()) {
                out.println(scan.nextLine());
            }
            System.out.println(in.readLine());
        }
    }
}