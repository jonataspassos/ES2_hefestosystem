package resources;

public class HUtil {
	public static java.sql.Date dateToSql (java.util.Date d){
		//return new java.sql.Date(d.getYear(),d.getMonth(),d.getDay());
		if(d != null)
			return new java.sql.Date(d.getTime());
		else
			return null;
	}
	public static java.util.Date dateToUtil (java.sql.Date d){
		if(d != null)
			return new java.util.Date(d.getTime());
		else
			return null;
	}
	
	public static float f(float x,float apc,float dec,float min) {
		return (float)(((1-min)/Math.PI)*(Math.atan((apc-x)/dec)-Math.atan(apc/dec)));
	}
	public static float d1(float x,float apc,float dec,float min) {
		return 1+f(x, apc, dec, min);
	}
	public static float d2(float x,float apc,float dec,float min) {
		return min - f(x, apc, dec, min);
	}
	
	public static float descNdias(float x) {
		return d1(x,50,18,0.2f);
	}
	public static float descHorasDias(float x) {
		return d2(x, 6, 1.3f, 0.2f);
	}
	public static float descVip(float x) {
		return d1(x, 10, 3, 0.6f);
	}
	
	public static float parseFloat(String s) {
		float f=0;
		s = s.replaceAll("[^0-9,\\.]", "");
		//Padrão com , para separar decimal e . para separar milhares
		String pattern1 = "(^[0-9]{1,3}(,[0-9]+)?$)|(^([0-9]{1,3}\\.)?([0-9]{3}\\.)*[0-9]{3}(,[0-9]+)?$)";
		//Padrão com '.' para separar decimal e ',' para separar milhares
		String pattern2 = "(^[0-9]{1,3}(\\.[0-9]+)?$)|(^([0-9]{1,3},)?([0-9]{3},)*[0-9]{3}(\\.[0-9]+)?$)";
		if(s.replaceAll(pattern1, "").equals("")) {
			s = s.replaceAll("\\.", "");
			s = s.replaceAll(",", ".");
		}else if(s.replaceAll(pattern2, "").equals("")) {
			s = s.replaceAll(",", "");
		}
		try {
			f = Float.parseFloat(s);
		} catch (NumberFormatException e) {
			System.err.println("Erro ao converter Flutuante");
		}
		return f;
	}
	
	public static void main(String [] args) {
		//java.util.Date d1 = new java.util.Date(1565492400000l);
		//java.util.Date d2 = new java.util.Date(1566615600000l);
		//System.out.println();
		String s = "11.11";
		System.out.println(parseFloat(s)*2);
		s = "11,111,111.11";
		System.out.println(parseFloat(s)*2);
		s = "11.111.111,11";
		System.out.println(parseFloat(s)*2);
		s = "11,11";
		System.out.println(parseFloat(s)*2);
		s = "1";
		System.out.println(parseFloat(s)*2);
		s = "11";
		System.out.println(parseFloat(s)*2);
		s = "111";
		System.out.println(parseFloat(s)*2);
	}
}