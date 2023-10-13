package tests;

import java.io.Serializable;

public class Patient implements Serializable {
    private String name;
    private String surname;
    private String diagnosis;
    private int chamber;

    public Patient(String name, String surname, String diagnosis, int chamber) {
        this.name = name;
        this.surname = surname;
        this.diagnosis = diagnosis;
        this.chamber = chamber;
    }

    public void changeChamber(int newChamber) {
        this.chamber = newChamber;
    }

    public void changeDiagnosis(String newDiagnosis) {
        this.diagnosis = newDiagnosis;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getChamber() {
        return chamber;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.
                append(name).
                append(" ").
                append(surname).
                append(" ").
                append(diagnosis).
                append(" ").
                append(chamber).
                toString();
    }
}
