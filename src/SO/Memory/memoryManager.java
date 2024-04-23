package SO.Memory;

import SO.SOProcess;

public class memoryManager {
    private SOProcess[] phisicalMemory;

    public memoryManager(int lengthMem) {
        this.phisicalMemory = new SOProcess[lengthMem];
        printStatusMemory();
    }

    private void printStatusMemory(){
        for(int i = 0; i < Math.ceil(this.phisicalMemory.length/8.0); i++){
            for(int j = 0; j < 8; j++){
                if(i*8+j >= this.phisicalMemory.length){
                    System.out.println();
                    break;
                }
                if(phisicalMemory[i*8+j]==null){
                    System.out.print(phisicalMemory[i*8+j] + " | ");
                }else{System.out.print(phisicalMemory[i*8+j].getId() + " | ");}
            }
            System.out.println();
        }
    }
}