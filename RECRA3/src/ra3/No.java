package ra3;

class No { // Nó guarda um valor do registro, caso seja uma referência para o proximo da lista
    Registro registro; 
    No proximo;

    public No(Registro registro) { // Construtor, chamado quando um np é criado
        this.registro = registro; // Atribiu valor ao argumento
        this.proximo = null;
    }
}