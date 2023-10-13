import javax.swing.*;
import java.io.IOException;

class ListenFromServer implements Runnable {

    String inString;

    @Override
    public void run() {
        try {
            while (true) {
                inString = Encryption.deсrypt(Client.IN.readUTF());
                ClientInterface.convoArea.append("\n" + (inString));
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "The connection with server was interrupted!"+ex.getClass());
        }
        try {
            Client.IN.close();
            Client.OUT.close();
            Client.client.close();
        } catch (IOException ex) {
        }
    }

}