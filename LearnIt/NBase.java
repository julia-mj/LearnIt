import java.util.*;
import java.io.*;
public class NBase implements Serializable{
	
	int nextid = 1, baseId;
	
	public LinkedList<Fiszka> list= new LinkedList<Fiszka>();
	public HashMap<String, Vector<Fiszka> > topic_sort = new HashMap<String, Vector<Fiszka> >();
	public Vector<FieldData> v = new Vector<FieldData>();
	
	public void setBase(LinkedList<Fiszka> ilist) {
		list = ilist;
		topic_sort.clear();
		for(int i = 0; i < list.size(); i++)
		{ 
			Fiszka f = list.get(i);
			if ( f.id == -1 ) f.id = nextid ++;
			if ( f.number_of_fields == -1 ) continue;
			Vector<Fiszka> vf;
			if(topic_sort.containsKey(f.kategoria().get()) == true) 
				vf = topic_sort.get(f.kategoria().get());
			else vf = new Vector<Fiszka>();
			vf.add(f);
			topic_sort.put(f.kategoria().get(), vf);
		}
	}	
	public Vector<FieldData> getData(){
            return v;
	}	
	public LinkedList<Fiszka> getList(){
		return list;
	}
	public void setAll(NBase x){
		baseId = x.baseId;
		nextid = x.nextid;
		topic_sort = x.topic_sort;
		list = x.list;
		v = x.v;
	}
	public static void translate()
    {
        File plik= new File("lista.txt");
        //plik.createNewFile();
        Scanner s;
        String x = "";
        String[] tablica =x.split("\t");
        LinkedList<Fiszka>  fiszki = new LinkedList<Fiszka>();
        try {
            s = new Scanner(plik);
            for(int i = 0; i< 80; i++)
            {
                x = s.nextLine();
                System.out.println(x);
                tablica = x.split("\t");
                Fiszka f = new NewFiszka(3);
                f.setith(new Text("angielski"), 0);
                f.setith(new Text(tablica[0]), 2);
                /*int j = 1;
                while(j<tablica.length && tablica[j]=="\t") j++;*/
                f.setith(new Text(tablica[tablica.length -1]), 1);
                fiszki.add(f);
            }
            
        }
        catch(Exception e){
            System.err.println("Caught IOException: " + e.getMessage());
        }
        
        
        NBase n = new NBase();
        Vector<FieldData> v = new Vector<FieldData>();
        v.add(new FieldData("TEKST", "kategoria"));
        v.add(new FieldData("TEKST", "hasło"));
        v.add(new FieldData("TEKST", "znaczenie"));
        n.v = v;
        n.setBase(fiszki);
        plik= new File("lista_domyslna.fiszka");
        //plik.createNewFile();
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(plik);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(n); //zapisuje base
            fos.close();
            oos.close();
        }
        catch(Exception e){
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
	public static void main(String[] args){
		translate();
	}
	NBase(){}
}
