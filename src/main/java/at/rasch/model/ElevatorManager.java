package at.rasch.model;

import at.rasch.Main;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ElevatorManager {

    private Elevator[] elevators;
    private ExecutorService executorService;

    public ElevatorManager(int n) {
        elevators = new Elevator[n];

        //Befüllen des Arrays mit Aufzügen 1 - n
        Arrays.setAll(elevators,e -> new Elevator(e+1));

        //Erstellen eines FixedThreadPool mit der Größe n
        executorService = Executors.newFixedThreadPool(elevators.length);
    }

    /*
    Übergabe des Request an einen Aufzug
     */
    public void addRequest(ElevatorRequest request) {
        Optional<Elevator> optionalElevator = findNearestElevator(request.getCurrentFloor());

        //Sollte kein Aufzug verfügbar sein wird 3 Sekunden bis zum nächsten Versuch gewartet
        while(optionalElevator.isEmpty()){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            optionalElevator = findNearestElevator(request.getCurrentFloor());
        }

        //Request wird an den Aufzug übergeben
        Elevator nearestElevator = optionalElevator.get();
        nearestElevator.setRequest(request);

        //Übergabe an den ThreadPool
        executorService.submit(nearestElevator);
    }

    /*
    Ausgabe des Status von allen Aufzügen
     */
    public void checkElevators(){
        for (Elevator e : elevators){
            System.out.println(e);
        }
    }

    /*
    Sucht nach dem nähersten, freistehenden Aufzug
     */
    private Optional<Elevator> findNearestElevator(int currentFloor){
        return Arrays.stream(elevators).filter(Elevator::isAvailable)
                .min((e1, e2) ->
                (e2.getCurrentFloor()-currentFloor) - (e1.getCurrentFloor()-currentFloor));
    }

    /*
    Abwarten bis jeder Request bearbeitet wurde und danach wird der ThreadPool beendet.
     */
    public void shutdown(){
        executorService.shutdown();
    }

}
