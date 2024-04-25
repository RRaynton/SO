package SO;

import SO.Memory.Strategy;

public class Execute {
    public static void main(String[] args) {
    SOProcess p1 = SystemOperation.systemCall(systemCallType.CREATE_PROCESS, //Cria um proceso
                                              28,               //De tamanho 28
                                              SOPriority.BAIXA,              //Com prioridade baixa
                                              500,             //Que leva 500ms pra ser executado
                                              Strategy.BEST_FIT);           //Define a estratégia a ser utilizada
    SOProcess p2 = SystemOperation.systemCall(systemCallType.CREATE_PROCESS, //Cria um proceso
                                              30,               //De tamanho 30
                                              SOPriority.BAIXA,              //Com prioridade baixa
                                              500);            //Que leva 500ms pra ser executado

    SOProcess p3 = SystemOperation.systemCall(systemCallType.CREATE_PROCESS, //Cria um proceso
                                              3,               //De tamanho 3
                                              SOPriority.BAIXA,              //Com prioridade baixa
                                              500);            //Que leva 500ms pra ser executado
    SOProcess p4 = SystemOperation.systemCall(systemCallType.CREATE_PROCESS, //Cria um proceso
                                              21,               //De tamanho 21
                                              SOPriority.BAIXA,              //Com prioridade baixa
                                              500);            //Que leva 500ms pra ser executado

SystemOperation.systemCall(systemCallType.WRITE_PROCESS, p1);            //Escreve o processo p1 na memória
System.out.println("******************** Processo 1 ********************");
SystemOperation.systemCall(systemCallType.WRITE_PROCESS, p2);            //Escreve o processo p2 na memória
System.out.println("******************** Processo 2 ********************");
SystemOperation.systemCall(systemCallType.WRITE_PROCESS, p3);            //Escreve o processo p3 na memória
System.out.println("******************** Processo 3 ********************");
SystemOperation.systemCall(systemCallType.DELETE_PROCESS, p2);           //Deleta o processo p2
SystemOperation.systemCall(systemCallType.WRITE_PROCESS, p4);            //Escreve o processo p4 na memória
System.out.println("******************** Processo 4 ********************");

    }
}