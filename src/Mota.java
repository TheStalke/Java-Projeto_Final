public class Mota extends Veiculo{

    private double custoPorKm;
    private double kmRodados;

    public Mota(String matricula, String marca, String modelo, double custoPorKm, double kmRodados) {
        super(matricula, marca, modelo);
        this.custoPorKm = custoPorKm;
        this.kmRodados = kmRodados;
    }

    public double calcularCustoManutencao(){
        return custoPorKm * kmRodados;
    }

    // Getter

    public double getCustoPorKm() {
        return custoPorKm;
    }

    public double getKmRodados() {
        return kmRodados;
    }


    // Setter


    public void setCustoPorKm(double custoPorKm) {
        this.custoPorKm = custoPorKm;
    }

    public void setKmRodados(double kmRodados) {
        this.kmRodados = kmRodados;
    }

    @Override
    public String toString() {
        return "----* Mota *----\n" +
                "Matricula: " + getMatricula() + " - " +
                "Marca: " + getMarca() + " - " +
                "Modelo: " + getModelo() + " - " +
                "Km Rodados" + getKmRodados() + " - " +
                "Custo de Manutenção: " + calcularCustoManutencao() + "\n" +
                "-----------------\n";
    }
}
