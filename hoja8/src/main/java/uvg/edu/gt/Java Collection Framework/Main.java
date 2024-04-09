import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Paciente> emergencyQueue = new PriorityQueue<>(Comparator.comparing(Paciente::getCodigoEmergencia));
        String line;

        //Implementar el archivo
        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String nombre = parts[0].trim();
                    String sintoma = parts[1].trim();
                    char codigoEmergencia = parts[2].trim().charAt(0);

                    Paciente paciente = new Paciente(nombre, sintoma, codigoEmergencia);
                    emergencyQueue.offer(paciente); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Orden de atención de los pacientes:");
        while (!emergencyQueue.isEmpty()) {
            Paciente paciente = emergencyQueue.poll(); 
            System.out.println(paciente);
        }
    }
}