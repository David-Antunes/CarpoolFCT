import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import CarpoolExceptions.AlreadyExistsElementException;
import CarpoolExceptions.InvalidArgsException;
import CarpoolExceptions.InvalidDateException;
import CarpoolExceptions.InvalidPasswordException;
import CarpoolExceptions.NoRideException;
import CarpoolExceptions.NonExistingElementException;
import CarpoolExceptions.SameUserException;
import CarpoolHandler.CarpoolHandler;
import CarpoolHandler.CarpoolHandlerClass;
import Date.Date;
import Date.DateClass;
import Rides.Ride;
import Users.User;
import dataStructures.Iterator;
import dataStructures.NoElementException;
/**
 * 
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 *
 */
public class Main {

	private static final String FILENAME = "savefile";
	private static final String NAO_EXISTE_O_UTILIZADOR_DADO = "Nao existe o utilizador dado.";
	private static final String SEM_DESLOCACOES = "Sem deslocacoes.";
	private static final String TODAS = "todas";
	private static final String BOLEIAS_ARG = "boleias";
	private static final String MINHAS = "minhas";
	private static final String EM_ESPERA_D = "Em espera: %d\n";
	private static final String SEM_BOLEIAS_REGISTADAS = "Sem boleias registadas.";
	private static final String LUGARES_VAGOS_D = "Lugares vagos: %d\n";
	private static final String BOLEIAS = "Boleias: ";
	private static final String RIDE_INFO_FORMAT = "%s %d:%d %d\n";
	private static final String LOCATION_FORMAT = "%s-%s\n";
	private static final String S_NESTA_DATA_NAO_TEM_REGISTO_DE_BOLEIA = "%s nesta data nao tem registo de boleia.\n";
	private static final String S_BOLEIA_RETIRADA = "%s boleia retirada.\n";
	private static final String S_JA_REGISTOU_UMA_BOLEIA_OU_DESLOCACAO_NESTA_DATA = "%s ja registou uma boleia ou deslocacao nesta data.\n";
	private static final String DESLOCACAO_NAO_EXISTE = "Deslocacao nao existe.";
	private static final String UTILIZADOR_INEXISTENTE = "Utilizador inexistente.";
	private static final String S_NAO_PODE_DAR_BOLEIA_A_SI_PROPRIO = "%s nao pode dar boleia a si proprio.\n";
	private static final String BOLEIA_REGISTADA = "Boleia registada.";
	private static final String FICOU_NA_FILA_DE_ESPERA_POSICAO_D = "Ficou na fila de espera (posicao %d).\n";
	private static final String S_JA_NAO_PODE_ELIMINAR_ESTA_DESLOCACAO = "%s ja nao pode eliminar esta deslocacao.\n";
	private static final String S_NESTA_DATA_NAO_TEM_REGISTO_DE_DESLOCACAO = "%s nesta data nao tem registo de deslocacao.\n";
	private static final String DATA_INVALIDA = "Data invalida.";
	private static final String S_JA_TEM_UMA_DESLOCACAO_OU_BOLEIA_REGISTADA_NESTA_DATA = "%s ja tem uma deslocacao ou boleia registada nesta data.\n";
	private static final String DADOS_INVALIDOS = "Dados Invalidos.";
	private static final String DESLOCACAO_D_REGISTADA_OBRIGADO_S = "Deslocacao %d registada. Obrigado %s.\n";
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
	private static final String RETIRA = "RETIRA";

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

	private static final String PROMPT = "> ";

	private static final String INV_COMM = "Comando invalido.";

	private static final String GOODBYE = "Obrigado. Ate a proxima.";
	private static final String NAME = "nome (maximo 50 caracteres): ";
	private static final String PASSWORD = "password (entre 4 e 6 caracteres - digitos e letras): ";
	private static final String NO_REGIST = "Registo nao realizado.";
	private static final String USER_EXISTS = "Utilizador ja existente.";
	private static final String N_USERS = "Registo %d efetuado.\n";
	private static final String NO_USER_EXISTS = "Utilizador nao existente.";
	private static final String NO_ENTRY = "Entrada nao realizada.";
	private static final String VISIT_NUM = "Visita %d efetuada.\n";
	private static final String LEAVE_SESSION = "Ate a proxima %s.\n";
	private static final String RIDE_REMOVE = "Deslocacao removida.";

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		CarpoolHandler ch = loadFile();
		String option = readOption(in, ch);
		do {
			executeOption(option, in, ch);
			option = readOption(in, ch);
		} while (!option.equals(TERMINA) || ch.hasCurrUser());
		System.out.println(GOODBYE);
		in.close();
		writeFile(ch);

	}

	/**
	 * Writes the respective prompt, regarding if it is in session mode or not, and
	 * reads the command that we want to execute
	 * 
	 * @param in
	 * @param ch
	 * @return the
	 */
	private static String readOption(Scanner in, CarpoolHandler ch) {
		printPrompt(ch);
		return in.next().toUpperCase();
	}

	/**
	 * Prints prompt whit email if session is initiated and without it if the
	 * session is not
	 * 
	 * @param ch - The object that handles the users, and the operations regarding
	 *           rides
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
	 * 
	 * @param option - the command to be run
	 * @param in     - Object that handles the I/O needed for the user
	 * @param ch     - The object that handles the users, and the operations
	 *               regarding rides
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
	 * 
	 * @param option - the command to be run
	 * @param in     - Object that handles the I/O needed for the user
	 * @param ch     - The object that handles the users, and the operations
	 *               regarding rides
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
	 * 
	 * @param option - the command to be run
	 * @param in     - Object that handles the I/O needed for the user
	 * @param ch     - The object that handles the users, and the operations
	 *               regarding rides
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

		case RETIRA:
			retira(in, ch);
			break;

		default:
			System.out.println(INV_COMM);
			in.nextLine();
			break;

		}
	}

	/**
	 * After executing command REGISTA, the user must input an email, a name and a
	 * password. If the provided email already exists, it will print the
	 * corresponding message. If the email is unique, then the program will prompt
	 * for the user to write its name, the name must not be over 50 characters.
	 * After that, the user must input a password that has more than 4 and less than
	 * 6 (both inclusive) with only letters or numbers. If the password does not
	 * meet the requirements, the user will be prompted 2 more times till the
	 * program returns to its initial state before running REGISTA command.
	 * 
	 * @param in - Object that handles the I/O needed for the user
	 * @param ch - The object that handles the users, and the operations regarding
	 *           rides
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

	/**
	 * After executing command ENTRADA, the user must input its email and password
	 * so it can access its rides and lifts. If the user misses its password 3 times
	 * the program will return to its initial state previous of running the command
	 * ENTRADA. If the user types the password correctly, the program will run in
	 * session mode, having additional commands. run AJUDA to see new commands when
	 * in session mode.
	 * 
	 * @param in - Object that handles the I/O needed for the user
	 * @param ch - The object that handles the users, and the operations regarding
	 *           rides
	 */
	private static void entrada(Scanner in, CarpoolHandler ch) {
		boolean passCorrect = false;
		String password = null;
		String email = in.next();
		in.nextLine();
		try {
			ch.userExists(email);
			for (int i = 1; !passCorrect && i <= 3; i++) {
				System.out.print("password: ");
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

	/**
	 * After executing command NOVA while in session mode, the user must provide the
	 * name of the origin and destination of the ride; a date in the format d-m-y,
	 * being d day, m month, y year; a timer in the format hh:mm; the duration of
	 * the ride; and its available seats. If there are errors in the input the ride
	 * will not be registered.
	 * 
	 * @param in - Object that handles the I/O needed for the user
	 * @param ch - The object that handles the users, and the operations regarding
	 *           rides
	 */
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
			System.out.printf(DESLOCACAO_D_REGISTADA_OBRIGADO_S, ch.getCurrUser().getNumberOfRides(),
					ch.getCurrUser().getName());

		} catch (NumberFormatException e) {
			System.out.println(DADOS_INVALIDOS);
		} catch (InvalidArgsException e) {
			System.out.println(DADOS_INVALIDOS);
		} catch (InvalidDateException e) {
			System.out.printf(S_JA_TEM_UMA_DESLOCACAO_OU_BOLEIA_REGISTADA_NESTA_DATA, ch.getCurrUser().getName());
		}

	}

	/**
	 * After executing command REMOVE while in session mode, the user must provide a
	 * date in the format d-m-y, being d day, m month, y year. Given the date, if
	 * the date is not correctly formatted, it will print on console "Data
	 * invalida.". If the date is correctly formatted the user does not have a ride
	 * in that date it will print on console "UserName nesta data nao tem registo de
	 * deslocacao.". If there is a ride in the provided date, but it already has
	 * other users registered in it, will be printed on console "UserName ja nao
	 * pode eliminar esta deslocacao.". If there is a ride in the provided date with
	 * no registered users, it will be removed and then be printed on console
	 * "Deslocacao removida."
	 * 
	 * @param in - Object that handles the I/O needed for the user
	 * @param ch - The object that handles the users, and the operations regarding
	 *           rides
	 */
	private static void remove(Scanner in, CarpoolHandler ch) {
		String date = in.next().trim();
		in.nextLine();

		try {
			ch.remove(new DateClass(date));
			System.out.println(RIDE_REMOVE);
		} catch (InvalidDateException e) {
			System.out.println(DATA_INVALIDA);
		} catch (NonExistingElementException e) {
			System.out.printf(S_NESTA_DATA_NAO_TEM_REGISTO_DE_DESLOCACAO, ch.getCurrUser().getName());
		} catch (AlreadyExistsElementException e) {
			System.out.printf(S_JA_NAO_PODE_ELIMINAR_ESTA_DESLOCACAO, ch.getCurrUser().getName());
		}

	}

	/**
	 * After executing command REMOVE while in session mode, the user must provide a
	 * date in the format d-m-y, being d day, m month, y year, and a valid email. If
	 * the user with the given email has a ride created in the given date, the
	 * current user will join the ride. If the ride is full, the current user will
	 * be put on the waiting line.
	 * 
	 * @param in - Object that handles the I/O needed for the user
	 * @param ch - The object that handles the users, and the operations regarding
	 *           rides
	 */
	private static void boleia(Scanner in, CarpoolHandler ch) {
		String email = in.next().trim();
		String date = in.next().trim();

		try {

			int value = ch.addLift(email, new DateClass(date));
			if (value != 0)
				System.out.printf(FICOU_NA_FILA_DE_ESPERA_POSICAO_D, value);
			else
				System.out.println(BOLEIA_REGISTADA);
		} catch (SameUserException e) {
			System.out.printf(S_NAO_PODE_DAR_BOLEIA_A_SI_PROPRIO, ch.getCurrUser().getName());
		} catch (NonExistingElementException e) {
			System.out.println(UTILIZADOR_INEXISTENTE);
		} catch (InvalidDateException e) {
			System.out.println(DATA_INVALIDA);
		} catch (NoRideException e) {
			System.out.println(DESLOCACAO_NAO_EXISTE);
		} catch (AlreadyExistsElementException e) {
			System.out.printf(S_JA_REGISTOU_UMA_BOLEIA_OU_DESLOCACAO_NESTA_DATA, ch.getCurrUser().getName());
		}
	}

	/**
	 * After executing command RETIRA while in session mode, the user must provide a
	 * date in the format d-m-y, being d day, m month, y year. Given the date, if
	 * the date is not correctly formatted, it will print on console "Data
	 * invalida.". If the date is correctly formatted the user does not have a lift
	 * in that date it will print on console "UserName nesta data nao tem registo de
	 * boleia.". If there is a lift in the provided date, the lift will be removed
	 * and then it will be printed on console "UserName boleia retirada."
	 * 
	 * @param in - Object that handles the I/O needed for the user
	 * @param ch - The object that handles the users, and the operations regarding
	 *           rides
	 */
	private static void retira(Scanner in, CarpoolHandler ch) {
		String date = in.next().trim();
		in.nextLine();

		try {
			ch.removeFromRide(new DateClass(date));
			System.out.printf(S_BOLEIA_RETIRADA, ch.getCurrUser().getName());
		}

		catch (InvalidDateException e) {
			System.out.println(DATA_INVALIDA);
		}

		catch (NonExistingElementException e) {
			System.out.printf(S_NESTA_DATA_NAO_TEM_REGISTO_DE_BOLEIA, ch.getCurrUser().getName());
		}

	}

	/**
	 * After executing command REMOVE while in session mode, the user must provide a
	 * date in the format d-m-y, being d day, m month, y year, and a valid email.
	 * Then it will print the information of the corresponding ride with the given
	 * email and date.
	 * 
	 * "(user email)\n" "(origin)-(destination)\n" "(date) (hour):(minutes)
	 * (duration)\n" "Lugares vagos: (remaining seats)\n" "Boleias : (user email
	 * n1); (user email n2); ...\n" if there are no users on registered users; "Sem
	 * boleias registadas.\n"
	 * 
	 * @param in - Object that handles the I/O needed for the user
	 * @param ch - The object that handles the users, and the operations regarding
	 *           rides
	 */
	private static void consulta(Scanner in, CarpoolHandler ch) {
		String email = in.next().trim();
		String date = in.next().trim();
		in.nextLine();

		try {
			Ride ride = ch.check(email, new DateClass(date));

			System.out.println(ride.getUser().getEmail());
			System.out.printf(LOCATION_FORMAT, ride.getOrigin(), ride.getDestination());
			System.out.printf(RIDE_INFO_FORMAT, ride.getDate().getFullDate(), ride.getHour(), ride.getMinutes(),
					ride.getDuration());
			System.out.printf(LUGARES_VAGOS_D, ride.getRemainingSeats());
			if (ride.hasUsers()) {
				Iterator<User> it = ride.iterateUsers();
				System.out.print(BOLEIAS);
				while (it.hasNext()) {
					System.out.print(it.next().getEmail());
					if (it.hasNext())
						System.out.print("; ");
				}
			} else {
				System.out.print(SEM_BOLEIAS_REGISTADAS);
			}
			System.out.print("\n");
			System.out.printf(EM_ESPERA_D, ride.getUsersInQueue());
		} catch (NoRideException e) {
			System.out.println(DESLOCACAO_NAO_EXISTE);
		} catch (NonExistingElementException e) {
			System.out.println(UTILIZADOR_INEXISTENTE);
		} catch (InvalidDateException e) {
			System.out.println(DATA_INVALIDA);
		}
	}

	/**
	 * After executing command REMOVE while in session mode, the user must provide a
	 * a valid argument. A valid argument is: a valid email; a valid date in the
	 * format d-m-y, being d day, m month, y year; "minhas", "todas", "boleias".
	 * Provided the respective argument, it will be run the respective command.
	 * 
	 * @param in - Object that handles the I/O needed for the user
	 * @param ch - The object that handles the users, and the operations regarding
	 *           rides
	 */
	private static void lista(Scanner in, CarpoolHandler ch) {
		String arg = in.next().trim();
		in.nextLine();

		String[] split;
		split = arg.split("-");
		if (split.length == 3) {
			listaData(ch, new DateClass(arg));
		} else {
			String cmd = arg.toLowerCase();
			switch (cmd) {
			case MINHAS:
				listaMinhas(ch);
				break;
			case BOLEIAS_ARG:
				listaBoleias(ch);
				break;
			case TODAS:
				listaTodas(ch);
				break;
			default:
				listaEmail(ch, arg);
			}

		}
	}

	/**
	 * Prints the respective information about the rides created of the user with
	 * the corresponding email.
	 * 
	 * * "(user email)\n" "(origin)-(destination)\n" "(date) (hour):(minutes)
	 * (duration)\n"
	 * 
	 * @param email - email to show the information of its rides
	 * @param ch    - The object that handles the users, and the operations
	 *              regarding rides
	 */
	private static void listaEmail(CarpoolHandler ch, String email) {
		try {
			Iterator<Ride> it = ch.iterateRidesThroEmails(email);
			while (it.hasNext()) {
				Ride ride = it.next();
				System.out.println(ride.getUser().getEmail());
				System.out.printf(LOCATION_FORMAT, ride.getOrigin(), ride.getDestination());
				System.out.printf(RIDE_INFO_FORMAT, ride.getDate().getFullDate(), ride.getHour(), ride.getMinutes(),
						ride.getDuration());
				System.out.println();
			}
		} catch (NoElementException e) {
			System.out.println(SEM_DESLOCACOES);
		} catch (NonExistingElementException e) {
			System.out.println(NAO_EXISTE_O_UTILIZADOR_DADO);
		}
	}

	/**
	 * Prints the respective emails of the rides with the given date.
	 * 
	 * * "(user email)\n" "(origin)-(destination)\n" "(date) (hour):(minutes)
	 * (duration)\n"
	 * 
	 * @param arg - date of the rides to be shown
	 * @param ch  - The object that handles the users, and the operations regarding
	 *            rides
	 */
	private static void listaData(CarpoolHandler ch, Date arg) {
		try {
			if (!arg.isDateValid(arg.getFullDate()))
				System.out.println(DATA_INVALIDA);
			else {
				Iterator<Ride> it = ch.iterateRidesThroDays(arg);
				while (it.hasNext()) {
					Ride ride = it.next();
					if (ride.getRemainingSeats() > 0) {
						System.out.println(ride.getUser().getEmail());
						System.out.println();
					}
				}
			}
		} catch (NoElementException e) {
			System.out.println(SEM_DESLOCACOES);
		}
	}

	/**
	 * Prints the respective information about the current user lifts.
	 * 
	 * * "(user email)\n" "(origin)-(destination)\n" "(date) (hour):(minutes)
	 * (duration)\n"
	 * 
	 * @param ch - The object that handles the users, and the operations regarding
	 *           rides
	 */
	private static void listaBoleias(CarpoolHandler ch) {
		try {
			Iterator<Ride> it = ch.iterateUserJoinedRides();
			while (it.hasNext()) {
				Ride ride = it.next();
				System.out.println(ride.getUser().getEmail());
				System.out.printf(LOCATION_FORMAT, ride.getOrigin(), ride.getDestination());
				System.out.printf(RIDE_INFO_FORMAT, ride.getDate().getFullDate(), ride.getHour(), ride.getMinutes(),
						ride.getDuration());
				System.out.println();
			}
		} catch (NoElementException e) {
			System.out.println(SEM_DESLOCACOES);
		}
	}

	/**
	 * Prints the respective information about the current user rides.
	 * 
	 * * "(user email)\n" "(origin)-(destination)\n" "(date) (hour):(minutes)
	 * (duration)\n" "Lugares vagos: (remaining seats)\n" "Boleias : (user email
	 * n1); (user email n2); ...\n" if there are no users on registered users; "Sem
	 * boleias registadas.\n"
	 * 
	 * @param ch - The object that handles the users, and the operations regarding
	 *           rides
	 */
	private static void listaMinhas(CarpoolHandler ch) {
		try {
			Iterator<Ride> it = ch.iterateUserCreatedRides();
			while (it.hasNext()) {
				Ride ride = it.next();
				System.out.println(ride.getUser().getEmail());
				System.out.printf(LOCATION_FORMAT, ride.getOrigin(), ride.getDestination());
				System.out.printf(RIDE_INFO_FORMAT, ride.getDate().getFullDate(), ride.getHour(), ride.getMinutes(),
						ride.getDuration());
				System.out.printf(LUGARES_VAGOS_D, ride.getRemainingSeats());

				if (ride.hasUsers()) {
					Iterator<User> users = ride.iterateUsers();
					System.out.print(BOLEIAS);
					while (users.hasNext()) {
						System.out.print(users.next().getEmail());
						if (users.hasNext())
							System.out.print("; ");
					}
				} else {
					System.out.print(SEM_BOLEIAS_REGISTADAS);
				}
				System.out.print("\n");
				System.out.printf(EM_ESPERA_D, ride.getUsersInQueue());
				System.out.println();
			}
		} catch (NoElementException e) {
			System.out.println(SEM_DESLOCACOES);
		}
	}

	/**
	 * Prints the date and email of the users that have created rides.
	 * 
	 * * "(user email) (date)\n"
	 * 
	 * @param ch - The object that handles the users, and the operations regarding
	 *           rides
	 */
	private static void listaTodas(CarpoolHandler ch) {
/**
		Iterator<Entry<Date, SortedMap<String, Ride>>> dates = ch.iterateAll();
		while (dates.hasNext()) {
			Entry<Date, SortedMap<String, Ride>> entry = dates.next();
			Iterator<Ride> ride = entry.getValue().values();
			Date date = entry.getKey();
			while (ride.hasNext()) {
				System.out.println(date.getFullDate() + " " + ride.next().getUser().getEmail());
				System.out.println();
			}

		}
		*/
		
		Iterator<Ride> rides = ch.iterateAll();
		while(rides.hasNext()) {
			Ride ride = rides.next();
			System.out.println(ride.getDate().getFullDate() + " " + ride.getUser().getEmail());
			System.out.println();
		}

	}

	/**
	 * Prints a message when the user session ends
	 * 
	 * @param ch - The object that handles the users, and the operations regarding
	 *           rides
	 */
	private static void sai(CarpoolHandler ch) {
		System.out.printf(LEAVE_SESSION, ch.leave());

	}

	/**
	 * Command AJUDA Prints menus when the program is in session or not
	 * 
	 * @param arg - date of the rides to be shown
	 * @param ch  - The object that handles the users, and the operations regarding
	 *            rides
	 */
	private static void ajuda(Scanner in, CarpoolHandler ch) {
		System.out.println(COMM_AJUDA);

		if (ch.hasCurrUser()) { // arranjar metodo ou assim para "dividir"
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
	 * Command TERMINA prints goodbye message when the program stops executing
	 */
	private static void terminar() {

		System.out.println(GOODBYE);

	}

	/**
	 * Loads a CarpoolHandler object from the file "savefile" if it is created. If
	 * there is no file, returns an empty CarpoolHandler object.
	 * 
	 * @return CarpoolHandler object
	 */
	private static CarpoolHandler loadFile() {

		ObjectInputStream inStream;
		CarpoolHandler ch = null;
		try {
			inStream = new ObjectInputStream(new FileInputStream(FILENAME));
			ch = (CarpoolHandler) inStream.readObject();
			inStream.close();
			return ch;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			ch = new CarpoolHandlerClass();
			return ch;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ch;
	}

	/**
	 * Writes the CarpoolHandler Object into the file "savefile".
	 * 
	 * @param ch - The object that handles the users, and the operations regarding
	 *           rides
	 */
	private static void writeFile(CarpoolHandler ch) {

		ObjectOutputStream outStream;

		try {
			outStream = new ObjectOutputStream(new FileOutputStream(FILENAME));
			outStream.writeObject(ch);
			outStream.flush();
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}