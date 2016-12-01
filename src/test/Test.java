package test;

import java.util.ArrayList;

import mongoFilter.filterSet.FilterSet;
import mongoFilter.properties.operator.Operator;
import mongoFilter.singleFilter.SingleFilter;

public class Test {
	public static void main(String[] args){
		SingleFilter sf1 = new SingleFilter();
		SingleFilter sf2 = new SingleFilter();
		SingleFilter sf3 = new SingleFilter();
		sf1.setFieldName("name");
		sf1.setOperator(Operator.equal);
		sf2.setFieldName("name");
		sf2.setOperator(Operator.in);
		sf3.setFieldName("name");
		sf3.setOperator(Operator.regex);
		
		
		sf1.setValue("newsun");
		System.out.println(sf1.getFilter());
		ArrayList<String> name = new ArrayList<String>();
		name.add("newsun");
		sf2.setValueList(name);
		System.out.println(sf2.getFilter());
		sf3.setValue("/^sun/");
		sf3.setOption("si");
		
		FilterSet fs = new FilterSet();
		fs.add(sf1);
		fs.add(sf2);
		fs.add(sf2); // try saving same single filter.
		fs.add(sf3);
		
		System.out.println(sf1.getFilterProperties());
		
		SingleFilter sf4 = new SingleFilter(sf1.getFilterProperties());
		fs.add(sf4);
		
		System.out.println(fs.getFilterAsString());
		
		
	}
}
