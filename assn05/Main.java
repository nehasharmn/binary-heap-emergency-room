package assn05;

public class Main {

    public static void main(String[] args) {
        testP1();
        testP2();
        testP3();
        testP4();
    }

    // test Part 1
    public static void testP1(){
        SimpleEmergencyRoom er = new SimpleEmergencyRoom();

        er.enqueue(new Patient("patient1", 3));
        er.enqueue(new Patient("patient2", 2));
        er.enqueue(new Patient("patient3", 5));
        Patient highestPriorityPatient = er.dequeue();
        System.out.println("dequeued " + highestPriorityPatient.getValue() + " (priority " + highestPriorityPatient.getPriority() + ")");

    }


    // test Part 2
    public static void testP2(){
        MaxBinHeapER<String, Integer> maxHeap = new MaxBinHeapER<>();

        maxHeap.enqueue("Neha", 10);
        maxHeap.enqueue("Anita", 5);
        maxHeap.enqueue("Pradeep", 20);
        maxHeap.enqueue("Meg", 2);

        maxHeap.enqueue("Oreo");

        String max = maxHeap.getMax();
        //  print Charlie
        System.out.println("Max Value " + max);

        String removed = maxHeap.dequeue();
        //  print Charlie
        System.out.println("Removed Value " + removed);

        Prioritized<String, Integer>[] array = maxHeap.getAsArray();
        System.out.println("Heap Elements ");
        for (Prioritized<String, Integer> element : array) {
            System.out.println(element.getValue() + " with priority " + element.getPriority());
        }

        boolean isValidMaxHeap = isMaxHeap(array);
        System.out.println("Is Max Heap " + isValidMaxHeap);
    }
    private static <V, P extends Comparable<P>> boolean isMaxHeap(Prioritized<V, P>[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int lChildIndex = 2 * i + 1;
            int rChildIndex = 2 * i + 2;

            if (lChildIndex < n && array[i].getPriority().compareTo(array[lChildIndex].getPriority()) < 0) {
                return false;
            }

            if (rChildIndex < n && array[i].getPriority().compareTo(array[rChildIndex].getPriority()) < 0) {
                return false;
            }
        }
        return true;
    }
    /*
    Part 3
     */
    public static void testP3() {
        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for (int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }
        Prioritized<Integer, Integer>[] initialEntries = makePatients();
        MaxBinHeapER<Integer, Integer> heap = new MaxBinHeapER<>(initialEntries);
        Prioritized<Integer,Integer>[] heapA = heap.getAsArray();
        for(int i = 0; i <initialEntries.length; i++){
            assert (heapA[i].getValue().equals(initialEntries[i].getPriority()));
            assert (heapA[i].getPriority().equals(initialEntries[i].getPriority()));
        }
    }
    /*
    Part 4
     */
    public static void testP4() {
        double[] runtimes = compareRuntimes();
        System.out.println("Time to dequeue all 100,000 patients: " + runtimes[0]);
        System.out.println("Avg time to dequeue 1 patient " + runtimes[1]);
        System.out.println("Time to dequeue all 100,000 patients in the new implementation " + runtimes[2]);
        System.out.println("Avg time to dequeue 1 patient in the new implementation " + runtimes[3]);

    }

    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    public static double[] compareRuntimes() {
    	// Array which you will populate as part of Part 4
    	double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        double start = System.nanoTime();
        fillER(simplePQ);
        double end = System.nanoTime();
        results[0] = end-start;
        results[1] = (end-start)/100000;

        // Code for (Task 4.1) Here

        MaxBinHeapER binHeap = new MaxBinHeapER();
        double start2 = System.nanoTime();
        fillER(binHeap);
        double end2 = System.nanoTime();
        results[2] = end2 - start2;
        results[3] = (end2-start2)/100000;

        // Code for (Task 4.2) Here

        return results;
    }

}

