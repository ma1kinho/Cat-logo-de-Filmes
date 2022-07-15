import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) throws IOException {
		
		Socket clientSocket = new Socket(InetAddress.getLocalHost().getHostAddress(), 20000);
		
		System.out.println("CATALOGO:");

		DataInputStream catalog = new DataInputStream(clientSocket.getInputStream());
		String catalogs = catalog.readUTF();
		System.out.println(catalogs);

		DataOutputStream movie = new DataOutputStream(clientSocket.getOutputStream());

		System.out.print("Digite o nome do filme que voce deseja ver o catalogo:\n=> ");
		Scanner sc = new Scanner(System.in);
		String movieName = sc.nextLine();
		System.out.println();
		movie.writeUTF(movieName);
		
		DataInputStream poster = new DataInputStream(clientSocket.getInputStream());
		String moviePoster = poster.readUTF();

		System.out.println(moviePoster);
		
		poster.close();
		movie.close();
		clientSocket.close();
	}

}
