package mongoFilter.properties.option;

import java.util.HashMap;

import mongoFilter.properties.operator.Operator;

public class Option {
	public static String case_insensitive = "i";
	public static String multi_match = "m";
	public static String ignore_white_space = "x";
	public static String allow_dots = "s";
	
	public static String[] option_name_list = {"case_insensitive","multi_match","ignore_white_space","allow_dots","true","false"};
	public static String[] option_list = {"i","m","x","s","true","false"};
	
	private static String[] regex_option_list = {"case_insensitive","multi_match","ignore_white_space","allow_dots"};
	private static String[] exists_option_list = {"true","false"};
	
	public static HashMap<String,String> option_map = new HashMap<String,String>();
	public static HashMap<String,String[]> operator_option_map = new HashMap<String,String[]>();
	
	
	static {
		
		// create a option name and option map.
		
		for (int i = 0 ; i < option_list.length; i++){
			option_map.put(option_name_list[i], option_list[i]);
		}///////////// option name option map created
		
		//  create a operator option name map . user can find option for specific operator
		for (String operator : Operator.operator_name_list){
			// regex operator and exists operator can have option.
			if (operator.contentEquals("regex")){
				operator_option_map.put(operator,regex_option_list);
			}
			
			else if (operator.contentEquals("exists")){
				operator_option_map.put(operator, exists_option_list);
			}
		}/////////////////////// operator map created
	}
	
}
