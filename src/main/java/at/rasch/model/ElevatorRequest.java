package at.rasch.model;

public class ElevatorRequest {

    private int currentFloor;
    private int destinationFloor;
    private Direction direction;

    public ElevatorRequest(int currentFloor, int destinationFloor, Direction direction) {
        if(currentFloor > 55 || currentFloor < 0){
            throw new IllegalArgumentException(currentFloor + ": currentFloor is not a valid Floor");
        } else if(destinationFloor > 55 || destinationFloor < 0){
            throw new IllegalArgumentException(destinationFloor + ": destinationFloor is not a valid Floor");
        }
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "ElevatorRequest{" +
                "currentFloor=" + currentFloor +
                ", destinationFloor=" + destinationFloor +
                ", direction=" + direction +
                '}';
    }
}
