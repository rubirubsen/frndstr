import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class einclientEchoServer {

	public static void main(String[] args) {
		einclientEchoServer t = new einclientEchoServer();
		t.starteServer();
		
		

	}
	
	public void  starteServer(){
		try {
			//Starte den Server
			ServerSocket server = new ServerSocket(5555);
			
			//Gibt den Hoast und die IP auf der console aus.
			InetAddress adresse = InetAddress.getLocalHost();
			System.out.println("Server gestartet \n" +
								"Server auf IP:  " +  adresse.getHostAddress() + adresse.getHostName());
			
			serverRun(server);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//Die Methode des Servers
	public void serverRun(ServerSocket server) throws IOException{
		/**
		 * Der Server läuft und bekommt zum Start den eben erstellten Socket übergeben.
		 * 
		 */
		while(true){
			//Die accept Methode wird aufgerufen.
			//Der  Server wartet auf eingehende Verbindungen.
			//Wenn eine verbindung reinkommt wird dann ein neuer Socket erzeugt.
			Socket client = server.accept();
			
			/**
			 * Jetzt können wir auch direckt die IP vom Client.
			 */
			String clientAdresse;
			clientAdresse = client.getInetAddress().getHostAddress() + client.getInetAddress().getHostName();
			System.out.println(clientAdresse);
			
			try {
				BufferedReader rein = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
				PrintWriter senden = new PrintWriter(client.getOutputStream());
				/**
				 * Diese Endlosschleife ist dazu da jede anfrage der Clients anzunemen und zurück zu senden.
				 * Eine Empfangene Nachricht wird in den Zwischenspeicher gelegt. 
				 * Anschliessend wird dieser String mit dem Wort "Antwort versehen" und zurück gesendet.
				 */
				String zwischenspeicher;
				while((zwischenspeicher = rein.readLine()) != null){
					zwischenspeicher = "Antwort: " + zwischenspeicher;
					//Printwriter mit Inhalt füllen:
					senden.println(zwischenspeicher);
					//Printwriter senden:
					senden.flush();
					//Zur sicherheit nochmal auf der Console ausgeben:
					System.out.println("Gesendt wurde: " + zwischenspeicher);
				}
				
			} catch (Exception e) {
				
			}
		}
				
	}

}