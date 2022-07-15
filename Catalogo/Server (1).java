import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

public class Server {
	
	public static void main(String[] args) throws IOException {
	
		ServerSocket serverSocket = new ServerSocket(20000);
		System.out.println("Servidor aberto. Seja bem vindo!");
		
		while(true) {
			Socket socket = serverSocket.accept();
			System.out.println("Cliente conectado!");
			
			ThreadSockets thread = new ThreadSockets(socket);
			thread.start();
		}
	}

}
