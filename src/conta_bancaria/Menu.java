package conta_bancaria;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import conta_bancaria.controller.ContaController;
import conta_bancaria.model.Conta;
import conta_bancaria.model.ContaCorrente;
import conta_bancaria.model.ContaPoupanca;
import conta_bancaria.util.Cores;

public class Menu {
	public static void main(String[] args) {
		
		Scanner leia = new Scanner(System.in);
		
		ContaController contas = new ContaController();
		
		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;
		
		// Dados para teste
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000.00f, 100.00f);
		contas.cadastrar(cc1);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 123, 2, "Maria da Silva", 1000.00f, 12);
		contas.cadastrar(cp1);
		
		while (true) {
			System.out.print(Cores.TEXT_BLACK+ Cores.ANSI_YELLOW_BACKGROUND);
			System.out.println("╔═════════◇─◇──◇─◇═════════╗");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("╚═════════◇─◇──◇─◇═════════╝");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar nova conta                     ");
			System.out.println("            2 - Visualizar todas as contas           ");
			System.out.println("            3 - Buscar conta por número              ");
			System.out.println("            4 - Atualizar informações da conta       ");
			System.out.println("            5 - Excluir uma conta                    ");
			System.out.println("            6 - Realizar saque                       ");
			System.out.println("            7 - Realizar depósito                    ");
			System.out.println("            8 - Realizar trasnferência               ");
			System.out.println("            9 - Listar contas por titular            ");
			System.out.println("            0 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println(" ═══════════════════════════════════════════════════ ");
			System.out.println("─◇             Escolha a opção desejada:           ◇─");
			System.out.println(" ╚══════════════════════════════════════════════════╝");
					
		opcao = leia.nextInt();
		
		if (opcao == 0) {
				System.out.println("\nBanco do Brasil com Z - O seu futuro começa aqui!\n");
				sobre();
				leia.close();
				System.exit(0);
		}
		
			switch (opcao) {
				case 1:
					System.out.println("Criar nova conta.\n\n");
					
					System.out.println("Digite o número da Agência:");
					agencia = leia.nextInt();
						
					System.out.println("Digite o nome do Titular:");
					leia.skip("\\R");
					titular = leia.nextLine();
						
					System.out.println("Digite o tipo da conta( 1 - Cc | 2 - Cp:");
					tipo = leia.nextInt();
						
					System.out.println("Digite o saldo inicial da conta:");
					saldo = leia.nextFloat();
					
					switch(tipo) {
						case 1 ->{
									System.out.println("Digite o limite da conta:");
									limite = leia.nextFloat();
									contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
					}
		
						case 2 ->{
									System.out.println("Digite o dia do aniversário da conta:");
									aniversario = leia.nextInt();
									contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
							}
					}
					
					keyPress();
					break;
			
				case 2:
					System.out.println("Visualizar todas as contas.\n\n");
					contas.listarTodas();
					keyPress();
					break;
		
				case 3:
					System.out.println("Consultar dados conta por número.\n\n");
					
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					contas.procurarPorNumero(numero);
					
					keyPress();
					break;
		
				case 4:
					System.out.println("Atualizar informações da conta.\n\n");
					
					// Informe o número da conta
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					// Checar  se a conta existe
					Optional<Conta> conta = contas.buscarNaCollection(numero);
					
					
					// Existe?
					if(conta.isPresent()) {
						
						// Atualiza os dados
						System.out.println("Digite o número da Agência:");
						agencia = leia.nextInt();
							
						System.out.println("Digite o nome do Titular:");
						leia.skip("\\R");
						titular = leia.nextLine();
						
						// Recuperar o tipo da conta
						tipo = conta.get().getTipo();
							
						System.out.println("Digite o novo saldo da conta:");
						saldo = leia.nextFloat();
						
						// Identificar o tipo
						switch(tipo) {
						case 1 ->{ // Se for conta conrrente
									System.out.println("Digite o limite da conta:");
									limite = leia.nextFloat();
									contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
							}
		
						case 2 ->{ // Se for conta poupança
									System.out.println("Digite o dia do aniversário da conta:");
									aniversario = leia.nextInt();
									contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
							}
						}
						
					}else // Caso a conta não exista
						System.out.printf("\nA conta número %d não existe!", numero);
					
					keyPress();
					break;
		
				case 5:
					System.out.println("Excluir uma conta.\n\n");
					
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					contas.deletar(numero);
					
					keyPress();
					break;
		
				case 6:
					System.out.println("Realizar saque.\n\n");
					
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					System.out.println("Digite o valor do saque: ");
					valor = leia.nextFloat();
					
					contas.sacar(numero, valor);
							
					keyPress();
					break;
		
				case 7:
					System.out.println("Realizar depósito.\n\n");
					
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					System.out.println("Digite o valor do depósito: ");
					valor = leia.nextFloat();
					
					contas.depositar(numero, valor);
					
					keyPress();
					break;
		
				case 8:
					System.out.println("Realizar trasnferência.\n\n");
					
					System.out.println("Digite o número da conta de origem: ");
					numero = leia.nextInt();
					
					System.out.println("Digite o número da conta de destino: ");
					numeroDestino = leia.nextInt();					
					
					System.out.println("Digite o valor do depósito: ");
					valor = leia.nextFloat();
					
					contas.transferir(numero, numeroDestino, valor);
					
					keyPress();
					break;
				
				case 9:
					System.out.println("Consultar constas por titular");
					System.out.println("Digite o nome do titular:");
					leia.skip("\\R");
					titular = leia.nextLine();
					
					contas.listarPorTitular(titular);
					
					keyPress();
					break;
			
				default:
					System.out.println("Opção inválida. Tente novamente!\n");
					
					keyPress();
					break;
			}
		}
	}
		
		public static void sobre() {
			System.out.println("""
			ꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀ
			Projeto desenvolvido por Fernanda Murched
			Generation Brasil - fernandao@genstudents.org
			GitHub- https://github.com/FernandaMurched
			ꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀꕀ		
			""");
		}
		
		public static void keyPress() { 
			try {
		        System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
		        
		        // Lê apenas a tecla Enter e ignora outras teclas
		        int input;
		        while ((input = System.in.read()) != '\n') {
		            // Ignora qualquer outra tecla diferente do Enter
		            if (input == -1) {
		                throw new IOException("Entrada encerrada inesperadamente");
		            }
		        }
		        
		    } catch (IOException e) {
		        System.err.println("Erro de entrada/saída: " + e.getMessage());
		    } catch (Exception e) {
		        System.err.println("Ocorreu um erro ao processar a entrada: " + e.getMessage());
		    }
		}

}
