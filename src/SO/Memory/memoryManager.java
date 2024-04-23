package SO.Memory;

import SO.SOProcess;

public class memoryManager {
    private SOProcess[] phisicalMemory; //A memoria física é um array de processos

    public memoryManager(int lengthMem) {
        this.phisicalMemory = new SOProcess[lengthMem]; //A memória comporta "lengthMem" processos
        printStatusMemory();                            //Escreve a memória vazia pra verificar se está correta.
    }

    private void printStatusMemory(){
        int col = 8;    //Define que a memória será escrita com 8 colunas
        int row = (int) Math.ceil(this.phisicalMemory.length/col);  //A quantidade de linhas é dada pelo    //x-x-x-x-x-x-x-x
                                                                    //maior inteiro da divisão entre o      //x-x-x-x-x-x-x-x
                                                                    //tamanho da memória e a quantidade     //x-x-x-x-x-x-x-x
                                                                    //de colunas. Exemplo: Memoria tamanho  //x-x-x-x-x-x-x-x
                                                                    //50 com 8 colunas: 50/8 = 6,25 portanto//x-x-x-x-x-x-x-x
                                                                    //a quantidade de linhas será 7. Como   //x-x-x-x-x-x-x-x
                                                                    //pode ser visto ao lado                //x-x
        int position;                                                                               //linha/col 0- 1- 2- 3- 4- 5- 6- 7

        for(int i = 0; i < row; i++){       //Percosse a memória linha a linha                              0// 0- 1- 2- 3- 4- 5- 6- 7
            for(int j = 0; j < col; j++){   //Escrevendo coluna por coluna                                  1// 8- 9-10-11-12-13-14-15
                position = i*col+j;         //A posição da memória pode ser obtida com linha*tamCol+col     2//16-17-18-19-20-21-22-23
                                            //Exemplo: O processo escrito na coluna 5, linha 2 é o processo 3//24-25-26-27-28-29-30-31
                                            //2*8+5 = 21 como pode ser observado nas posições ao lado       4//32-33-34-35-36-37-38-39
                if(position >= this.phisicalMemory.length){ //Caso a posição que estou tentando escrever seja maior que o próprio tamanho da memória
                    System.out.println();                   //Pula a linha
                    break;                                  //E finaliza o loop
                }
                if(phisicalMemory[position]==null){         //Se ainda não houver um processo naquela posição
                    System.out.print(phisicalMemory[position] + " | "); //Escreva "null" e uma barra
                }else{                                      //Caso possua processo naquela posição
                    System.out.print(phisicalMemory[position].getId() + "   | "); //Escreva o Id deste processo
                }
            }
            System.out.println();   //Sempre que terminar de escrever as colunas de uma linha, pule a linha para iniciar a próxima linha
        }
    }

    public void writeProcess(Strategy strategy, SOProcess p){
        if(strategy.equals(Strategy.FIRST_FIT)){    //Caso a estratégia escolhida seja a First Fit
            writeWithFirstFit(p);   //Escreve com First Fit
        }
    }

    private void writeWithFirstFit(SOProcess p){

        boolean processFitsInSpace=true;    //Indica se o processo cabe ou não na memória
        for(int i=0; i<phisicalMemory.length; i++){ //Percorre toda a memoria
            if(phisicalMemory[i] == null ){         //Se o espaço analisado estiver vazio
                for(int j=i; j<i+p.getSizeInMemory(); j++){ //Percorre deste espaço até ele mais o tamanho do processo
                                                            //Exemplo: Pra escrever um processo de tamanho 4, ao verificar
                                                            //que a posição 12 da memória está vazia, verifica-se se as posições
                                                            //até antes do 16 também estão vazias: Posições (12,13,14,15)
                    if(phisicalMemory[j] != null ){ //Se não estiver vazio 
                        processFitsInSpace=false;   //Informa que o processo não cabe neste espaço
                        i=j+1;  //Passa a procurar espaços vazios depois do local que já possui processo.
                        break;  //E finaliza o for
                    }
                }
                if(processFitsInSpace){ //Caso o processo caiba no espaço
                    for(int k=i; k<(i+p.getSizeInMemory()); k++){   //Escreve um a um
                        this.phisicalMemory[k]=p;                   //o processo na memória
                    }
                    System.out.println("Processo inserido na memória"); //Informa que o processo foi devidamente escrito
                    break;                                          //Encerra o for pois o processo já foi escrito com sucesso
                }
            }
        }
        if(!processFitsInSpace){  //Caso após percorrer toda a memória o processo não caiba em lugar algum
            System.out.println("Não há espaço na memória"); //Informa que o processo não coube
        }
        printStatusMemory();    //Escreve como ficou a memória após a escrita
    }

}