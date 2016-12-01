package mongoFilter.properties.operator;

import java.util.ArrayList;
import java.util.HashMap;

public class Operator {
	
	// comparison
	public static String equal = "$eq";
	public static String eq = "$eq";
	public static String greaterthan = "$gt";
	public static String greaterthanorequal = "$gte";
	public static String lowerthan = "$lt";
	public static String lowerthanorequal = "$lte";
	
	public static String notequal = "$ne";
	public static String ne = "$ne";
	public static String in = "$in";
	public static String notin = "$nin";
	
	
	
	// array
	
	public static String all = "$all";
	public static String eleMatch = "$eleMatch";
	public static String size = "$size";
	
	// evaluation
	
	public static String mod = "$mod";
	public static String regex = "$regex";
	public static String text = "$text";
	public static String where = "$where";
	
	public static String option = "$option";
	
	
	public static String and = "$and";
	public static String exist = "$exist"; // can have boolean parameter
	
	public static String nor = "$nor";
	public static String not = "$not";
	public static String or = "$or";
	
	
	public static String[] operator_list = {"$eq","$all","$and","$eleMatch","$exists","$gt","$gte","$in","$lt","$lte","$mod","$ne","$nin","$nor","$not","$or","$regex","$size","$text","$type","$where"};
	public static String[] operator_name_list = {"eq","all","and","eleMatch","exists","gt","gte","in","lt","lte","mod","ne","nin","nor","not","or","regex","size","text","type","where"};
	
	public static HashMap<String,String> operator_map = new HashMap<String,String>();
	static {
		
		// creating operator name and operator map.
		// user can just use operator list or operator name list.
		for (int i = 0; i < operator_list.length; i++){
			String operatorname = operator_name_list[i];
			String operator = operator_list[i];
			operator_map.put(operatorname, operator);
		}/////////////////// operator name operator map created
	}

}
