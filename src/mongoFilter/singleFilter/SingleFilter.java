package mongoFilter.singleFilter;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.*;

public class SingleFilter {
	// fields/////////////////////////////
	
	// three required properties
	private String field_name;
	private String operator;
	private ArrayList<String> value_list;  // value list and value can not exist together
	private String value;
	
	// two optional properties
	private String filter_type;
	private String option;
	
	// a filter as string object
	private String filter;
	
	private JsonParser jp;
	
	/////////////////////////////////////////

	
	//////////////////methods///////////////////////////
	
	
	///////////////////////////// generators ///////////////////////////////////////////
	public SingleFilter(){
		init();
	}
	
	public SingleFilter(String filter){
		init();
		JsonObject properties = (JsonObject) jp.parse(filter);
		JsonElement field_name = properties.get("field_name");
		JsonElement operator = properties.get("operator");
		JsonElement filter_type = properties.get("filter_type");
		JsonElement option = properties.get("option");
		ArrayList<String> value_list = new ArrayList<String>();
		JsonElement value = properties.get("value");
		
		
		JsonElement value_string = properties.get("value_list");
		if(value_string!=null){
			String v = value_string.toString().replace("\"", "");
			v = v.replace("[",""); // remove open
			v = v.replace("]",""); // remove close
			v = v.replace(" ", ""); // remove empty space
			String[] temp = v.split(",");
			for (int i = 0 ; i<temp.length ; i++){
				value_list.add(temp[i]);
			}
		}
		
		if(field_name!=null){
			this.setFieldName(field_name.toString().replace("\"", ""));
		}
		if(operator!=null){
			this.setOperator(operator.toString().replace("\"", ""));
		}
		if(filter_type!=null){
			this.setFilterType(filter_type.toString().replace("\"", ""));
		}
		if(option!=null){
			this.setOption(option.toString().replace("\"", ""));
		}
		if(value_string!=null){
			this.setValueList(value_list); 
		}
		else if(value!=null){
			this.setValue(value.toString());
		}
		
		
	}
	
	public SingleFilter(HashMap<String,String> properties){
		String field_name = properties.get("field_name");
		String operator = properties.get("operator");
		String filter_type = properties.get("filter_type");
		String option = properties.get("option");
		ArrayList<String> value_list = new ArrayList<String>();
		String value = properties.get("value");
		
		// get value as string and make it as array list again.
		String value_string = properties.get("value_list");
		if(value_string!=null){
			value_string = value_string.replace("[",""); // remove open
			value_string = value_string.replace("]",""); // remove close
			value_string = value_string.replace(" ", ""); // remove empty space
			String[] temp = value_string.split(",");
			for (int i = 0; i < 4 ; i++){
					char wrapper = '\'';
					if((!temp[i].startsWith("'"))&&(!temp[i].startsWith("\""))){
						temp[i] = wrapper + temp[i];
					}
					else {
						wrapper = temp[i].charAt(0);
					}
					
					if((!temp[i].endsWith("'"))&&(!temp[i].endsWith("\""))){
						temp[i] = temp[i] + wrapper;
					}
					else if(temp[i].charAt(temp[i].length()-1)!=wrapper){
						temp[i] = temp[i].substring(0, temp[i].length() -2 ) + wrapper;
					}
					
			
				value_list.add(temp[i]);
			}
		}
		
		this.setFieldName(field_name);
		this.setOperator(operator);
		this.setFilterType(filter_type);
		this.setOption(option);
		if(value==null){
			this.setValueList(value_list); 
		}
		else if(value!=null){
			this.setValue(value);
		}
	}
	
	public SingleFilter(String field_name, String operator, ArrayList<String> value){
		init();
		
		this.field_name = field_name;
		this.operator = operator;
		this.setValueList(value);
		this.renewFilter();
	}
	
	public SingleFilter(String field_name, String operator, String value){
		init();
		
		this.field_name = field_name;
		this.operator = operator;
		this.setValue(value);
		this.renewFilter();
	}
	
	/// recommended filter generator
	public SingleFilter(String field_name, String operator, String filter_type, ArrayList<String> value){
		init();
		
		this.field_name = field_name;
		this.operator = operator;
		this.value_list = value;
		this.filter_type = filter_type;
		this.renewFilter();
	}
	
	public SingleFilter(String field_name, String operator, String filter_type, String option, ArrayList<String> value){
		init();
		
		this.field_name = field_name;
		this.operator = operator;
		this.value_list = value;
		this.option = option;
		this.filter_type = filter_type;
		this.renewFilter();
	}
	
	public SingleFilter(String field_name, String operator, String filter_type, String option, String value){
		init();
		
		this.field_name = field_name;
		this.operator = operator;
		this.value = value;
		this.option = option;
		this.filter_type = filter_type;
	}
	
	public void init(){
		jp = new JsonParser();
		value_list = new ArrayList<String>();
		this.value = null;
		this.field_name = null;
		this.filter_type = null;
		this.operator = null;
		this.option = null;
		this.filter = null;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	
	
	//////////////////////////////// set methods //////////////////////////////////////////
	public String setFieldName(String field_name){
		String temp = this.field_name;
		this.field_name = field_name;
		this.renewFilter();
		return temp;
	}
	
	public String setOperator(String operator){
		String temp = this.operator;
		this.operator = operator;
		this.renewFilter();
		return temp;
	}
	
	// check out if the filter type is either string or number.
	public String setFilterType(String filter_type){
		String temp = this.filter_type;
		if (filter_type == "string" || filter_type == "String" || filter_type == "number" || filter_type == "Number")
		{ 
			this.filter_type = filter_type;
			this.renewFilter();
			return temp; 
		}
		else{
			String error_message = "wrong filter_type";
			System.out.println(error_message);
			return error_message;
		}
	}
	
	public String setOption (String option){
		String temp = this.option;
		this.option = option;
		this.renewFilter();
		return temp;
	}
	
	public ArrayList<String> setValueList(ArrayList<String> value_list ){
		ArrayList<String> temp0 = this.value_list;
		ArrayList<String> temp = value_list;
		
		for (int i = 0; i < 4 ; i++){
			char wrapper = '\'';
			if((!temp.get(i).startsWith("'"))&&(!temp.get(i).startsWith("\""))){
				temp.add(i,wrapper + temp.get(i));
			}
			else {
				wrapper = temp.get(i).charAt(0);
			}
			
			if((!temp.get(i).endsWith("'"))&&(!temp.get(i).endsWith("\""))){
				temp.set(i,temp.get(i) + wrapper);
			}
			else if(temp.get(i).charAt(temp.get(i).length()-1)!=wrapper){
				temp.set(i, temp.get(i).substring(0, temp.get(i).length() -2 ) + wrapper);
			}
		}
		
		this.value_list = temp;
		this.value = null;
		this.renewFilter();
		return temp0;
	}
	public String setValue(String value ){
		String temp = this.value;
		char wrapper = '\'';
		if ((!value.startsWith("'"))&&(!value.startsWith("\""))){
			value = wrapper+value;
		}
		else if((value.startsWith("'"))||(value.startsWith("\""))){
			wrapper = value.charAt(0);
		}
		
		
		if((!value.endsWith("'"))&&(!value.endsWith("\""))){
			value = value + wrapper;
		}
		else if (value.charAt(0)!=value.charAt(value.length()-1)){
			value = value.substring(0,value.length()-2) + wrapper;
		}
		
		this.value = value;
		this.value_list.clear();
		this.renewFilter();
		return temp;
	}
	////////////////////////////////////////////////////////////////////////////////
	
	
	
	//////////////////////////////// get methods ///////////////////////////////////
	public String getFieldName(){
		return this.field_name;
	}
	public String getOperator (){
		return this.operator;
	}
	public String getFilterType (){
		return this.filter_type;
	}
	public String getOption (){
		return this.option;
	}
	public ArrayList<String> getValueList (){
		return this.value_list;
	}
	public String getValue(){
		return this.value;
	}
	
	public String getFilter(){
		this.renewFilter();
		return this.filter;
	}
	
	public String getFilterProperties(){
		String properties = "";
		if(this.hasNecessaryProperties()){
			properties += "{";
			properties += "field_name : ";
			properties += this.field_name;
			properties += " , ";
			properties += " operator : ";
			properties += this.operator;
			properties += " , ";
			if (this.value==null){
				properties += " value_list : ";
				properties += this.value_list.toString();
			}
			else if(this.value_list.isEmpty()){
				properties += " value : ";
				properties += this.value;
			}
			
			if(this.option!=null){
				properties +=" , option : ";
				properties += this.option;
			}
			else {}
			
			if(this.filter_type!=null){
				properties +=", filter_type : ";
				properties +=this.filter_type;
			}
			else{}
			
			properties += " }";
		}
		else {
			String repr = "Can not make properties without necessary properties, field name, operator and value";
			System.out.println(repr);
		}
		
		
		return properties;
	}
	
	///////////////////////////////////////////////////////////////////////
	
	//////////////// a method that makes a filter with the properties. /////
	//////////////// need to be called whenever fields are set. ////////////
	public String renewFilter(){
		
		// check out if any required property is missing.
		if (this.operator==null||this.field_name==null||(this.value_list.isEmpty()&&this.value==null)){
			String repr = "";
			if (this.operator==null){
				repr += "operator ";
			}
			else if(this.field_name==null){
				repr += "field name ";
			}
			else if(this.value_list.isEmpty()&&this.value==null){
				repr += "value ";
			}
			
			//repr += "missing. Please set missing properties.";
			//System.out.println(repr);
			return repr;
		}
		// check out if optional property is not set.
		else if (this.option == null){
			String v = "";
			if (this.value_list.isEmpty()){
				v = this.value;
			} else if (this.value==null){
				v = this.value_list.toString();
			}
			
			String filter = "";
			
			filter += "{ ";
			
			filter += field_name;
			
			filter += " : { ";
			
			filter += operator;
			
			filter += " : ";
			
			filter += v;
			
			filter += " } ";
			
			filter += " } ";
			
			
			this.filter = filter;
		}
		// in the case all properties are set
		else {
			String v = "";
			if (this.value_list.isEmpty()){
				v = this.value;
			} else if (this.value==null){
				v = this.value_list.toString();
			}
			
			String filter = "";
			
			filter += "{ ";
			
			filter += field_name;
			
			filter += " : { ";
			
			filter += operator;
			
			filter += " : ";
			
			filter += v;
			
			filter += " , ";
			
			filter += "$option : ";
			
			filter += option; // option is not null, put option of the operator
			
			filter += " } ";
			
			filter += " } ";
			
			this.filter = filter;
		}
		
		return filter;
	}
	////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////// checking methods /////////////////////////////
	public boolean isSame(SingleFilter f){
		boolean is_same = false;
		
		if (this.getFieldName()==f.getFieldName()){
			if (this.getOperator()==f.getOperator()){
				if ( this.getFilterType() == f.getFilterType()){
					if(this.getOption() == f.getOption()){
						int length = this.getValueList().size();
						if (this.value==null&&length==f.getValueList().size()){
							for (int i = 0 ; i < length; i++){
								if (this.getValueList().get(i)==f.getValueList().get(i)){
									is_same = true;
									continue;
								} else {is_same = false; break;}
							} //check out for all element of value
						}// check out the size of value list
						else if(this.value!=null){
							if (this.getValue()==f.getValue()){
								is_same = true;
							} else {is_same = false;}
						}// check out if value is same
					}// check out if option is same
				}// check out if filter type is same
			}// check out if operator is same
		}// check out if field name is same
		
		return is_same;
	}
	
	public boolean hasSameNecessaryProperties(SingleFilter f){
		boolean is_same = false;
		
		if (this.getFieldName().contentEquals(f.getFieldName())){
			if (this.getOperator().contentEquals(f.getOperator())){
						int length = this.getValueList().size();
						if (this.value==null&&length==f.getValueList().size()){
							for (int i = 0 ; i < length; i++){
								if (this.getValueList().get(i).contentEquals(f.getValueList().get(i))){
									is_same = true;
									continue;
								} else {is_same = false; break;}
							} //check out for all element of value
						}// check out the size of value list
						else if(this.value!=null){
							if (this.getValue().contentEquals(f.getValue())){
								is_same = true;
							} else {is_same = false;}
						}// check out if value is same
			}// check out if operator is same
		}// check out if field name is same
		
		return is_same;
	}
	
	public boolean hasNecessaryProperties(){
		boolean b = false;
		if (this.field_name!=null&&this.operator!=null&&(this.value!=null||!this.value_list.isEmpty())){
			b = true;
		}
		return b;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	
}
 