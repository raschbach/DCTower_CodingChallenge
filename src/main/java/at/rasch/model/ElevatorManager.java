package at.rasch.model;

import at.rasch.Main;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ElevatorManager {

    private Elevator[] elevators = new Elevator[7];
    private ExecutorService executorService = Executors.newFixedThreadPool(elevators.length);


    public ElevatorManager() {
        elevators[0] = new Elevator(1);
        elevators[1] = new Elevator(2);
        elevators[2] = new Elevator(3);
        elevators[3] = new Elevator(4);
        elevators[4] = new Elevator(5);
        elevators[5] = new Elevator(6);
        elevators[6] = new Elevator(7);
    }

    public void addRequest(ElevatorRequest request) {

        Optional<Elevator> optionalElevator = findNearestElevator(request.getCurrentFloor());

        while(!optionalElevator.isPresent()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            optionalElevator = findNearestElevator(request.getCurrentFloor());
        }

        Elevator nearestElevator = optionalElevator.get();
        nearestElevator.setRequest(request);
        executorService.submit(nearestElevator);
    }

    public void checkElevators(){
        for (Elevator e : elevators){
            System.out.println(e);
        }
    }

    private Optional<Elevator> findNearestElevator(int currentFloor){
        return Arrays.stream(elevators).filter(e -> e.isAvailable())
                .min((e1, e2) ->
                (e1.getCurrentFloor()-currentFloor) - (e2.getCurrentFloor()-currentFloor));
    }

    public void shutdown(){
        executorService.shutdown();
    }

}
