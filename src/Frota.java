import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frota {

    public List<Veiculo> veiculos = Collections.synchronizedList(new ArrayList<>());

    private Thread ThreadGuardar = null; // iniciar as variaveis
    private Thread ThreadLer = null;

    String filepath = System.getProperty("user.dir") + "/veiculos.txt";

    public void adicionarVeiculo(Veiculo veiculoInput){

        if (!validarFormato(veiculoInput.getMatricula())) {
            System.out.println("\nMatrícula inválida: " + veiculoInput.getMatricula());
            return;
        }

        if (!validarMatricula(veiculoInput.getMatricula())) {   // = if (validarMatricula(veiculoInput.getMatricula())) == false
            System.out.println("\nMatrícula já registada: " + veiculoInput.getMatricula());
            return;
        }

        // adiciona o veiculo e imprime os detalhes do inserido
        veiculos.add(veiculoInput);
            System.out.println(
                    "------* Veiculo adicionado *-------" +
                    "\nMatrícula: " + veiculoInput.getMatricula() +
                    "\nMarca: " + veiculoInput.getMarca() +
                    "\nModelo: " + veiculoInput.getModelo() +
                    "\nCusto Manutenção: " + veiculoInput.calcularCustoManutencao() +
                    "\n---------------------------------");
    }

    public void removerVeiculo(String matricula) {
        for(Veiculo veiculo: veiculos){         // para cada veiculo da variavel Veiculo na lista veiculos
            if (veiculo.getMatricula().equals(matricula)){
                veiculos.remove(veiculo); // remove o veiculo inserido
                System.out.println("O veículo com a matrícula: " + matricula + " foi removido com sucesso!");
                return;
            }
        }
        System.out.println("O veículo com a matrícula: " + matricula + " não existe!");
    }

    public void listarVeiculos(){
        if (veiculos.isEmpty()) {

            System.out.println(
                    "\n------* Lista de Veículos *------\n" +
                    "Não existem veículos registados.\n" +
                    "---------------------------------");
        } else {
            System.out.println("------* Lista de Veículos *------\n");
            for (Veiculo veiculo : veiculos) {
                System.out.println(veiculo);
            }
            System.out.println("---------------------------------");
        }
    }

    public void mostrarCustoTotalManutencao(){
        if (veiculos.isEmpty()) {
            System.out.println(
                            "\n------* Lista de Veículos *------\n" +
                            "Não existem veículos registados.\n" +
                            "---------------------------------");
            return;
        }

        double total = 0.0;
        for (Veiculo veiculo: veiculos){
            total += veiculo.calcularCustoManutencao();
        }
        System.out.println(
                "\n------* Custo Total *-----------------" +
                "\nCusto total de todos os veiculos: " + total+"€" +
                "\n--------------------------------------");
    }

    public void listarPorMarca(String marca){
        int contador = 0;
            if (veiculos.isEmpty()) {
                System.out.println(
                        "\n------* Lista de Veículos *------\n" +
                                "Não existem veículos registados.\n" +
                                "---------------------------------");
                return;
            }
            for(Veiculo veiculo: veiculos){
                if (veiculo.getMarca().equalsIgnoreCase(marca)){
                    System.out.println(veiculo);
                    contador++;
                }
            }
            if (contador == 0 ) {
                System.out.println(
                                "\n------* Lista de Veículos *-----------------" +
                                "\n- Não existem veiculos da marca: "+ marca +"." +
                                "\n------------------------------------------------");
            }
        }

    public void guardarVeiculosTxt(){
        ThreadGuardar = new Thread(() -> {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
                for (Veiculo veiculo : veiculos) {
                    if (veiculo instanceof Carro carro) {

                        bw.write("Carro;");
                        bw.write(carro.getMatricula() + ";");
                        bw.write(carro.getMarca() + ";");
                        bw.write(carro.getModelo() + ";");
                        bw.write(String.valueOf(carro.getCustoBaseManutencao()) + ";");
                        bw.write(String.valueOf(carro.getSeguroAnual()));

                    } else if (veiculo instanceof Mota mota) {

                        bw.write("Mota;");
                        bw.write(mota.getMatricula() + ";");
                        bw.write(mota.getMarca() + ";");
                        bw.write(mota.getModelo() + ";");
                        bw.write(String.valueOf(mota.getCustoPorKm()) + ";");
                        bw.write(String.valueOf(mota.getKmRodados()));

                    }
                    bw.newLine();
                }
                }catch (IOException e) {
                    System.out.println("Erro ao escrever ficheiro ou escrever ficheiro: " + e.getMessage());
                }

        });

        ThreadGuardar.start();

        try {
            ThreadGuardar.join(); // não deixa o programa terminar antes da thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void lerVeiculosTxt(){
        ThreadLer = new Thread(() -> {
            try{
                BufferedReader br = new BufferedReader(new FileReader(filepath));
                String linha;

                while ((linha = br.readLine()) != null) {

                    String[] campos = linha.split(";");

                    if (campos[0].equalsIgnoreCase("Carro")) {
                        String matricula = campos[1];
                        String marca = campos[2];
                        String modelo = campos[3];
                        double custoBase = Double.parseDouble(campos[4]);
                        double seguro = Double.parseDouble(campos[5]);

                        Carro carro = new Carro(matricula, marca, modelo, seguro, custoBase);
                        veiculos.add(carro);
                    }else if (campos[0].equalsIgnoreCase("Mota")) {
                        String matricula = campos[1];
                        String marca = campos[2];
                        String modelo = campos[3];
                        double custoPorKm = Double.parseDouble(campos[4]);
                        double kmRodados = Double.parseDouble(campos[5]);

                        Mota mota = new Mota(matricula, marca, modelo, custoPorKm, kmRodados);
                        veiculos.add(mota);
                    }
                }
            }catch (IOException e) {
                System.out.println("Erro ao ler ou escrever ficheiro: " + e.getMessage());
            }catch(NumberFormatException e) {
                System.out.println("Erro: linha contém valor não numérico");
            }
        });


        ThreadLer.start();
    }



    public void adicionarVeiculosTxt(String newFilepath){
        try{
            BufferedReader br = new BufferedReader(new FileReader(newFilepath));
            String linha;

            while ((linha = br.readLine()) != null) {

                String[] campos = linha.split(";");

                if (campos[0].equalsIgnoreCase("Carro")) {
                    String matricula = campos[1];
                    String marca = campos[2];
                    String modelo = campos[3];
                    double custoBase = Double.parseDouble(campos[4]);
                    double seguro = Double.parseDouble(campos[5]);

                    if (!validarFormato(matricula)) {
                        System.out.println("Matrícula inválida: " + matricula);
                        continue;
                    }

                    if (!validarMatricula(matricula)) {
                        System.out.println("Matrícula já registada: " + matricula);
                        continue;
                    }


                    Carro carro = new Carro(matricula, marca, modelo, seguro, custoBase);
                    veiculos.add(carro);

                }else if (campos[0].equalsIgnoreCase("Mota")) {
                    String matricula = campos[1];
                    String marca = campos[2];
                    String modelo = campos[3];
                    double custoPorKm = Double.parseDouble(campos[4]);
                    double kmRodados = Double.parseDouble(campos[5]);

                    if (!validarFormato(matricula)) {   // se retornar diferente de verdadeiro é porque deu erro (falso)
                        System.out.println("Matrícula inválida: " + matricula);
                        continue;
                    }
                    if (!validarMatricula(matricula)) {
                        System.out.println("Matrícula já registada: " + matricula); // if (validarMatricula(matricula) == false
                        continue;
                    }


                    Mota mota = new Mota(matricula, marca, modelo, custoPorKm, kmRodados);
                    veiculos.add(mota);
                }
            }
        }catch (IOException e) {
            System.out.println("Erro ao ler ou escrever ficheiro: " + e.getMessage());
        }catch(NumberFormatException e) {
            System.out.println("Erro: linha contém valor não numérico");
        }
    }

    public static boolean validarFormato(String matricula) {
        String regex = "([A-Z0-9]{2})-([A-Z0-9]{2})-([A-Z0-9]{2})";  // A-Z (qualquer número de A a Z) 0-9 qualquer num de 0 a 9 {2} (2 digitos)
        return matricula.matches(regex);
    }

    public boolean validarMatricula(String matricula){
        for (Veiculo veiculo: veiculos){
            if (matricula.equals(veiculo.getMatricula())){
                // Erro
                return false;
            }
        }
        return true; // passou retorna verdadeiro porque passou e falso porque errou (aka validarKMatricula true passou false não passou)
    }
}

