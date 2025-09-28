public abstract class Veiculo {
    protected String Matricula;
    protected String Marca;
    protected String Modelo;

    public abstract double calcularCustoManutencao();


    // Constructor

    public Veiculo(String matricula, String marca, String modelo) {
        Matricula = matricula;
        Marca = marca;
        Modelo = modelo;
    }


    // Getter

    public String getMatricula() {
        return Matricula;
    }

    public String getMarca() {
        return Marca;
    }

    public String getModelo() {
        return Modelo;
    }


    // Setter

    public void setMatricula(String matricula) {
        Matricula = matricula;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    @Override
    public String toString() {
        return "----* Veiculo *----" +"\n" +
                "Matricula: " + Matricula + " - " +
                "Marca: " + Marca + " - " +
                "Modelo: " + Modelo + "\n" +
                "--------------------";
    }
}
