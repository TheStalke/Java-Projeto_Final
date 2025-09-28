
import java.util.*;


public class Main {
    public static void main(String[] args) {

        Frota frota = new Frota();

        Scanner sc = new Scanner(System.in);
        int opcao;

        System.out.println("=== Bem-vindo à AutoComercio ===");

        frota.lerVeiculosTxt();

        do {
            // MENU PRINCIPAL
            System.out.println("\n----- MENU -----");
            System.out.println("1 - Listar veículos");
            System.out.println("2 - Adicionar veículo");
            System.out.println("3 - Remover veículo");
            System.out.println("4 - Listar por marca");
            System.out.println("5 - Mostrar custo total manutenção");
            System.out.println("6 - Adicionar veiculos via .txt");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> { //listarVeiculos
                    frota.listarVeiculos();
                }

                case 2 -> {
                    int opAdd;
                    do {
                        // SUBMENU ADICIONAR
                        System.out.println("\n--- Adicionar veículo ---");
                        System.out.print("Opção (1-Carro, 2-Moto, 0-Voltar): ");

                        opAdd = sc.nextInt();
                        sc.nextLine();

                        if (opAdd == 1) { // Carro

                            System.out.println(
                                    "\nRegras:"+
                                    "\nFormato: Carro;A1-B2-C3;Audi;A3;100.0;200.0" +
                                    "\nLetra Maiuscula ou numero de 09" +
                                    "\nExemplo: Mota;Matricula;Marca;Modelo;Double;Double\n\n"
                            );

                            System.out.print("Matricula: ");
                            String matricula = sc.nextLine();
                            System.out.print("Marca: ");
                            String marca = sc.nextLine();
                            System.out.print("Modelo: ");
                            String modelo = sc.nextLine();
                            System.out.print("Custo Base: ");
                            double base = sc.nextDouble();
                            System.out.print("Seguro: ");
                            double seguro = sc.nextDouble();

                            sc.nextLine();

                            Carro carro = new Carro(matricula, marca, modelo, seguro, base);

                            frota.adicionarVeiculo(carro);

                            // adiciona Carro

                        } else if (opAdd == 2) { // Moto
                            System.out.println(
                                    "\nRegras:"+
                                            "\nFormato: Carro;A1-B2-C3;Audi;A3;100.0;200.0" +
                                            "\nLetra Maiuscula ou numero de 09" +
                                            "\nExemplo: Mota;Matricula;Marca;Modelo;Double;Double\n\n"
                            );

                            System.out.print("Matricula: ");
                            String matricula = sc.nextLine();
                            System.out.print("Marca: ");
                            String marca = sc.nextLine();
                            System.out.print("Modelo: ");
                            String modelo = sc.nextLine();
                            System.out.print("Custo por Km: ");
                            double custoKm = sc.nextDouble();
                            System.out.print("Km Rodados: ");
                            double km = sc.nextDouble();

                            sc.nextLine();

                            Mota mota = new Mota(matricula, marca, modelo, custoKm, km);

                            frota.adicionarVeiculo(mota);

                            //adiciona Mota
                        }
                    } while (opAdd != 0);
                }

                case 3 -> {
                    String opRem;
                    do {
                        // SUBMENU REMOVER
                        System.out.println("\n--- Remover veículo ---");
                        System.out.print("Matricula do veículo (ou 0 para voltar): ");
                        opRem = sc.nextLine();

                        if (!Objects.equals(opRem, "0")) {

                            frota.removerVeiculo(opRem);

                            //remove mota
                        }
                    } while (!Objects.equals(opRem, "0"));
                }

                case 4 -> {
                    System.out.print("Marca: ");
                    String marcaBusca = sc.nextLine();

                    frota.listarPorMarca(marcaBusca);
                    //listarPorMarca
                }

                case 5 -> { //mostrarCustoTotal;
                    frota.mostrarCustoTotalManutencao();
                }

                case 6 -> {
                    System.out.println("\nRegras:"+
                            "\nFormato: Carro;A1-B2-C3;Audi;A3;100.0;200.0" +
                            "\nLetra Maiuscula ou numero de 09" +
                            "\nExemplo: Mota;Matricula;Marca;Modelo;Double;Double" +
                            "\n\nO ficheiro tem de ser .txt!");



                    System.out.println("\nInsira o caminho do ficheiro que vai ler: ");
                    String filepath = sc.nextLine();
                    if (filepath.endsWith(".txt")) {
                        frota.adicionarVeiculosTxt(filepath);

                    } else {
                        System.out.println("File não termina com .txt");
                    }
                }

                case 0 -> {
                    //guardarVeiculosTxt

                    frota.guardarVeiculosTxt();
                    System.out.println(
                            "\n-----------* A Sair *------------" +
                            "\nA sair da aplicação..." +
                            "\n---------------------------------");
                }

                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        

        sc.close();
    }
}

