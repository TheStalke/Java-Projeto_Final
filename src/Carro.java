public class Carro extends Veiculo{

    private double custoBaseManutencao;
    private  double seguroAnual;


    public Carro(String matricula, String marca, String modelo, double seguroAnual, double custoBaseManutencao) {
        super(matricula, marca, modelo);
        this.seguroAnual = seguroAnual;
        this.custoBaseManutencao = custoBaseManutencao;
    }

    public double calcularCustoManutencao(){
            return custoBaseManutencao + seguroAnual;
    }


    // Getter

    public double getCustoBaseManutencao() {
        return custoBaseManutencao;
    }

    public double getSeguroAnual() {
        return seguroAnual;
    }

    // Setter


    public void setCustoBaseManutencao(double custoBaseManutencao) {
        this.custoBaseManutencao = custoBaseManutencao;
    }

    public void setSeguroAnual(double seguroAnual) {
        this.seguroAnual = seguroAnual;
    }

    @Override
    public String toString() {
        return "----* Carro *----\n" +
                "Matricula: " + getMatricula() + " - " +
                "Marca: " + getMarca() + " - " +
                "Modelo: " + getModelo() + " - " +
                "Custo de Manutenção: " + calcularCustoManutencao() + "\n" +
                "-----------------\n";
    }
}
