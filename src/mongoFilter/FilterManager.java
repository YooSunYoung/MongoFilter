package mongoFilter;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import filterUI.FilterActionListener;
import filterUI.FilterFrame;
import mongoFilter.filterSet.FilterSet;
import mongoFilter.properties.operator.Operator;
import mongoFilter.properties.option.Option;
import mongoFilter.singleFilter.SingleFilter;

public class FilterManager {
	private FilterFrame filter_frame;
	private FilterActionListener filter_action_listener;
	private FilterSet filter_set;
	
	public FilterManager(){
		init();
	}
	
	public FilterFrame getFilterFrame(){
		return this.filter_frame;
	}
	
	public void init(){
		filter_frame = new FilterFrame();
		filter_action_listener = new FilterActionListener(this);
		filter_set = new FilterSet();
		filter_frame.setActionListener(filter_action_listener);
	}
	
	public void renew(){
		filter_frame.getFilterList().removeAll();
		for (SingleFilter sf : filter_set.getFilter()){
			filter_frame.getFilterList().add(sf.getFilterProperties());
		}
		
	}
	
	public String add(){
		String result = "";
		boolean b = true;
		
		ArrayList<JTextField> tfl = this.filter_frame.getPropertiesTextField();
		for (JTextField tf : tfl){
			if(tf.getText().contains(" ")){
				result += "Error : Sorry. Properties can not contain white space...";
				b = false;
				break;
			}
		}
		
		String field_name = getFieldName();
		String operator = getOperator();
		String value = getValue();
		if(field_name.contentEquals("")||operator.contentEquals("")||value.contentEquals("")){
			result += "Error : Necessary property is missing. \n";
			result += "Field Name , Operator and Value are necessary.";
			b = false;
		}
		ArrayList<String> valuelist = getValueAsArray();
		
		String option = getOption();
		String filter_type = getFilterType();
		
		SingleFilter singlefilter = new SingleFilter();
		
		if (b == true){
			singlefilter.setFieldName(field_name);
			singlefilter.setOperator(operator);
			
			if (isValueSingle()){
			singlefilter.setValue(value);
			}
			else {
			singlefilter.setValueList(valuelist);
			}
			
			if(!option.contentEquals("")){
				singlefilter.setOption(option);
			}
			
			if(!filter_type.contentEquals("")){
				singlefilter.setFilterType(filter_type);
			}
			
			result += this.filter_set.add(singlefilter);
		}
		
		System.out.println(this.getFilterSet().getFilterAsString());
		JOptionPane.showMessageDialog(filter_frame, result);
		renew();
		return result;
	}
	
	public String remove (){
		String result = "a filter : ";
		int i = filter_frame.getFilterList().getSelectedIndex();
		result += filter_set.remove(i).getFilterProperties();
		result += "\n was removed from the filter list";
		JOptionPane.showMessageDialog(filter_frame, result);
		renew();
		return result;
	}
	
	public String getFieldName(){
		return filter_frame.getFieldName().getText();
	}
	public String getValue(){
		String value = filter_frame.getValue().getText();
		String[] valuelist = value.split(",");
		ArrayList<String> valuearraylist = new ArrayList<String>();
		if (isValueSingle()){
			return value;
		}
		else{
			for (int i = 0 ; i < valuelist.length ; i++){
				valuearraylist.add(valuelist[i]);
			}
			return valuearraylist.toString();
		}
	}
	public ArrayList<String> getValueAsArray(){
		String value = filter_frame.getValue().getText();
		String[] valuelist = value.split(",");
		ArrayList<String> valuearraylist = new ArrayList<String>();
		
		for (int i = 0 ; i < valuelist.length ; i++){
			valuearraylist.add(valuelist[i]);
		}
		return valuearraylist;
	}
	public boolean isValueSingle(){
		String value = filter_frame.getValue().getText();
		String[] valuelist = value.split(",");
		if (valuelist.length==1){
			return true;
		}
		else {return false;}
	}
	public String getOption(){
		String option = "";
		if(filter_frame.getOption().getItemCount()!=0){
			String option_name = filter_frame.getOption().getSelectedItem().toString() ;
			option += Option.option_map.get(option_name) ;
		}
		return option;
	}
	public String getOperator(){
		String operator_name = filter_frame.getOperator().getSelectedItem().toString();
		String operator = Operator.operator_map.get(operator_name);
		return operator;
	}
	public String getFilterType(){
		return filter_frame.getFilterType().getText();
	}
	
	public FilterSet getFilterSet(){
		return this.filter_set;
	}

	
	
	public static void main(String[] args){
		FilterManager fm = new FilterManager();
	}
}
