package ra3;

class No { // N� guarda um valor do registro, caso seja uma refer�ncia para o proximo da lista
    Registro registro; 
    No proximo;

    public No(Registro registro) { // Construtor, chamado quando um np � criado
        this.registro = registro; // Atribiu valor ao argumento
        this.proximo = null;
    }
}