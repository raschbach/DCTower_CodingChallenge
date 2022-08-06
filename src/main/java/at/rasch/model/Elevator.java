package at.rasch.model;

public class Elevator implements Runnable{

    private int id;
    private int currentFloor;
    private ElevatorRequest request;

    @Override
    public void run() {
        System.out.println("Elevator: " + id + " takes the request from "
                + request.getCurrentFloor() + " to " + request.getDestinationFloor());

        /*
        Calculation of the floors the elevator must travel to complete the request
         */
        int floorsToTravel = Math.abs(currentFloor - request.getCurrentFloor()) +
                             Math.abs(request.getCurrentFloor() - request.getDestinationFloor());

        //For each floor the elevator waits 1 second
        try {
            Thread.sleep(floorsToTravel * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Simulated arrival at the DestinationFloor
        currentFloor = request.getDestinationFloor();

        System.out.println("Elevator: " + id + " has completed the request from  " +
                request.getCurrentFloor() + " to " + request.getDestinationFloor());

        request = null;
    }

    public Elevator(int id) {
        this.id = id;
        currentFloor = 0;
        request = null;
    }

    /*
    The method checks if the elevator already has a request.
     */
    public boolean isAvailable(){
        return request == null;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public ElevatorRequest getRequest() {
        return request;
    }

    public void setRequest(ElevatorRequest request) {
        if(!this.isAvailable()){
            throw new IllegalArgumentException("Elevator: " + id + " is already processing a request.");
        }

        this.request = request;
    }

    @Override
    public String toString() {
        if(request == null){
            return "Elevator: " + id + " waits on the " + currentFloor + ". floor.";
        }
        return "Elevator " + id + " processes the request from " + request.getCurrentFloor() +
                ". floor to the " + request.getDestinationFloor() + ". floor.";
    }
}
