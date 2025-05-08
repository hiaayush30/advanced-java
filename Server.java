import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    Socket socket = null;
    DataInputStream dis = null;
    ServerSocket serverSocket = null;

    public Server(int port) throws Exception
    {
        serverSocket = new ServerSocket(port);
        System.out.println("Server Started");

        socket = serverSocket.accept();
        System.out.println("Client Accepted");

        dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        String msg = "";

        while(!msg.equalsIgnoreCase("end"))
        {
            msg = dis.readUTF();
            System.out.println(msg);
        }
        System.out.println("Closing Connection");
        socket.close();
        dis.close();
    }
    public static void main(String[] args) throws Exception {
        Server s = new Server(2323);
    }
}
