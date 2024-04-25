package SO.Memory;

import SO.SOProcess;

public class memoryManager {
    private SOProcess[] phisicalMemory; //A memoria física é um array de processos
    private Strategy strategy;

    public memoryManager(int lengthMem, Strategy strategy) {
        this.phisicalMemory = new SOProcess[lengthMem]; //A memória comporta "lengthMem" processos
        this.strategy = strategy;
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

    public void writeProcess(SOProcess p){
        if(this.strategy.equals(Strategy.FIRST_FIT)){    //Caso a estratégia escolhida seja a First Fit
            writeWithFirstFit(p);   //Escreve com First Fit
        }else if(this.strategy.equals(Strategy.BEST_FIT)){    //Caso a estratégia escolhida seja a Best Fit
            writeWithBestFit(p);   //Escreve com Best Fit
        }else if(this.strategy.equals(Strategy.WROST_FIT)){    //Caso a estratégia escolhida seja a Best Fit
            writeWithWrostFit(p);   //Escreve com Best Fit
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
    
    private void writeWithBestFit(SOProcess p){
        boolean oneProcessFit=false;        //Indica se houve pelo menos um espaço em que ele coube
        int indiceBestFit=0;                //Indica o índice onde melhor coube o processo
        int realRestOfMem=phisicalMemory.length;    //Indica quanto espaço vazio ficará a frente do processo

        for(int i=phisicalMemory.length-1; i>=p.getSizeInMemory()-1; i--){ //Percorre toda a memoria
            boolean processFitsInSpace=true;    //Indica se o processo cabe ou não na memória
            if(phisicalMemory[i] == null ){         //Se o espaço analisado estiver vazio
                int endOfProcess = i-(p.getSizeInMemory()-1);   //O fim do processo estará no local encontrado mais o tamano do processo
                if(endOfProcess>=0){
                    for(int j=i; j>=endOfProcess; j--){     //Percorre deste espaço até o fim do processo
                                                            //Exemplo: Pra escrever um processo de tamanho 4, ao verificar
                                                            //que a posição 12 da memória está vazia, verifica-se se as posições
                                                            //até antes do 16 também estão vazias: Posições (12,13,14,15)
                        if(phisicalMemory[j] != null){ //Se não estiver vazio
                            processFitsInSpace=false;   //Informa que o processo não cabe neste espaço
                            i=j;  //Passa a procurar espaços vazios depois do local que já possui processo.
                            break;  //E finaliza o for
                        }
                    }
                    if(processFitsInSpace){ //Caso o processo caiba no espaço
                        int leftRemaing = 0;   //O espaço restante após o processo inicia em zero
                        int rightRemaing = 0;   //O espaço restante após o processo inicia em zero
                        while(phisicalMemory[endOfProcess-leftRemaing] == null){
                            if(endOfProcess-leftRemaing == 0)
                                break;
                                                    //Caso este espaço esteja vazio e não chegou ao fim da memória
                            leftRemaing++;  //Aumente um ao espaço vazio restante
                        }
                        while(phisicalMemory[i+rightRemaing] == null){
                            if(i+rightRemaing == phisicalMemory.length-1)
                                break;
                                                    //Caso este espaço esteja vazio e não chegou ao fim da memória
                            rightRemaing++;  //Aumente um ao espaço vazio restante
                        }
                        if(leftRemaing+rightRemaing<=realRestOfMem){  //Se o espaço vazio restante for menor que o menor já visto
                            realRestOfMem=leftRemaing+rightRemaing;  //O menor já visto então será esse
                            indiceBestFit=i;                    //Salva o indice desse
                            oneProcessFit=true;                 //Indica que um processo coube na memória
                        }
                    }
                }
            }
        }

        if(oneProcessFit){  //Caso após percorrer toda a memória, pelo menos um coube
            for(int k=indiceBestFit; k>=indiceBestFit-(p.getSizeInMemory()-1); k--){   //Escreve um a um
                this.phisicalMemory[k]=p;                         //o processo na memória
            }
            System.out.println("Processo inserido na memória"); //Informa que o processo foi devidamente escrito
        }else{                                                    //Caso não caiba na memória
            System.out.println("Não há espaço na memória");     //Informa que o processo não coube
        }
        printStatusMemory();    //Escreve como ficou a memória após a escrita
    }

    private void writeWithWrostFit(SOProcess p){
        boolean oneProcessFit=false;        //Indica se houve pelo menos um espaço em que ele coube
        int indiceBestFit=0;                //Indica o índice onde melhor coube o processo
        int realRestOfMem=0;    //Indica quanto espaço vazio ficará a frente do processo

        for(int i=phisicalMemory.length-1; i>=p.getSizeInMemory()-1; i--){ //Percorre toda a memoria
            boolean processFitsInSpace=true;    //Indica se o processo cabe ou não na memória
            if(phisicalMemory[i] == null ){         //Se o espaço analisado estiver vazio
                int endOfProcess = i-(p.getSizeInMemory()-1);   //O fim do processo estará no local encontrado mais o tamano do processo
                if(endOfProcess>=0){
                    for(int j=i; j>=endOfProcess; j--){     //Percorre deste espaço até o fim do processo
                                                            //Exemplo: Pra escrever um processo de tamanho 4, ao verificar
                                                            //que a posição 12 da memória está vazia, verifica-se se as posições
                                                            //até antes do 16 também estão vazias: Posições (12,13,14,15)
                        if(phisicalMemory[j] != null){ //Se não estiver vazio
                            processFitsInSpace=false;   //Informa que o processo não cabe neste espaço
                            i=j;  //Passa a procurar espaços vazios depois do local que já possui processo.
                            break;  //E finaliza o for
                        }
                    }
                    if(processFitsInSpace){ //Caso o processo caiba no espaço
                        int leftRemaing = 0;   //O espaço restante após o processo inicia em zero
                        int rightRemaing = 0;   //O espaço restante após o processo inicia em zero
                        while(phisicalMemory[endOfProcess-leftRemaing] == null){
                            if(endOfProcess-leftRemaing == 0)
                                break;
                                                    //Caso este espaço esteja vazio e não chegou ao fim da memória
                            leftRemaing++;  //Aumente um ao espaço vazio restante
                        }
                        while(phisicalMemory[i+rightRemaing] == null){
                            if(i+rightRemaing == phisicalMemory.length-1)
                                break;
                                                    //Caso este espaço esteja vazio e não chegou ao fim da memória
                            rightRemaing++;  //Aumente um ao espaço vazio restante
                        }
                        if(leftRemaing+rightRemaing>=realRestOfMem){  //Se o espaço vazio restante for maior que o maior já visto
                            realRestOfMem=leftRemaing+rightRemaing;  //O maior já visto então será esse
                            indiceBestFit=i;                    //Salva o indice desse
                            oneProcessFit=true;                 //Indica que um processo coube na memória
                        }
                    }
                }
            }
        }

        if(oneProcessFit){  //Caso após percorrer toda a memória, pelo menos um coube
            for(int k=indiceBestFit; k>=indiceBestFit-(p.getSizeInMemory()-1); k--){   //Escreve um a um
                this.phisicalMemory[k]=p;                         //o processo na memória
            }
            System.out.println("Processo inserido na memória"); //Informa que o processo foi devidamente escrito
        }else{                                                    //Caso não caiba na memória
            System.out.println("Não há espaço na memória");     //Informa que o processo não coube
        }
        printStatusMemory();    //Escreve como ficou a memória após a escrita
    }

    public void deleteProcess(SOProcess p){
        for(int i=0; i<phisicalMemory.length; i++){             //Percorre a memória
            if(phisicalMemory[i].getId().equals(p.getId())){    //Se encontrar o Id do processo
                for(int j=i; j<i+p.getSizeInMemory();j++){      //Percorre deste ponto até o tamanho do processo
                    phisicalMemory[j] = null;                   //Apagando a memória nestas posições
                }
                break;                                          //Interrompe o for
            }
        }
    }
}