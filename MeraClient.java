import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class MeraClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost",12345);
            DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            String msg = "Hello from client";
            dos.writeUTF(msg);


            String serverMEssage = "";
            while (!serverMEssage.equalsIgnoreCase("end")) {
                serverMEssage = dis.readUTF();
                System.out.println("server sent:"+serverMEssage);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}
