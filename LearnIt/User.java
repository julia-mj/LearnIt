import java.io.*;
import java.util.*;
public class User implements Serializable{
	private static final long serialVersionUID = 95847483;
	
	String login;
	int id;
	Calendar today;
	NextBaseNr nextBaseNr = new NextBaseNr();
	NBase nfb = new NBase();
	RBase rfb;
  
	void modifyBase(){
		OknoModyfikacji julka = new OknoModyfikacji(nfb);
	}
	void takeBase(boolean newOne){
		if (newOne == true) {
			
			Okno_wpisywania julka = new Okno_wpisywania(nfb);
			nfb.baseId = nextBaseNr.giveNr();
		}
		else {
			OknoWyboru julka = new OknoWyboru(nfb);
		}
	}
	void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException{
		login = (String) stream.readObject();
		id = stream.readInt();
	}
	void writeObject(ObjectOutputStream stream) throws IOException{
		stream.writeObject(login);
		stream.writeObject(id);
	}
	
	User(String ilogin, int iid){
		today = Calendar.getInstance();
        today.set(Calendar.HOUR, 23);
        today.set(Calendar.MINUTE, 59);
        today.set(Calendar.SECOND, 59);

		login = ilogin;
		id = iid;
		nextBaseNr.readNextBaseNr();
	}
}
