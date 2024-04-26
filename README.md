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

The process p1 has size 28, Low priority and 500ms to be executed. The last parameter define the strategy adopted by the memory manager.

After this, we can write the processes in memory. For that, we use this code:
```
SystemOperation.systemCall(systemCallType.WRITE_PROCESS, p1);            //Escreve o processo p1 na memória
```

It is also possible to delete processes from  memory by using the code below:
```
SystemOperation.systemCall(systemCallType.DELETE_PROCESS, p2);           //Deleta o processo p2
```
That code will be delete p2 from memory.

## System Operation

That class will communicate whith system devices, when the system need to write processes in memory, the system oparation call the memory manager to be that, when the system needs to run a process in cpu, the system operation call the cpu manager for that. It forwards user requests to the appropriate manager.

## Memory

### Memory Manager

The memory manager is responsible to do the user requests with memory manipulation. Process like write in memory, delete from the memory and read a memory. For write in memory, some techiniques are used as can be seen below:

- `First Fit`: Write in the first space you find that fits the entire process
- `Best Fit`: Write in the lowest space you find that fits the entire process
- `Worst Fit`: Write in the largest space you find that fits the entire process
- `Paging`: Writes using fixed-size pages, each process is then allocated to some pages
