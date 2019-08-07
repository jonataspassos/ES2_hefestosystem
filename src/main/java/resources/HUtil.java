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
	
	/*public static void main(String [] args) {
		java.util.Date util = new java.util.Date(1567209600000l);
		java.sql.Date sql = new java.sql.Date(1567209600000l);
		
		
		
		System.out.println(1567209600000l);
		System.out.println(util);
		System.out.println(dateToSql(util));
		
		System.out.println(sql);
		System.out.println(dateToUtil(sql));
		
		System.out.println("\tutil\tsql");
		System.out.println("y\t"+util.getYear()+"\t"+sql.getYear());
		System.out.println("m\t"+util.getMonth()+"\t"+sql.getMonth());
		System.out.println("d\t"+util.getDay()+"\t"+sql.getDay());
	}*/
}

