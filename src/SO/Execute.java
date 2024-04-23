package SO;

public class Execute {
    public static void main(String[] args) {
    SOProcess p1 = SystemOperation.systemCall(systemCallType.CREATE_PROCESS, //Cria um proceso
                                              28,               //De tamanho 32
                                              SOPriority.BAIXA,              //Com prioridade baixa
                                              500);            //Que leva 500ms pra ser executado
    SystemOperation.systemCall(systemCallType.WRITE_PROCESS, p1);            //Escreve o processo p1 na memória
    System.out.println("******************** Processo 1 ********************");
                                              
    }
}