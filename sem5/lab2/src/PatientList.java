import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatientList {
    private List<Patient> patientList = new ArrayList<>();

    PatientList() {
    }

    PatientList(ArrayList<Patient> patientsInfo) {
        patientList = patientsInfo;
    }

    public void add(String name, String surname, String diagnosis, int chamber) {
        patientList.add(new Patient(name, surname, diagnosis, chamber));
    }

    public int length() {
        return patientList.size();
    }

    public Patient getByIndex(int index) {
        return patientList.get(index);
    }

    public String whichPatient(String name, String surname) {
        for (Patient patient : this.patientList) {
            if (patient.getName().equals(name) &&
                    patient.getSurname().equals(surname)) {
                return patient.toString();
            }
        }
        return "Patient is absent";
    }

    public void changeChamberForPatient(String name, String surname, int newChamber) {
        for (Patient patient : this.patientList) {
            if (patient.getName().equals(name) &&
                    patient.getSurname().equals(surname)) {
                patient.changeChamber(newChamber);
                return;
            }
        }
    }

    public void changeDiagnosisForPatient(String name, String surname, String diagnosis) {
        for (Patient patient : this.patientList) {
            if (patient.getName().equals(name) &&
                    patient.getSurname().equals(surname)) {
                patient.changeDiagnosis(diagnosis);
                return;
            }
        }
    }


    public void discharge(String name, String surname) {
        for (int i = 0; i < this.patientList.size(); i++) {
            if (patientList.get(i).getName().equals(name) &&
                    patientList.get(i).getSurname().equals(surname)) {
                patientList.remove(i);
                return;
            }
        }
    }

    public PatientList getPatientsFromChamber(int chamber) {
        List<Patient> patientsFromChamber = patientList.stream().
                filter(item -> item.getChamber() == chamber).collect(Collectors.toList());
        return new PatientList(new ArrayList<>(patientsFromChamber));
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        for (Patient patient : this.patientList) {
            str.append(patient.toString()).append("\n");
        }

        return str.toString();
    }
}