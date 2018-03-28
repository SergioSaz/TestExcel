import java.io.*;
import java.net.*;
import java.util.regex.*;

public class Start {
	public static DataModel download(){
		
		String nextLine;
		double[] data = new double[3];
		DataModel dm = new DataModel();
		
		try(BufferedReader buff = 
				new BufferedReader(new InputStreamReader(new URL("https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=BTC,USD,EUR").openConnection().getInputStream(), "UTF8"))){
			while(true){
				nextLine = buff.readLine();
				
				if(nextLine != null){
					Pattern pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
					Matcher matcher=pat.matcher(nextLine);
					int i = 0;
					while (matcher.find() && i < 4) {
					   data[i] = Double.parseDouble(matcher.group());
					   i++;
					}
				}else{
					break;
				}
			}
		}catch(Exception e){
			System.out.println("Îøèáêà");
		}
		dm.setBtc(data[0]);
		dm.setUsd(data[1]);
		dm.setEur(data[2]);
		
		return dm;
		
	}
}	
