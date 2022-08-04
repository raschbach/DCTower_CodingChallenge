package at.rasch.model;

public class Elevator implements Runnable{

    private int ID;
    private int currentFloor;
    private ElevatorRequest request;

    @Override
    public void run() {
        System.out.println("Aufzug: " + ID + " übernimmt die fahrt von " + request.getCurrentFloor() + " nach " + request.getDestinationFloor());

        //Funktion überarbeiten
        while(currentFloor != request.getCurrentFloor()){
            if(currentFloor < request.getCurrentFloor()){
                currentFloor++;
            } else {
                currentFloor--;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //System.out.println(ID + ": " + currentFloor + " : " + request.getCurrentFloor());
        }

        while(currentFloor != request.getDestinationFloor()){
            if(currentFloor < request.getDestinationFloor()){
                currentFloor++;
            } else {
                currentFloor--;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //System.out.println(ID + ": " + currentFloor + " : " + request.getDestinationFloor());
        }

        request = null;
    }

    public Elevator(int id) {
        ID = id;
        currentFloor = 0;
        request = null;
    }

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
        this.request = request;
    }

    @Override
    public String toString() {
        if(request == null){
            return "Der Aufzug wartet im " + currentFloor + ". Stock.";
        }
        return "Der Aufzug befindet sich im " + currentFloor +
                ". Stock und ist auf dem Weg in den " + request.getDestinationFloor() + ". Stock.";
    }
}
