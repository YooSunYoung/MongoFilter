package mongoFilter.properties;

public class Properties {
	public static String field_name = "field_name";
	public static String operator = "operator";
	public static String filter_type = "filter_type";
	public static String option = "option";
	public static String value = "value";
	
	public static String[] properties = {field_name,operator,value,filter_type,option};
	public static String[] necessary_properties = {field_name,operator,value};
	public static String[] optional_properties = {filter_type,option};
	
	public static String[] getProperties(){
		return properties;
	}
	
	public static String[] getNecessaryProperties(){
		return necessary_properties;
	}
	
	public static String[] getOptionalProperties(){
		return optional_properties;
	}
}
