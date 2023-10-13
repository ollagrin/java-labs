import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {

    private static final int WIDTH = 960;
    private static final int HEIGHT = 650;

    private final JTextArea textArea;
    private JFileChooser fileChooser;

    private final FileHandler fileHandler = new FileHandler();
    private boolean isFileSaved;

    public Window(String name) {
        super(name);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!isFileSaved) {
                    int confirm = JOptionPane.showOptionDialog(
                            null, "Do you want to save the file before closing?",
                            "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (confirm != 0) {
                        System.exit(0);
                    }
                } else {
                    System.exit(0);
                }
            }
        };

        addWindowListener(exitListener);
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        //setResizable(false);

        addMenu();

        setLayout(new BorderLayout());
        JPanel radioButtons = new JPanel();
        JRadioButton enButton = new JRadioButton("Encode", true);
        JRadioButton deButton = new JRadioButton("Decode", false);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(enButton);
        buttonGroup.add(deButton);
        radioButtons.add(enButton);
        radioButtons.add(deButton);
        add(radioButtons, BorderLayout.SOUTH);

        textArea = new JTextArea(50, 50);
        textArea.setText("Need to open your file!");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        //add(textArea, BorderLayout.CENTER);
        //add(scrollPane, BorderLayout.CENTER);
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.add(scrollPane);
        add(textAreaPanel, BorderLayout.CENTER);

        JPanel paramsPanel = new JPanel();
        paramsPanel.setLayout(new FlowLayout());
        JTextField a = new JTextField("Enter prime num", 10);
        JButton goButton = new JButton("Go");
        goButton.setEnabled(false);
        a.addActionListener(e -> {
            try {
                int num = Integer.parseInt(a.getText());
                if (!isPrime(num)) {
                    JOptionPane.showOptionDialog(
                            null, "Number A isn't a prime!!",
                            "Warning!", JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.ERROR_MESSAGE, null, null, null);
                    goButton.setEnabled(false);
                } else{
                    goButton.setEnabled(true);
                }
            } catch (NumberFormatException exc) {
                JOptionPane.showOptionDialog(
                        null, "Number A is a text!!",
                        "Warning!", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.ERROR_MESSAGE, null, null, null);
                goButton.setEnabled(false);

            }
        });
        JTextField b = new JTextField("Enter num", 10);
        b.addActionListener(e -> {
            try {
                int num = Integer.parseInt(a.getText());
                goButton.setEnabled(true);
            } catch (NumberFormatException exc) {
                JOptionPane.showOptionDialog(
                        null, "Number B is a text!!",
                        "Warning!", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.ERROR_MESSAGE, null, null, null);
                goButton.setEnabled(false);
            }
        });

        goButton.addActionListener(e -> {
            fileHandler.setParams(Integer.parseInt(a.getText()), Integer.parseInt(b.getText()));
            if (enButton.isSelected()) {
                textArea.setText(fileHandler.encode());
            } else {
                textArea.setText(fileHandler.decode());
            }
        });
        paramsPanel.add(a);
        paramsPanel.add(b);
        paramsPanel.add(goButton);
        add(paramsPanel, BorderLayout.NORTH);
    }

    private void addMenu() {
        JMenuBar menu = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);
        menu.add(fileMenu);

        setJMenuBar(menu);

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        openItem.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    textArea.setText(fileHandler.readFile(file));
                } catch (IOException ex) {
                    JOptionPane.showOptionDialog(
                            null, "Cannot read file!",
                            "Warning!", JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.ERROR_MESSAGE, null, null, null);
                }
            }
        });
        saveItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    if (selectedFile.createNewFile()) {
                        fileHandler.writeToFile(selectedFile);
                    } else {
                        int input = JOptionPane.showConfirmDialog(
                                null,
                                "File already exists. Do you want to rewrite it?",
                                "Attention!",
                                JOptionPane.YES_NO_CANCEL_OPTION);
                        if (input == 0) {
                            isFileSaved = true;
                            fileHandler.writeToFile(selectedFile);
                        } else {
                            isFileSaved = false;
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private boolean isPrime(int num) {
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
