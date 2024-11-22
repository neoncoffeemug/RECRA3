package ra3;

public class Registro {
	
    private String codigo; // Cria o objeto codigo como privado limitar seu acesso por outras classes
  
    public Registro(String codigo) { // Permitindo criar instancia do valor do codigo, assim que o registro for chamado
        this.codigo = codigo; // this é usado para diferenciar o atributo da instância
    }

    public String getCodigo() {
        return codigo;
    }
}