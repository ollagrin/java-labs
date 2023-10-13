import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;


public class Client {
    public static DataOutputStream OUT = null;
    static Socket client = null;
    static DataInputStream IN = null;


    public static void main(String[] args) {
        Client chatter = new Client();

        chatter.setUp();
        chatter.letsChat();
    }

    private void setUp() {
        try {
            String ipOfserver = JOptionPane.showInputDialog("Enter the IP address of the Server :- ");
            String portNo = JOptionPane.showInputDialog("Enter the port number:- ");

            client = new Socket(ipOfserver,Integer.parseInt(portNo));
            OUT = new DataOutputStream(client.getOutputStream());
            IN = new DataInputStream(client.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    private void letsChat() {
        new ClientInterface();
        Thread listenFromServer = new Thread(new ListenFromServer());
        listenFromServer.start();
    }
}