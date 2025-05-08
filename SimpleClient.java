import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SimpleClient {

    public static void main(String[] args) {
        String hostName = "localhost"; // Replace with the server's IP address or hostname
        int portNumber = 12345; // Must match the server's port

        try (
             Socket socket = new Socket(hostName, portNumber);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)
        ) {
            String userInput;
            while (true) {
                System.out.print("Enter message (or 'bye' to quit): ");
                userInput = scanner.nextLine();
                out.println(userInput);
                String serverResponse = in.readLine();
                System.out.println("Server says: " + serverResponse);
                if (userInput.equalsIgnoreCase("bye")) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}