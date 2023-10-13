
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


public class Window extends JFrame {

    private PatientList patientList = new PatientList();

    private JFileChooser chooser = new JFileChooser();
    private JMenuBar menu = new JMenuBar();

    private JMenu fileMenu = new JMenu("File");
    private JMenuItem openFile = new JMenuItem("Open");
    private JMenuItem saveFile = new JMenuItem("Save");


    private JMenu patientListMenu = new JMenu("Patients");
    private JMenuItem changeChamber = new JMenuItem("Change Chamber");

    private JMenuItem changeDiagnosis = new JMenuItem("Change Diagnosis");
    private JMenuItem getByChamber = new JMenuItem("Patients From Chamber");

    private JMenuItem disCharge = new JMenuItem("Discharge patient");
    private JMenuItem getPatient = new JMenuItem("Which Patient");
    private JMenuItem addPatient = new JMenuItem("Add Patient");

    private JTextArea fileTextArea = new JTextArea("");

    private JLabel inputNameLabel = new JLabel("Input name: ");
    private JLabel inputSurnameLabel = new JLabel("Input surname: ");
    private JLabel inputDiagnosisLabel = new JLabel("Input diagnosis: ");
    private JLabel inputChamberLabel = new JLabel("Input chamber: ");

    private JTextField inputNameField = new JTextField("");
    private JTextField inputSurnameField = new JTextField("");
    private JTextField inputDiagnosisField = new JTextField("");
    private JTextField inputChamberField = new JTextField("");


    private void clearInputs() {
        inputNameField.setText("");
        inputSurnameField.setText("");
        inputChamberField.setText("");
        inputDiagnosisField.setText("");

    }

    public Window() {

        this.setTitle("MSS---LAB_2");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocation(350, 150);

        fileTextArea.setLocation(45, 20);
        fileTextArea.setSize(500, 150);
        fileTextArea.setLineWrap(true);

        fileTextArea.setEnabled(false);

        inputNameLabel.setLocation(20, 200);
        inputNameLabel.setSize(100, 20);
        this.add(inputNameLabel);

        inputNameField.setLocation(120, 200);
        inputNameField.setSize(200, 20);
        this.add(inputNameField);

        inputSurnameLabel.setLocation(20, 230);
        inputSurnameLabel.setSize(100, 20);
        this.add(inputSurnameLabel);

        inputSurnameField.setLocation(120, 230);
        inputSurnameField.setSize(200, 20);
        this.add(inputSurnameField);

        inputDiagnosisLabel.setLocation(20, 260);
        inputDiagnosisLabel.setSize(100, 20);
        this.add(inputDiagnosisLabel);

        inputDiagnosisField.setLocation(120, 260);
        inputDiagnosisField.setSize(200, 20);
        this.add(inputDiagnosisField);


        inputChamberLabel.setLocation(20, 290);
        inputChamberLabel.setSize(100, 20);
        this.add(inputChamberLabel);

        inputChamberField.setLocation(120, 290);
        inputChamberField.setSize(200, 20);
        this.add(inputChamberField);

        this.add(fileTextArea);
        fileTextArea.setVisible(true);

        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        menu.add(fileMenu);

        patientListMenu.add(changeChamber);
        patientListMenu.add(changeDiagnosis);
        patientListMenu.add(disCharge);
        patientListMenu.add(getPatient);
        patientListMenu.add(getByChamber);
        patientListMenu.add(addPatient);
        menu.add(patientListMenu);

        this.setJMenuBar(menu);

        changeDiagnosis.addActionListener(e -> {
            String name = inputNameField.getText();
            String surname = inputSurnameField.getText();
            String newDiagnosis = inputDiagnosisField.getText();
            patientList.changeDiagnosisForPatient(name, surname, newDiagnosis);
            fileTextArea.setText(patientList.toString());
            clearInputs();
        });

        disCharge.addActionListener(e -> {
            String name = inputNameField.getText();
            String surname = inputSurnameField.getText();
            this.patientList.discharge(name, surname);
            fileTextArea.setText(patientList.toString());
            clearInputs();
        });

        getPatient.addActionListener(e -> {
            String name = inputNameField.getText();
            String surname = inputSurnameField.getText();
            JOptionPane.showMessageDialog(null, patientList.whichPatient(name, surname));
            clearInputs();
        });

        changeChamber.addActionListener(e -> {
            String name = inputNameField.getText();
            String surname = inputSurnameField.getText();
            int newChamber = Integer.parseInt(inputChamberField.getText());
            patientList.changeChamberForPatient(name, surname, newChamber);
            fileTextArea.setText(patientList.toString());
            clearInputs();
        });

        getByChamber.addActionListener(e -> {
            PatientList patients = patientList.getPatientsFromChamber(Integer.parseInt(inputChamberField.getText()));
            JOptionPane.showMessageDialog(null, patients);
            clearInputs();
        });

        addPatient.addActionListener(e -> {
            String name = inputNameField.getText();
            String surname = inputSurnameField.getText();
            String diagnosis = inputDiagnosisField.getText();
            int chamber = Integer.parseInt(inputChamberField.getText());
            patientList.add(name, surname, diagnosis, chamber);

            fileTextArea.setText(patientList.toString());
            clearInputs();
        });

        openFile.addActionListener(e -> {
            int result = chooser.showOpenDialog(null);
            ArrayList<Patient> patients = new ArrayList<>();

            File target = chooser.getSelectedFile();

            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(target.getAbsolutePath()))) {
                Patient patient = (Patient) ois.readObject();
                while (patient != null) {
                    patients.add(patient);
                    patient = (Patient) ois.readObject();
                }
            } catch(EOFException exc){
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getClass());
            }
            patientList = new PatientList(patients);
            fileTextArea.setText(patientList.toString());
        });

        saveFile.addActionListener(e -> {
            int result = chooser.showSaveDialog(null);
            File target = chooser.getSelectedFile();

            Patient patient;
            try (ObjectOutputStream ous = new ObjectOutputStream(
                    new FileOutputStream(target.getAbsolutePath()))) {
                for (int i = 0; i < patientList.length(); i++) {
                    patient = patientList.getByIndex(i);
                    ous.writeObject(patient);
                    ous.flush();
                }
            } catch (Exception ex) {
            }
        });
        this.setLayout(null);
        this.setVisible(true);
    }
}

