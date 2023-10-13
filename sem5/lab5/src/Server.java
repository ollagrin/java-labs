import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Server {

    Socket individualServer = null;
    static String portNumber = null;
    public static List<DataOutputStream> OUTALL = new ArrayList<DataOutputStream>();

    synchronized public static void add(DataOutputStream opStream) {
        OUTALL.add(opStream);
    }

    synchronized public static void remove(DataOutputStream opStream) {
        OUTALL.remove(opStream);
    }

    synchronized public static void sendToAll(String msg) {
        for (DataOutputStream dop : OUTALL) {
            try {
                dop.writeUTF((msg));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server listener = new Server();
        listener.setUp();
        new ServerInterface();  // Start server window
        listener.Conversations(Integer.parseInt(portNumber));
    }


    private void setUp() {
        portNumber = JOptionPane.showInputDialog("Enter the port number "
                + "to start the server: ");
    }

    private void Conversations(int port) {
        try {
            ServerSocket ss = new ServerSocket(port);
            ServerInterface.Conversations.setText("Server listening @ portNumber :- "
                    + port + "\n\n");
            while (true) {
                individualServer = ss.accept();
                Thread clientThread = new Thread
                        (new NewClientThread(individualServer));
                clientThread.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}