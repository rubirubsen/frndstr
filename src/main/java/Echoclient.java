import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.text.AbstractDocument.BranchElement;


public class Echoclient {

	public static void main(String[] args) {
		
		//Socket verfügbar machen:
		Socket server = null;
		
		try {
			//Erzeuge ein Socket objekt und versuch eine Verbindung herzustellen.
			server = new Socket("127.0.0.1", 5555);
			
			// Der Eingabestrom wird im Objekt reader gespeichert.
			BufferedReader reader = new BufferedReader(new InputStreamReader(server.getInputStream()));
			
			//der Printwriter out ist das Ausgabeobjekt.
			PrintWriter out = new PrintWriter(server.getOutputStream());
			
			
			//Jetzt soll in einer Schleife das was der Nutzer auf der Tastatur eingibt an den Server gesendet werden.
			
			//Tastaturabfragen werden hier abgefragt.
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			
			String zwischenspeicher;
			while(true){
				System.out.println("Warte auf eingabe: ");
				zwischenspeicher = input.readLine();
				//Sende an den Server		
				out.println(zwischenspeicher);
				out.flush();
				//Auf antwort des Servers warten und diese Ausgabe schreiben:
				System.out.println(reader.readLine());
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}