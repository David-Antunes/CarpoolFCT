import java.util.Iterator;
import java.util.Scanner;

import CarpoolHandler.AlreadyExistsElementException;
import CarpoolHandler.CarpoolHandler;
import CarpoolHandler.CarpoolHandlerClass;
import CarpoolHandler.DateClass;
import CarpoolHandler.InvalidArgsException;
import CarpoolHandler.InvalidDateException;
import CarpoolHandler.InvalidPasswordException;
import CarpoolHandler.NoRideException;
import CarpoolHandler.NonExistingElementException;
import CarpoolHandler.Ride;
import CarpoolHandler.User;

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
	private static final String NO_REGIST = "Registo nao efetuado";
	private static final String USER_EXISTS = "Utilizador ja existente.";
	private static final String N_USERS = "Registo %d efetuado\n";
	private static final String NO_USER_EXISTS = "Utilizador nao existente.";
	private static final String NO_ENTRY = "Entrada nao realizada.";
	private static final String VISIT_NUM = "Visita %d efetuada\n";

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		CarpoolHandler ch = new CarpoolHandlerClass();
		String option = readOption(in, ch);
		do {
			executeOption(option, in, ch);
			option = readOption(in, ch);
		} while (!option.equals(TERMINA) || ch.hasCurrUser());
		System.out.println(GOODBYE);
		in.close();

	}

	// Reads the command that we want to execute

	private static String readOption(Scanner in, CarpoolHandler ch) {
		printPrompt(ch);
		return in.next().toUpperCase();
	}

	/**
	 * Prints prompt whit email if session is initiated and without it if the
	 * session is not
	 */

	private static void printPrompt(CarpoolHandler ch) {

		try {
			System.out.print(ch.userEmail() + " " + PROMPT);
		} catch (NonExistingElementException e) {
			System.out.print(PROMPT);
		}
	}

	/**
	 * Executes the option with two different ways depending if the session is
	 * initiated or not
	 */

	private static void executeOption(String option, Scanner in, CarpoolHandler ch) {
		if (ch.hasCurrUser())
			executeOptionSessionMode(option, in, ch);
		else
			executeOptionInicialMode(option, in, ch);
	}

	/**
	 * Executes commands when session is not initiated Commands are AJUDA TERMINA
	 * REGISTA ENTRADA
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
	 * Executes commands when session is not initiated Commands are SAI NOVA LISTA
	 * BOLEIA CONSULTA REMOVE
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
		boolean passCorrect = false;
		String password = null;
		String email = in.next();
		in.nextLine();
		try {
			ch.userExists(email);
			for (int i = 1; !passCorrect && i <= 3; i++) {
				System.out.print(PASSWORD);
				password = in.nextLine();
				if (ch.isPassCorrect(email, password, i))
					passCorrect = true;
			}

			ch.login(email);
			System.out.printf(VISIT_NUM, ch.nVisitas());

		} catch (NonExistingElementException e) {
			System.out.println(NO_USER_EXISTS);
		}

		catch (InvalidPasswordException e) {
			System.out.println(NO_ENTRY);
		}

	}

	private static void remove(Scanner in, CarpoolHandler ch) {
		// TODO Auto-generated method stub

	}

	private static void consulta(Scanner in, CarpoolHandler ch) {
		String email = in.next().trim();
		String date = in.next().trim();
		in.nextLine();

		try {
			Ride ride = ch.check(email, new DateClass(date));

			System.out.println(ride.getUser().getEmail());
			System.out.println(ride.getOrigin());
			System.out.println(ride.getDestination());
			System.out.printf("%d %d:%d %d\n", ride.getDate(), ride.getHour(), ride.getMinutes(), ride.getDuration());
			System.out.printf("Lugares vagos: %d\n", ride.getRemainingSeats());
			Iterator<User> it = ride.iterateUsers();
			System.out.print("Boleias: ");
			while (it.hasNext()) {
				System.out.print(it.next().getEmail());
				if (it.hasNext())
					System.out.print("; ");
			}
			System.out.print("\n");
			System.out.printf("Em espera: %d\n", ride.getUsersInQueue());

		} catch (NoRideException e) {
			System.out.println("Deslocacao nao existe.");
		} catch (NonExistingElementException e) {
			System.out.println("Utilizador inexistente.");
		} catch (InvalidDateException e) {
			System.out.println("Data invalida.");
		}
	}

	private static void boleia(Scanner in, CarpoolHandler ch) {
		String email = in.next().trim();
		String date = in.next().trim();

		try {
			if (email.equals(ch.getCurrUser().getEmail()))
				System.out.printf("%d  nao pode dar boleia a si proprio.\n", ch.getCurrUser().getName());
			else {
				int value = ch.addLift(email, new DateClass(date));
				if (value != 0)
					System.out.printf("Ficou na fila de espera %d.\n", value);
				else
					System.out.println("Boleia registada.");
			}
		} catch (NonExistingElementException e) {
			System.out.println("Utilizador inexistente.");
		} catch (InvalidDateException e) {
			System.out.println("Data invalida.");
		} catch (NoRideException e) {
			System.out.println("Deslocacao nao existe.");
		} catch (AlreadyExistsElementException e) {
			System.out.printf("%d ja registou uma boleia ou deslocacao nesta data.", ch.getCurrUser().getName());
		}
	}

	private static void lista(Scanner in, CarpoolHandler ch) {

	}

	private static void nova(Scanner in, CarpoolHandler ch) {
		try {
			in.nextLine();
			String origin = in.nextLine();
			String destiny = in.nextLine();
			String line = in.nextLine();
			String[] split = line.split(" ");
			String date = split[0];

			String[] time = split[1].split(":");
			int hour = Integer.parseInt(time[0]);
			int minutes = Integer.parseInt(time[1]);
			int duration = Integer.parseInt(split[2]);
			int seats = Integer.parseInt(split[3]);

			ch.Ride(origin, destiny, new DateClass(date), hour, minutes, duration, seats);

		} catch (NumberFormatException e) {
			System.out.println("Dados Invalidos.");
		} catch (InvalidArgsException e) {
			System.out.println("Dados Invalidos.");
		} catch (InvalidDateException e) {
			System.out.println(ch.getCurrUser().getName() + " ja tem uma deslocacao ou boleia registada nesta data.");
		}

	}

	private static void sai(CarpoolHandler ch) {
		// TODO Auto-generated method stub

	}

	/**
	 * Command AJUDA Prints menus when the session is and is not initiated
	 */

	private static void ajuda(Scanner in, CarpoolHandler ch) {
		System.out.println(COMM_AJUDA);

		if (!ch.hasCurrUser()) { // arranjar metodo ou assim para "dividir"
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
	 * Command TERMINA Pints goodbye message when the program stops executing
	 */

	private static void terminar() {

		System.out.println(GOODBYE);

	}

	/**
	 * Command REGISTA register new user
	 * 
	 * @throws NonExistingElementException
	 */

	private static void regista(Scanner in, CarpoolHandler ch) {
		boolean passValid = false;
		String name;
		String password = null;
		String email = in.next();
		in.nextLine();

		try {

			ch.hasUser(email);
			System.out.print(NAME);
			name = in.nextLine();
			for (int i = 1; !passValid && i <= 3; i++) {
				System.out.print(PASSWORD);
				password = in.nextLine();
				if (ch.validPassaword(password, i))
					passValid = true;
			}

			ch.register(email, name, password);
			System.out.printf(N_USERS, ch.nUsers());

		}

		catch (AlreadyExistsElementException e) {
			System.out.println(USER_EXISTS);
		}

		catch (InvalidPasswordException e) {
			System.out.println(NO_REGIST);
		}
	}
}
