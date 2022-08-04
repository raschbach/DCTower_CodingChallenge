package at.rasch;

import at.rasch.model.Direction;
import at.rasch.model.Elevator;
import at.rasch.model.ElevatorManager;
import at.rasch.model.ElevatorRequest;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World");


        ElevatorRequest request1 = new ElevatorRequest(0, 10, Direction.UP);
        ElevatorRequest request2 = new ElevatorRequest(35, 0, Direction.DOWN);
        ElevatorRequest request3 = new ElevatorRequest(0, 15, Direction.UP);
        ElevatorRequest request4 = new ElevatorRequest(30, 0, Direction.DOWN);
        ElevatorRequest request5 = new ElevatorRequest(27, 0, Direction.DOWN);
        ElevatorRequest request6 = new ElevatorRequest(0, 11, Direction.UP);
        ElevatorRequest request7 = new ElevatorRequest(0, 12, Direction.UP);
        ElevatorRequest request8 = new ElevatorRequest(0, 13, Direction.UP);
        ElevatorRequest request9 = new ElevatorRequest(0, 14, Direction.UP);
        ElevatorRequest request10 = new ElevatorRequest(0, 55, Direction.UP);


        ElevatorManager elevatorManager = new ElevatorManager();
        elevatorManager.addRequest(request1);
        elevatorManager.addRequest(request2);
        elevatorManager.addRequest(request3);
        elevatorManager.addRequest(request4);
        elevatorManager.addRequest(request5);
        elevatorManager.addRequest(request6);
        elevatorManager.addRequest(request7);
        elevatorManager.addRequest(request8);
        elevatorManager.addRequest(request9);
        elevatorManager.addRequest(request10);

        //elevatorManager.checkElevators();

        System.out.println("End");

        elevatorManager.shutdown();
        //elevatorManager.checkElevators();
    }

}
