import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import CarpoolHandler.AlreadyExistsElementException;
import CarpoolHandler.CarpoolHandler;
import CarpoolHandler.CarpoolHandlerClass;
import CarpoolHandler.NonExistingElementException;

public class Main {
	
	
	// Command Constants

	private static final String AJUDA = "AJUDA";
	private static final String TERMINA = "TERMINA";
	private static final String REGISTA = "REGISTA";
	private static final String ENTRADA = "ENTRADA";
	private static final String SAI = "SAI";
	private static final String NOVA = "NOVA";
	private static final String LISTA = "LISTA";
	private static final String BOLEIA = "BOLEIA";
	private static final String CONSULTA = "CONSULTA";
	private static final String REMOVE = "REMOVE";

	// Help menu constants
	private static final String COMM_AJUDA = "ajuda - Mostra os comandos existentes";
	private static final String COMM_SAI = "sai - Termina a sessao deste utilizador no programa";
	private static final String COMM_NOVA = "nova - Regista uma nova deslocacao";
	private static final String COMM_LISTA = "lista - Lista todas ou algumas deslocacoes registadas";
	private static final String COMM_BOLEIA = "boleia - Regista uma boleia para uma dada deslocacao";
	private static final String COMM_CONSULTA = "consulta - Lista a informacao de uma dada deslocacao";
	private static final String COMM_RETIRA = "retira - Retira uma dada boleia";
	private static final String COMM_REMOVE = "remove - Elimina uma dada deslocacao"; 
	private static final String COMM_TERMINA = "termina - Termina a execucao do programa";
	private static final String COMM_REGISTA = "regista - Regista um novo utilizador no programa";
	private static final String COMM_ENTRADA = "entrada - Permite a entrada (\"login\") dum utilizador no programa";

	private static final String PROMPT = ">";
	
	private static final String INV_COMM = "Comando invalido";

	
	private static final String GOODBYE = "Obrigado. Ate a proxima.";
	private static final String NAME = "nome (maximo 50 caracteres): ";
	private static final String PASSWORD = "password (entre 4 e 6 caracteres - digitos e letras): ";
	private static final String NO_REGIST = "Registo nao efetuado" ;
	private static final String USER_EXISTS = "Utilizador ja existente.";

	public static void main(String[] args) {
		

		Scanner in = new Scanner(System.in);
		CarpoolHandler ch = new CarpoolHandlerClass();
		String option = readOption(in, ch);
		do { 
			executeOption(option, in, ch);
			option = readOption(in, ch);
		} while (!option.equals(TERMINA) || ch.IsSectionStarted());
		System.out.println(GOODBYE);
		in.close();

	}

		// Reads the command that we want to execute

		private static String readOption(Scanner in, CarpoolHandler ch) {
			printPrompt(ch);
			return in.next().toUpperCase();
		}

		/**
		 * Prints prompt whit email if session is initiated and without it if the session is not
		 */

		private static void printPrompt(CarpoolHandler ch) {
			
			try {
				System.out.println(ch.getSessionEmail + " " + PROMPT); // metodo para devolver email do utilizador
			}
			catch(NonExistingElementException e) {
				System.out.println(PROMPT);
			}
		}

		/**
		 * Executes the option with two different ways depending if the session is initiated or not
		 */

		private static void executeOption(String option, Scanner in, CarpoolHandler ch) {
			if (ch.IsSectionStarted())  // este ou outro metodo para saber se a sessao esta iniciada
				executeOptionSessionMode(option, in, ch);
			else
				executeOptionInicialMode(option, in, ch);
		}

		/**
		 * Executes commands when session is not initiated 
		 * Commands are
		 * AJUDA TERMINA REGISTA ENTRADA
		 */

		private static void executeOptionInicialMode(String option, Scanner in, CarpoolHandler ch) {
			switch (option) {

			case AJUDA:
				ajuda(in, ch);
				break;

			case TERMINA:
				terminar();
				break;

			case REGISTA:
				regista(in, ch);
				break;

			case ENTRADA:
				entrada(in, ch);
				break;
			default:
				System.out.println(INV_COMM);
				in.nextLine();
				break;

			}

		}


		/**
		 * Executes commands when session is not initiated 
		 * Commands are
		 * SAI NOVA LISTA BOLEIA CONSULTA REMOVE
		 */

		private static void executeOptionSessionMode(String option, Scanner in, CarpoolHandler ch) {
			switch (option) {

			case AJUDA:
				ajuda(in, ch);
				break;

			case SAI:
				sai(ch);
				break;

			case NOVA:
				nova(in, ch);
				break;

			case LISTA:
				lista(in, ch);
				break;

			case BOLEIA:
				boleia(in, ch);
				break;

			case CONSULTA:
				consulta(in, ch);
				break;

			case REMOVE:
				remove(in, ch);
				break;

			default:
				System.out.println(INV_COMM);
				in.nextLine();
				break;

			}
		}
		
		private static void entrada(Scanner in, CarpoolHandler ch) {
			// TODO Auto-generated method stub
			
		}

		private static void remove(Scanner in, CarpoolHandler ch) {
			// TODO Auto-generated method stub
			
		}

		private static void consulta(Scanner in, CarpoolHandler ch) {
			// TODO Auto-generated method stub
			
		}

		private static void boleia(Scanner in, CarpoolHandler ch) {
			// TODO Auto-generated method stub
			
		}

		private static void lista(Scanner in, CarpoolHandler ch) {
			// TODO Auto-generated method stub
			
		}

		private static void nova(Scanner in, CarpoolHandler ch) {
			// TODO Auto-generated method stub
			
		}

		private static void sai(CarpoolHandler ch) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * Command AJUDA
		 * Prints menus when the session is and is not initiated
		 */

		private static void ajuda(Scanner in, CarpoolHandler ch) {
			System.out.println(COMM_AJUDA);

			if (ch.IsSectionStarted()) { // arranjar metodo ou assim para "dividir"
				System.out.println(COMM_SAI);
				System.out.println(COMM_NOVA);
				System.out.println(COMM_LISTA);
				System.out.println(COMM_BOLEIA);
				System.out.println(COMM_CONSULTA);
				System.out.println(COMM_RETIRA);
				System.out.println(COMM_REMOVE);
			} else {
				System.out.println(COMM_TERMINA);
				System.out.println(COMM_REGISTA);
				System.out.println(COMM_ENTRADA);

			}
		}

		/**
		 * Command TERMINA
		 * Pints goodbye message when the program stops executing
		 */

		private static void terminar() {

			System.out.println(GOODBYE);

		}
		

		/**
		 * Command REGISTA
		 * register new user
		 */

		private static void regista(Scanner in, CarpoolHandler ch) {
			boolean IsPassawordOk = false;
			String nome;
			String password = null;
			String email = in.next();
			in.nextLine();
			
			
			try {
				System.out.print(NAME);
				nome = in.nextLine();
				for (int i = 1; !IsPassawordOk && i <= 3; i++) { 
					System.out.print(PASSWORD);
					password = in.nextLine();
					
					if(ch.metodoQueConfirma(password)) {
						IsPassawordOk = true;
						
					}
					else 
						System.out.println(NO_REGIST);
			}
			}
				
				catch (AlreadyExistsElementException e){
					System.out.println(USER_EXISTS); 
				}
		}
}	
			

