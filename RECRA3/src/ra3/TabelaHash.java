package ra3;

public class TabelaHash {
    private No[] tabela;
    private int tamanho;
    private int numeroColisoes;
    private int tipoHash; // 1 = resto da divis�o, 2 = multiplica��o, 3 = dobramento

    public TabelaHash(int tamanho, int tipoHash) {
        this.tamanho = tamanho; // define o tamanho da tabela
        this.tabela = new No[tamanho]; // cria um array com o numero das posicoes especificas
        this.numeroColisoes = 0; // inicia numero de colises como
        this.tipoHash = tipoHash; // define o tipo de hash que vai ser usado
    }

    private int hash(String chave) { // transforma um string em um indice no array da tabela
        int codigo = Integer.parseInt(chave);

        switch (tipoHash) {
            case 1: // Resto da divis�o, divide o numero pelo tamanho da tabela e pega o resto
                return codigo % tamanho;
            case 2: // Multiplica��o
                double A = 0.6180339887; // Proporcao aurea auxiliar na funcao hash, usada para reduzir o risco de colisoes
                return (int) (tamanho * (codigo * A % 1));
            case 3: // Dobramento, dividimos a chave em duas partes e somamos as duas partes e dividimos pelo resto
                int part1 = codigo / 1000;
                int part2 = codigo % 1000;
                return (part1 + part2) % tamanho;
            default:
                throw new IllegalArgumentException("Tipo de hash invalido");
        }
    }

    public void inserir(Registro registro) {
        int indice = hash(registro.getCodigo());

        if (tabela[indice] != null) { // verifice se j� existe um elemento no indice
            numeroColisoes++; // se for positivo ele incrementa o numero de colisoes pois houve uma colisao
        }

        No novoNo = new No(registro);
        novoNo.proximo = tabela[indice];
        tabela[indice] = novoNo;
    }

    public Registro buscar(String codigo) {
        int indice = hash(codigo);
        No atual = tabela[indice];

        while (atual != null) {
            if (atual.registro.getCodigo().equals(codigo)) { // percorre a lista at� encontrar um no
                return atual.registro; // retorna o atual registro
            }
            atual = atual.proximo;
        }

        return null;
    }

    public int getNumeroColisoes() {
        return numeroColisoes;
    }
}