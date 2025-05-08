import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MeraServer {
    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(12345);
            System.out.println("server running");
            while (true) {
                Socket socket = s.accept();
                System.out.println("new Client connected:" + socket.getInetAddress());
                DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                String msg = "Hello from server";
                dos.writeUTF(msg);

                String clientMessage = "";
                while (!clientMessage.equalsIgnoreCase("end")) {
                     clientMessage = dis.readUTF();
                     System.out.println("Client sent:"+clientMessage);
                }
                socket.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}