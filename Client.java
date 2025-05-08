
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    Socket socket = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;

    public Client(String addr , int port) throws Exception
    {
        socket = new Socket(addr,port);
        System.out.println("Connected");
        dis = new DataInputStream(System.in);
        dos = new DataOutputStream(socket.getOutputStream());
        String msg = "";

        while(!msg.equalsIgnoreCase("end"))
        {
            msg = dis.readLine();
            dos.writeUTF(msg);
        }
        dis.close();
        dos.close();
        socket.close();
    }
    public static void main(String[] args) throws Exception {
        Client c = new Client("127.0.0.1", 2323);
    }
}
