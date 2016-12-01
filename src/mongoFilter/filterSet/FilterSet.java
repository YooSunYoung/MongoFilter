package mongoFilter.filterSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import mongoFilter.singleFilter.SingleFilter;

// have a array list of single-filters and methods for managing the filters.
public class FilterSet {
	private ArrayList<SingleFilter> filter_set;
	
	public FilterSet(){
		init();
	}
	
	public FilterSet(String filter){
		init();
		
		String[] f = filter.split("\n\n");
		for (int i = 0 ; i < f.length ; i++){
			
		}
	}
	
	private void init(){
		filter_set = new ArrayList<SingleFilter>();
	}
	
	// a method that gather all filter as one string
	public String getFilterAsString(){
		int end = this.filter_set.size() ;
		String result = "";
		
		// gather all filter as string
		for (int j = 0; j < end ; j++){
			result += this.filter_set.get(j).getFilter();
			if (j==(end-1)){
				 break;// filter delimiter is two enters "\n\n"
			} else {result += "\n\n";continue;}
		}
		
		return result;
	}
	
	// gathering all filter whose field_name is 'field_name'
	public String getFilterAsString(String field_name){
		int end = this.filter_set.size();
		String result = "";
		
		// gather all filter whose field name is 'field_name' as string
		for (int j = 0; j < end ; j++){
			if (this.filter_set.get(j).getFieldName() == field_name){
				result += this.filter_set.get(j).getFilter();
				if (j==(end-1)){
					 break;// filter delimiter is two enters "\n\n"
				} else {result += "\n\n";continue;}
			}
			else {continue;}
		}
		
		return result;
	}
	
	// get whole filter_set
	public ArrayList<SingleFilter> getFilter(){
		return this.filter_set;
	}
	
	public SingleFilter getFilter(int number){
		return this.filter_set.get(number);
	}
	
	public Integer getFilterIndex(SingleFilter filter){
		Integer index = null;
		for (int i = 0 ; i< this.filter_set.size() ; i++){
			
			SingleFilter f = this.filter_set.get(i);
			 
			if(f.getFilter()==filter.getFilter()){
				index = i;
				break;
			} else {continue;}
			
		}
		
		return index;
	}

	// gather all index of filter whose field name is 'field_name'
	public Set<Integer> getFilterIndex(String field_name){
		Set<Integer> index = new HashSet<Integer>();
		
		// gather all indices of filter whose field name is 'field_name'
		for (int i = 0 ; i< this.filter_set.size() ; i++){
			SingleFilter f = this.filter_set.get(i);
			
			if(f.getFieldName() == field_name){
				index.add(i);
				continue;
			} else {continue;}
		}
		
		return index;
	}
	
	// gather all index of filter whose field name is 'field_name and value is 'value'
	public Set<Integer> getFilterIndex(String field_name, ArrayList<String> value){
		Set<Integer> index = new HashSet<Integer>();
		
		for (int i = 0 ; i< this.filter_set.size() ; i++){
			SingleFilter f = this.filter_set.get(i);
			
			if(f.getFieldName() == field_name && f.getValueList() == value){
				index.add(i);
				continue;
			} else {continue;}
		}
		
		return index;
	}
	
	// there is no more than two filters which has same field name, operator and value
	public Integer getFilterIndex(String field_name, String operator, ArrayList<String> value){
		Integer i = null;
		
		for (int j = 0; j < this.filter_set.size(); j++) {
			SingleFilter s = this.filter_set.get(j);
			
			String f = s.getFieldName();
			String o = s.getOperator();
			ArrayList<String> v = s.getValueList();
			
			if(f==field_name&&o==operator&&v==value){
				i = j;
				break;
			}
			else{continue;}
		}
		
		return i;
	}
	
	// add a filter to filter set with 'properties'.
	public String addFilter(HashMap<String,String> properties){
		SingleFilter f = new SingleFilter(properties);
//		
//		String field_name = properties.get("field_name");
//		String operator = properties.get("operator");
//		String filter_type = properties.get("filter_type");
//		String option = properties.get("option");
//		ArrayList<String> value_list = new ArrayList<String>();
//		String value = properties.get("value");
//		
//		// get value as string and make it as array list again.
//		String value_string = properties.get("value_list");
//		if(value_string!=null){
//			value_string = value_string.replace("[",""); // remove open
//			value_string = value_string.replace("]",""); // remove close
//			value_string = value_string.replace(" ", ""); // remove empty space
//			String[] temp = value_string.split(",");
//			for (int i = 0 ; i<temp.length ; i++){
//				value_list.add(temp[i]);
//			}
//		}
//		
//		f.setFieldName(field_name);
//		f.setOperator(operator);
//		f.setFilterType(filter_type);
//		f.setOption(option);
//		if(value==null){
//			f.setValueList(value_list); 
//		}
//		else if(value!=null){
//			f.setValue(value);
//		}
		
		String repr = "";
		repr = this.add(f);
		System.out.println(repr);
		
		return repr;
	}
	
	// add a SingleFilter object to the filter set.
	public String add(SingleFilter single_filter){
		// check out if there are 3 necessary properties
		String repr = "";
		if(single_filter.hasNecessaryProperties()){
			// check out if there is same filter already.
			if(!this.filter_set.isEmpty()){
				int old_size = this.filter_set.size(); // get size before a filter is added.
														// whenever a filter is added, size of array increases 1...
				for (int k = 0; k<old_size; k++){
					SingleFilter sf = filter_set.get(k);
					if(sf.hasSameNecessaryProperties(single_filter)){
						repr += "There is a same filter already. Can not save two same filters.";
						break;
					}
					else if (k==(old_size-1)){
						this.filter_set.add(single_filter);
						repr += "A filter was added to filter set successfully.";						
					} else {}
				}
			}
			else {
				this.filter_set.add(single_filter);
				repr += "A filter was added to filter set successfully.";
			}
			
		}
		else {
			 repr += "can not add a filter without 3 necessary properties , field name, operator and value";
		}
		
		System.out.println(repr);
		
		return repr;
	}
	
	// remove a single filter which is same as 'single_filter' from filter set.
	public SingleFilter remove(SingleFilter single_filter){
		int i = this.filter_set.size();
		
		SingleFilter temp = null;
		
		for (int j = 0 ; j<i ; j++){
			SingleFilter f = this.filter_set.get(j);
			if (f.isSame(single_filter)){
				temp = this.filter_set.remove(j);
				continue;
			} else {continue;}
		}
		
		String repr = "";
		
		if(temp!=null){
			repr += "The filter was removed successfully.";
		} else{
			repr += "Can not remove the filter. There is no such filter in filter set.";
		}
		
		System.out.println(repr);
		
		return temp;
	}
	
	// remove a single_filter with number from filter set.
	public SingleFilter remove(int number){
		System.out.println("remove " + number + "th filter.");
		return this.filter_set.remove(number);
	}
	
	// remove filters whose field name is 'field_name'
	public String remove(String field_name){
		Set<Integer> indices = this.getFilterIndex(field_name);
		
		for (Integer i : indices){
			this.remove(i);
		}
		
		String repr = "";
		repr += indices.size() + " filter(s) removed whose field_name is " + field_name + ".";
		System.out.println(repr);
		
		return repr;
	}

	// remove filters whose field name is 'field_name' and value is 'value'
	public String remove(String field_name,ArrayList<String> value){
		Set<Integer> indices = this.getFilterIndex(field_name,value);
		
		for (Integer i : indices){
			this.remove(i);
		}
		
		String repr = "";
		repr += indices.size() + " filter(s) removed whose field_name is " + field_name + ".";
		System.out.println(repr);
		
		return repr;
	}
	
	
	public SingleFilter createSingleFilter(){
		SingleFilter newone = new SingleFilter();
		return newone;
	}
	
//	public SingleFilter modify(String property, String value){
//		
//	}
//	
//	public SingleFilter modify(String property, String ArrayList<String>){
//		
//	}
	
}
