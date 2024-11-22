package ra3;

import java.util.*;

public class GeradorDados {
    public static void main(String[] args) {
    	
        // Criu um objeto random com uma seed fixa(52) para gerar valores aleatorios
        Random random = new Random(52);

        // Definição manual dos tamanhos de tabela e conjunto de dados
        int[] tamanhosTabela = {1000, 10000, 100000}; // tamanho das tabelas
        int[] tiposHash = {1, 2, 3}; // 1 = resto da divisão, 2 = multiplicação, 3 = dobramento
        int[] tamanhosConjunto = {10000, 100000, 1000000}; // numero dos registros

        // funcao para calcular o tamanho dos arrays 
        int tamanhoTamanhosTabela = tamanhoArray(tamanhosTabela);
        int tamanhoTiposHash = tamanhoArray(tiposHash);
        int tamanhoTamanhosConjunto = tamanhoArray(tamanhosConjunto);

        // loop para cada conjunto de dados
        for (int i = 0; i < tamanhoTamanhosConjunto; i++) {
            int tamanhoConjunto = tamanhosConjunto[i]; // numero dos registros atuais
            System.out.println("\n\nInserindo " + tamanhoConjunto + " registros...");

            for (int j = 0; j < tamanhoTamanhosTabela; j++) {
                int tamanhoTabela = tamanhosTabela[j]; // ve o tamanho da tabela hash atual.

                for (int k = 0; k < tamanhoTiposHash; k++) {
                    int tipoHash = tiposHash[k]; // tipo de hash que vai ser utilizado no teste atual
                    TabelaHash tabela = new TabelaHash(tamanhoTabela, tipoHash); // cria a tabela com o tipo e tamanho especifico do hash

                    // registrar o tempo inicial em milissegundos
                    long tempoInicioInsercao = System.currentTimeMillis();

                    // Inserir dados na tabela de hash
                    for (int m = 0; m < tamanhoConjunto; m++) {
                        String codigo = gerarCodigoAleatorio(random); // gera um codigo aleatorio com 9 digito
                        tabela.inserir(new Registro(codigo)); // insere o codigo na tabela
                    }

                    // registrar o tempo final e calcular a duração
                    long tempoFinalInsercao = System.currentTimeMillis();
                    long duracaoInsercao = tempoFinalInsercao - tempoInicioInsercao;

                    System.out.println("\nTabela de tamanho " + tamanhoTabela + " usando tipo de hash " + tipoHash);
                    System.out.println("Tempo de inserção: " + duracaoInsercao + " ms.");
                    System.out.println("Número de colisões: " + tabela.getNumeroColisoes());

                    // realiza 5 buscas aleatórias com cada tipo de hash e tamanho de tabela
                    for (int n = 0; n < 5; n++) {
                        String codigoBusca = gerarCodigoAleatorio(random); // gera um codigo aleatorio pra realizar a busca
                        long tempoInicioBusca = System.currentTimeMillis(); // registra o inicio da busca
                        Registro resultadoBusca = tabela.buscar(codigoBusca); // realiza a busca na tabela
                        long tempoFinalBusca = System.currentTimeMillis(); // marca o fim da busca

                        long duracaoBusca = tempoFinalBusca - tempoInicioBusca; // calcula a duracao da busca

                        // verifica se o registro foi encontrado e exibe seu resultado
                        if (resultadoBusca != null) {
                            System.out.println("Registro com código " + resultadoBusca.getCodigo() + " encontrado em " + duracaoBusca + " ms.");
                        } else {
                            System.out.println("Registro com código " + codigoBusca + " não encontrado em " + duracaoBusca + " ms.");
                        }
                    }
                }
            }
        }
    }

    // metodo para gerar codigo aleatorio de 9 digitos
    public static String gerarCodigoAleatorio(Random random) {
        char[] codigoArray = new char[9];
        // array para armazenar os digitos do codigo
        for (int i = 0; i < 9; i++) {
            codigoArray[i] = (char) ('0' + random.nextInt(10)); // preenche cada posição com um dígito aleatorio ('0' a '9')
        }
        return new String(codigoArray);
    }

    // Método auxiliar para calcular o tamanho de um array
    public static int tamanhoArray(int[] array) {
        int tamanho = 0;
        for (int i = 0; ; i++) {
            try {
                int valor = array[i];
                tamanho++;
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        return tamanho;
    }
}