import javax.swing.*;
import java.awt.*;

public class ServerInterface extends JFrame {
    public static JTextArea Conversations;

    public ServerInterface() {
        setVisible(true);
        initComponents();
    }

    private void initComponents() {

        JLabel jLabel1 = new JLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        Conversations = new JTextArea();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server Running!");
        setName("Server"); // NOI18N
        setResizable(false);

        jLabel1.setFont(new Font("Tahoma", 0, 14));
        jLabel1.setText("Conversations");

        jScrollPane1.setAutoscrolls(true);

        Conversations.setColumns(20);
        Conversations.setEditable(false);
        Conversations.setFont(new Font("MS Gothic", 0, 24)); // NOI18N
        Conversations.setLineWrap(true);
        Conversations.setRows(5);
        Conversations.setToolTipText("These are the current Conversations.");
        Conversations.setWrapStyleWord(true);
        jScrollPane1.setViewportView(Conversations);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                                        .addComponent(jLabel1))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(86, Short.MAX_VALUE))
        );

        pack();
    }
}