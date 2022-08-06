package at.rasch.model;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ElevatorManager {

    private final Elevator[] elevators;
    private final ExecutorService executorService;

    public ElevatorManager(int n) {
        elevators = new Elevator[n];

        //Filling the array with elevators 1 - n
        Arrays.setAll(elevators,e -> new Elevator(e+1));

        //Create a FixedThreadPool with size n
        executorService = Executors.newFixedThreadPool(elevators.length);
    }

    /*
    A new request is added and passed to an elevator
     */
    public void addRequest(ElevatorRequest request) {
        Optional<Elevator> optionalElevator = findNearestElevator(request.getCurrentFloor());

        //Try again when no elevator is available
        while(optionalElevator.isEmpty()){
            Thread.onSpinWait();
            optionalElevator = findNearestElevator(request.getCurrentFloor());
        }

        //Request is passed to the elevator
        Elevator nearestElevator = optionalElevator.get();
        nearestElevator.setRequest(request);

        //Submitting the elevator to the ThreadPool
        executorService.submit(nearestElevator);
    }

    /*
    Prints the status for all elevators
     */
    public void checkElevators(){
        for (Elevator e : elevators){
            System.out.println(e);
        }
    }

    /*
    Searches for the closest freestanding elevator
     */
    private Optional<Elevator> findNearestElevator(int currentFloor){
        return Arrays.stream(elevators).filter(Elevator::isAvailable)
                .min((e1, e2) ->
                (e2.getCurrentFloor()-currentFloor) - (e1.getCurrentFloor()-currentFloor));
    }

    /*
    Waits until each request has been processed and then the ThreadPool is terminated.
     */
    public void shutdown(){
        executorService.shutdown();
    }

}
