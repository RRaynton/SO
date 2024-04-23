package SO;

public class SOProcess {
    private String id;
    private int sizeInMemory;
    private SOPriority priority;
    private int timeToExecute;
    private static int countIndex = 0;

    public SOProcess(int sizeInMemory, SOPriority priority, int timeToExecute) {
        this.id = "P"+countIndex;   //O id do processo será P0, P1, P2 e assim por diante   
        countIndex++;   //Faz com que o id do próximo processo seja o deste acrescido de 1
        this.sizeInMemory = sizeInMemory;
        this.priority = priority;
        this.timeToExecute = timeToExecute;
    }
    public String getId() {
        return id;
    }
    public int getSizeInMemory() {
        return sizeInMemory;
    }
    public SOPriority getPriority() {
        return priority;
    }
    public int getTimeToExecute() {
        return timeToExecute;
    }  
}
