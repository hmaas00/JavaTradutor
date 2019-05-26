/**
 * 
 */

/**
 * @author harlan
 *
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Tradutor {

	/*String fName: arquivo fonte para montar o dicionario na forma:
	 * key<separador>value
	 * 
	 * String separador: separador usado no dicionario
	*/
	
	public static HashMap<String,String> montarDicio(String fName, String separador){
		File file = new File(fName);
		BufferedReader br;
		HashMap<String,String> dic = new HashMap<String,String>();
		try {
			br = new BufferedReader(new FileReader(file));
			String st; 
			while ((st = br.readLine()) != null){ 
				String s1 = st.split(separador)[0];
				String s2 = st.split(separador)[1].replace("/n", "");
				dic.put(s1,s2);
			}
			return dic;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String traduzir(String texto, HashMap<String,String> dicionario){
		//cria espacos no inicio e final do texto
		String res = " " + texto + " ";
		
		res = gerarEspacos(res);
		for (String i : dicionario.keySet()){
			//traduz e mantem espacos
			res= res.replaceAll("\\s+"+i+"\\s+", " " + dicionario.get(i) + " ");
		}
		res = juntarEspacos(res);
		return res.trim();
	}
	//criar função para transformar: [(a++b)--c&&d] em [( a ++ b ) -- c && d]
	public static String gerarEspacos(String s){
		s = s.replaceAll("\\(" , " \\( ");
		s = s.replaceAll("\\)" , " \\) ");
		s = s.replaceAll("\\+{2}" , " \\+\\+ ");
		s = s.replaceAll("--" , " -- ");
		s = s.replaceAll("&&" , " && ");
		return s;
	}
  
	//criar função para transformar: [   (  a  ++   b     )    --    c    &&      d] em [ ( a ++ b ) -- c && d ]
	public static String juntarEspacos(String s){
		s = s.replaceAll("\\s*\\(\\s*" , " \\( ");
		s = s.replaceAll("\\s*\\)\\s*" , " \\) ");
		s = s.replaceAll("\\s*\\+{2}\\s*" , " \\+\\+ ");
		s = s.replaceAll("\\s*\\--\\s*" , " \\-- ");
		s = s.replaceAll("\\s*&&\\s*" , " && ");
		  
		return s;
	}
  
	/*public static void main(String[] args) {
		
		HashMap<String,String> dic = Tradutor.montarDicio("test.txt",":");
	  	System.out.println(Tradutor.traduzir("trad linha2 linha1", dic));
	  	String s = gerarEspacos("(ab++cd)++(ab++cd)--(ab++cd))&&ab++cd");
	  	System.out.println(s);
	  	s = juntarEspacos(s);
	  	System.out.println(s);
		
  	}*/
}
