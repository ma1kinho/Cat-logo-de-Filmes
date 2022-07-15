import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadSockets extends Thread {
	
	private Socket socket;
	
	public ThreadSockets(Socket s) {
		this.socket = s;
	}
	
	public void run(){
		String[] movies = new String[5];
		movies[0] = "Top Gun: Maverick\nCineMaceio - Horarios\nHoje: 13:10\nAmanha: 15:50\nTerca: 15:50\nVALOR: R$ 15,00\n";
		movies[1] = "The Batman\nCineMaceio - Horarios\nHoje: 13:30\nAmanha: 16:10\nTerca: 17:30\nVALOR: R$ 17,50\n";
		movies[2] = "Sonic 2 - O Filme\nShoppingMcz - Horarios\nHoje: 11:30\nAmanha: 11:30\nTerca: 13:20\nVALOR: R$ 10,00\n";
		movies[3] = "Doutor Estranho no Multiverso da Loucura\nALfilms - Horarios\nHoje: 10:00\nAmanha: 15:20\nTerca: 15:30\nVALOR: R$ 15,00\n";
		movies[4] = "Thor: Amor e Trovao\nALfilms - Horarios\nHoje: 13:30\nAmanha: 13:30\nTerca: 17:40\nVALOR: R$ 10,00\n";

		String catalogs = "-Maverick\n-Batman\n-Sonic\n-Doutor Estranho\n-Thor\n";

		try{
			DataOutputStream catalog = new DataOutputStream(socket.getOutputStream());
			catalog.writeUTF(catalogs);
			System.out.println("Catalogo enviado para o cliente!");

			DataInputStream movie = new DataInputStream(socket.getInputStream());
			String movieName = movie.readUTF();
			System.out.println("Filme solicitado: " + movieName);

			DataOutputStream moviePoster = new DataOutputStream(socket.getOutputStream());
			
			if(movieName.equals("Maverick")){
				moviePoster.writeUTF(movies[0]);
			}else if(movieName.equals("Batman")){
				moviePoster.writeUTF(movies[1]);
			}else if(movieName.equals("Sonic")){
				moviePoster.writeUTF(movies[2]);
			}else if(movieName.equals("Doutor Estranho")){
				moviePoster.writeUTF(movies[3]);
			}else if(movieName.equals("Thor")){
				moviePoster.writeUTF(movies[4]);
			}else{
				moviePoster.writeUTF("O Filme nao esta no catalogo!\n");
			}

			movie.close();
			moviePoster.close();
		} catch (IOException e){
			System.out.println("Erro!!!\n");		
		}
		
	}

}
