import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NewClientThread implements Runnable {
    Socket newClient;
    DataInputStream IN;
    DataOutputStream OUT;

    public NewClientThread(Socket s) {
        this.newClient = s;
    }

    @Override
    public void run() {
        try {
            try {
                IN = new DataInputStream(newClient.getInputStream());
                OUT = new DataOutputStream(newClient.getOutputStream());
                String hostName = newClient.getInetAddress().getHostName();
                Server.add(OUT);
                String str;
                try {
                    while ((str = (IN.readUTF())) != null) {
                        ServerInterface.Conversations.append("\n" + hostName + "::" + str);
                        Server.sendToAll(Encryption.encrypt(hostName + "::" + Encryption.deсrypt(str)));
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } catch (IOException ex) {
            }
            Server.remove(OUT);
            IN.close();
            OUT.close();
            newClient.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "The connection with server was interrupted!");
        }
    }
}