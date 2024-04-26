# Getting Started

This code implements an OS using some techniques to write process to memory or delete process from this memory.
 
# Folder Structure

For this, the file structure is as follow:
  - `Execute`: Main file where the system will run
  - `SystemOperation`: This class will communicate with the system devices
- `Memory`
  - `MemoryManager`: This class will manage system memory
- `CPU`
  - `CpuManager`: This class will manage the procesor cores

## Execute

The OS will run in this file. At the beginning, we'll create the processes that will be managed by the system. For this, the processes has an Id, a size, a priority and a time to be executed.
```
SOProcess p1 = SystemOperation.systemCall(systemCallType.CREATE_PROCESS, //Cria um proceso
                                              28,                         //De tamanho 28
                                              SOPriority.BAIXA,           //Com prioridade baixa
                                              500,                        //Que leva 500ms pra ser executado
                                              Strategy.BEST_FIT);         //Define a estratégia a ser utilizada
```
