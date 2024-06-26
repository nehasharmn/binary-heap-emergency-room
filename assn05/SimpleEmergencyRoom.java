package assn05;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    // TODO: dequeue
    public Patient dequeue() {
        if (patients.isEmpty()) {
            return null;
        } else {
            Patient highPriority = patients.get(0);
            for (int i = 1; i < patients.size(); i++) {
                if (patients.get(i).getPriority().compareTo(highPriority.getPriority()) > 0) {
                    highPriority = patients.get(i);
                }
            }
            patients.remove(highPriority);
            return highPriority;
        }
    }
    // i made a enquene method to test my dequene method in main
    public void enqueue(Patient patient) {
        patients.add(patient);
    }

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}