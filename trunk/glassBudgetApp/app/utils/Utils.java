package utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utils {
	
	public static String invert(String s) {
		String dia = s.substring(0, 2);
		String mes = s.substring(3, 5);
		String ano = s.substring(6, 10);
		return ano + "-" + mes + "-" + dia;
	}
	
	public static String formatarMoeda(double valor) {
	    DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));  
	    formatoDois.setMinimumFractionDigits(2);   
	    formatoDois.setParseBigDecimal (true);  
	      
	    return "R$ " + formatoDois.format(valor).toString();  
	}
	
	public static String getHour() {
		Calendar c = Calendar.getInstance();
		String nh = "";
		String h = c.getTime().getHours() + "";
		String m = c.getTime().getMinutes() + "";
		String s = c.getTime().getSeconds() + "";
		
		if (h.length() == 1) {
			h = "0"+h;
		}
		
		if (m.length() == 1) {
			m = "0"+m;
		}
		
		if (s.length() == 1) {
			s = "0"+s;
		}
		return h + ":" + m + ":" + s;
	}
	
	public static String getDate() {
		Date data = new Date(System.currentTimeMillis());    
	    SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");   
	    return formatarDate.format(data).toString();
	}
	
	public static Date convertStringtoDate(String d) {
		int year = Integer.parseInt(d.substring(0, 4));
		int moth = Integer.parseInt(d.substring(5, 7));
		int day = Integer.parseInt(d.substring(d.length()-2, d.length()));
		System.out.println(year + " " + moth + " " + day);
		Date data = new Date(year-1900, moth-1, day);
		return data;
	}
	
	public static Timestamp convertStringToTimestamp(String data, String hs) {
		int year = Integer.parseInt(data.substring(0, 4));
		int moth = Integer.parseInt(data.substring(5, 7));
		int day = Integer.parseInt(data.substring(data.length()-2, data.length()));
		int h = Integer.parseInt(hs.substring(0, 2));
		int m = Integer.parseInt(hs.substring(3, 5));
		int s = Integer.parseInt(hs.substring(hs.length()-2, hs.length()));
		Timestamp timestp = new Timestamp(year-1900, moth-1, day, h, m, s, 0);
		return timestp;
	}

}
