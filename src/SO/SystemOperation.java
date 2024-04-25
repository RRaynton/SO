package SO;

import SO.Cpu.cpuManager;
import SO.Memory.Strategy;
import SO.Memory.memoryManager;

public class SystemOperation {

    private static memoryManager mm;
    private static cpuManager cm;
    public static int MEM_LENGTH = 256;

    public static SOProcess systemCall(systemCallType type, int sizeInMemory, SOPriority priority, int timeToExecute, Strategy strategy){
        if(type.equals(systemCallType.CREATE_PROCESS)){     //Caso se deseje criar um processo
            if(mm == null){     //Observa-se se o sistema ainda não possui uma memória com seu gerenciador
                mm = new memoryManager(MEM_LENGTH, strategy); //Caso não, então um é criado com a memória de tamanho MEM_LENGTH
            }
            if(cm == null){     //Caso não exista um gerenciador de cpu
                cm = new cpuManager();  //Cria-se um
            }
            return new SOProcess(sizeInMemory, priority, timeToExecute);    //Retorna o processo com seu tamanho, prioridade e tempo de execução
        }else{return null;} //Se o tipo não for Criação de processo, retorna vazio
    }

    public static SOProcess systemCall(systemCallType type, int sizeInMemory, SOPriority priority, int timeToExecute){
        return systemCall(type, sizeInMemory, priority, timeToExecute, Strategy.BEST_FIT);
    }

    public static void systemCall(systemCallType type, SOProcess p){    //Caso sejam passados apenas o tipo de operação e o processo
        if(type.equals(systemCallType.WRITE_PROCESS)){  //Verifica se o processo é de escrita
            mm.writeProcess(p);     //Escreve o processo utilizando a estratégia desejada
        }else if(type.equals(systemCallType.DELETE_PROCESS)){  //Verifica se o processo é de escrita
            mm.deleteProcess(p);     //Escreve o processo utilizando a estratégia desejada
        }
    }
}
