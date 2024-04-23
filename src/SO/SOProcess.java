package SO;

public class SOProcess {
    private String id;
    private int sizeInMemory;
    private SOPriority priority;
    private int timeToExecute;
    private static int countIndex = 0;

    public SOProcess(String id, int sizeInMemory, SOPriority priority, int timeToExecute) {
        this.id = "P"+countIndex;
        this.sizeInMemory = sizeInMemory;
        this.priority = priority;
        this.timeToExecute = timeToExecute;
        countIndex++;
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
