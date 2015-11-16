package visual;

import java.io.*;
/**
 * Class utilitária para fazer ligação aos ficheiros, serializando e deserializando
 * 
 */

public class Utilidades {
	
	
	/**
	 * tirar informação do ficherio
	 * @param ficheiro-String 
	 * @return Object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deserialize (String ficheiro) throws IOException, ClassNotFoundException{

		ObjectInputStream loadFile  = new ObjectInputStream (new FileInputStream(ficheiro));
		Object objeto = loadFile.readObject();
		loadFile.close();
		return objeto;
	}
	
	
	/**
	 * guardar informação no ficheiro
	 * @param objeto
	 * @param ficheiro
	 * @throws IOException
	 */
	public static void serialize(Object objeto, String ficheiro) throws IOException {
		
		ObjectOutputStream saveFile = new ObjectOutputStream (new FileOutputStream (ficheiro));
		saveFile.writeObject(objeto);
		saveFile.flush();
		saveFile.close();
    }
	
	
	/**
	 * serve para saber o último id inserido
	 * @param ficheiro
	 * @return int
	 */
	public static int ultimoId(String ficheiro){
		FileReader fileR;
		BufferedReader buff;
		int idFinal = 0;
		try{
		    fileR = new FileReader(ficheiro);
		    buff = new BufferedReader(fileR);
		    while(buff.ready()){
		        	idFinal = Integer.parseInt(buff.readLine());
		        	//String id= buff.readLine();          
		            //idFinal = Integer.parseInt(id);            
		    }
		  buff.close();               
		}
		catch(FileNotFoundException ex){
		    	System.out.println(ex.getMessage());       
		}
		catch(IOException er){
		    	System.out.println(er.getMessage());
		}
		           
		return idFinal;
		             
	} // fim ultimoId()
	
	/**
	 * ve o ultimo id inserido e atualiza para mais 1, para a lista ser 
     * carregada e os ids serem gerados a partir desse id atualizado
	 * @param ficheiro - String
	 */
	
    public static void atualizaUltimoId(String ficheiro){
        try{
            //abrir ficheiro para escrever
            int id = ultimoId(ficheiro);
            //escreve no ficheiro, limpa tudo antes.
            BufferedWriter out = new BufferedWriter(new FileWriter(ficheiro));
            
            ++id;                  
            out.write(String.valueOf(id));
            out.close();
        }
        catch(IOException ex){
                System.out.println(ex.getMessage());
        }        
    }
}
