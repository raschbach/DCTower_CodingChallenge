package at.rasch;

import at.rasch.model.Direction;
import at.rasch.model.ElevatorManager;
import at.rasch.model.ElevatorRequest;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        //Erstellen eines Aufzugs-Manager mit 7 Aufzügen
        ElevatorManager elevatorManager = new ElevatorManager(7);

        //Testing:
        elevatorManager.addRequest(new ElevatorRequest(0, 3, Direction.UP));
        elevatorManager.addRequest(new ElevatorRequest(0, 6, Direction.UP));

        Thread.sleep(8000);

        //Aufzug 2 nimmt sich den ersten Request, weil er näher ist.
        elevatorManager.addRequest(new ElevatorRequest(8, 0, Direction.DOWN));
        elevatorManager.addRequest(new ElevatorRequest(4, 0, Direction.DOWN));

        //Alle Aufzüge sind wieder bei 0
        Thread.sleep(11000);
        elevatorManager.checkElevators();

        //7 Request
        elevatorManager.addRequest(new ElevatorRequest(0, 8, Direction.UP));
        elevatorManager.addRequest(new ElevatorRequest(0, 10, Direction.UP));
        elevatorManager.addRequest(new ElevatorRequest(0, 12, Direction.UP));
        elevatorManager.addRequest(new ElevatorRequest(0, 14, Direction.UP));
        elevatorManager.addRequest(new ElevatorRequest(0, 16, Direction.UP));
        elevatorManager.addRequest(new ElevatorRequest(0, 18, Direction.UP));
        elevatorManager.addRequest(new ElevatorRequest(0, 20, Direction.UP));

        //Alle Aufzüge sind belegt, daher wird gewartet bis der Erste frei ist.
        elevatorManager.addRequest(new ElevatorRequest(12, 0, Direction.DOWN));
        elevatorManager.addRequest(new ElevatorRequest(16, 0, Direction.DOWN));
        elevatorManager.addRequest(new ElevatorRequest(20, 0, Direction.DOWN));
        elevatorManager.addRequest(new ElevatorRequest(10, 0, Direction.DOWN));
        elevatorManager.checkElevators();

        elevatorManager.shutdown();
    }

}
