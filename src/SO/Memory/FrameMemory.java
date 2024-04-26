package SO.Memory;

public class FrameMemory {
    private int pageNumber;
    private int positionX;

    public FrameMemory(int pageNumber, int positionX) {
        this.pageNumber = pageNumber;
        this.positionX = positionX;
    }
    
    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    public int getPositionX() {
        return positionX;
    }
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    

}
