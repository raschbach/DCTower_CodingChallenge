package at.rasch.model;

public class Elevator implements Runnable{

    private int id;
    private int currentFloor;
    private ElevatorRequest request;

    @Override
    public void run() {
        System.out.println("Aufzug: " + id + " übernimmt die fahrt von "
                + request.getCurrentFloor() + " nach " + request.getDestinationFloor());

        /*
        Durch den Betrag wird berechnet wie viele Stockwerke der Aufzug zurück legen muss,
        damit er den Request erledigt.
         */
        int floorsToTravel = Math.abs(currentFloor - request.getCurrentFloor()) +
                             Math.abs(request.getCurrentFloor() - request.getDestinationFloor());

        //Für jedes Stockwerk wartet der Aufzug 1 Sekunde
        try {
            Thread.sleep(floorsToTravel * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Ankunft beim DestinationFloor
        currentFloor = request.getDestinationFloor();

        System.out.println("Aufzug: " + id + " hat die Fahrt von " +
                request.getCurrentFloor() + " nach " + request.getDestinationFloor() + " abgeschlossen.");

        request = null;
    }

    public Elevator(int id) {
        this.id = id;
        currentFloor = 0;
        request = null;
    }

    /*
    Die Methode überprüft ob der Aufzug einen Request hat.
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
            throw new IllegalArgumentException("Der Aufzug: " + id + " bearbeitet bereits einen Request");
        }

        this.request = request;
    }

    @Override
    public String toString() {
        if(request == null){
            return "Der Aufzug: " + id + " wartet im " + currentFloor + ". Stock.";
        }
        return "Der Aufzug: " + id + " bearbeitet den Request von " + request.getCurrentFloor() +
                ". Stock in den " + request.getDestinationFloor() + ". Stock.";
    }
}
