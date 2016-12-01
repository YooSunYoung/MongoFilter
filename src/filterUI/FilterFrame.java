package filterUI;

import java.awt.Font;
import java.awt.List;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.*;

import mongoFilter.properties.operator.Operator;
import mongoFilter.properties.option.Option;

public class FilterFrame extends JFrame{
	
	
	private int interval = 30;
	
	private int x_size = 160;
	private int y_size = 30;
	
	private JPanel main_panel;
	
	private JLabel properties;
	private JLabel lfieldname;
	private JLabel loperator;
	private JLabel loption;
	private JLabel lfiltertype;
	private JLabel lvalue;
	
	private JButton add_button;
	private JButton remove_button;
	
	private JTextField field_name;
	
	private JTextField operator;
	private JTextField filter_type;
	private JTextField value;
	
	private JComboBox<String> operator_combobox;
	private JComboBox<String> option_combobox;
	
	ArrayList<JLabel> properties_label_list;
	ArrayList<JTextField> properties_text_field;
	
	private List filter_list;
	private JTextField option;
	
	public FilterFrame(){
		init();
	}
	
	public void initComboBox(){
		
		operator_combobox = new JComboBox<String>(Operator.operator_name_list);
		option_combobox = new JComboBox<String>();
		operator_combobox.addFocusListener(new FocusListener(){

			public void focusGained(FocusEvent e) {
			}

			public void focusLost(FocusEvent e) {
				
				String op = (String) operator_combobox.getSelectedItem();
				option_combobox.removeAllItems();
				
				if(Option.operator_option_map.containsKey(op)){
					
					for(String opt:Option.operator_option_map.get(op)){
						option_combobox.addItem(opt);
					}
					
				}
				else{
					
				}
			}
		});
	}
	
	public void initLabel(){
		
		properties_label_list = new ArrayList<JLabel>();
		
		// make labels
		properties = new JLabel("Properties");
		properties_label_list.add(properties);
		lfieldname = new JLabel("field name  ");
		properties_label_list.add(lfieldname);
		loperator = new JLabel("operator  ");
		properties_label_list.add(loperator);
		lvalue = new JLabel("value  ");
		properties_label_list.add(lvalue);
		loption = new JLabel("option  ");
		properties_label_list.add(loption);
		lfiltertype = new JLabel("filter type  ");
		properties_label_list.add(lfiltertype);
		
		for (int i = 0 ; i<properties_label_list.size(); i++){
			int x = interval*1+x_size*0;
			int y = interval*(i+1) + y_size*i;

			JLabel j = properties_label_list.get(i);
			j.setBounds(x, y, x_size, y_size);
			j.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
			main_panel.add(j);
			
			if (i!=0){
				JLabel k = new JLabel(":");
				k.setBounds(x+x_size,y,5,30);
				main_panel.add(k);
			}
		}
	}
	
	public void initTextField(){
		
		properties_text_field = new ArrayList<JTextField>();
		
		
		// make text fields
		field_name = new JTextField("field name",10);
		properties_text_field.add(field_name);
		
		operator = new JTextField("operator",10); 
		properties_text_field.add(operator);
		
		value = new JTextField("value1,value2,...",10);
		properties_text_field.add(value);
		
		option = new JTextField("option",10);
		properties_text_field.add(option);
		
		filter_type = new JTextField("filter_type",10);
		properties_text_field.add(filter_type);

		
		filter_type.addFocusListener(new FocusListener(){

			public void focusGained(FocusEvent e) {
				filter_type.setText("");
			}

			public void focusLost(FocusEvent e) {
			}
		});
		main_panel.add(filter_type);
		

	}
	
	public void initButton(){
		// make buttons
		add_button = new JButton("add");
		add_button.setBounds(interval*1+x_size*0,interval*7+y_size*6,100,y_size);
		remove_button = new JButton("remove");
		remove_button.setBounds(interval*2+100,interval*7+y_size*6,100,y_size);
		
		main_panel.add(add_button);
		main_panel.add(remove_button);
	}
	
	public void initPosition(){
		for (int i = 0; i < this.properties_text_field.size() ; i++){

			int x = interval*2+x_size*1;
			int y = interval * (i+2) + y_size *(i+1);
			/////////////////// if u want more general form, comment out combo box area  /////
			//////////////////and re-make getOption() and getOperator method of frame and manager.//
			////////////////////combo box/////////////////////////////////////////////////////////
			if(i==1){
				operator_combobox.setBounds(x, y, x_size, y_size);
				operator_combobox.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
				main_panel.add(operator_combobox);
			}// second component
			else if (i==3){
				option_combobox.setBounds(x, y, x_size, y_size);
				option_combobox.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
				main_panel.add(option_combobox);
				System.out.println("??");
			}// fourth component
			////////////////////combo box/////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////
			else{
				JTextField ptf = properties_text_field.get(i);
				ptf.setBounds(x, y, x_size, y_size);
				ptf.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
				ptf.addFocusListener(new FocusListener(){

					public void focusGained(FocusEvent e) {
						ptf.setText("");
					}

					public void focusLost(FocusEvent e) {
					}
				});
				main_panel.add(ptf);
			}// other component
			

		}// for all component
	}
	
	public void initList(){
		// make a list for filter list.
		JLabel j = new JLabel("Filter List");
		j.setBounds(interval*3+x_size*2, interval, x_size, y_size);
		j.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		main_panel.add(j);
		
		filter_list = new List(1,true);
		filter_list.setBounds(interval*3+x_size*2,interval*2+y_size*1,x_size*2,interval*5+y_size*6);
		main_panel.add(filter_list);
	}
	
	public void init(){
		
		int panel_x_size = x_size * 4 + interval *4;
		int panel_y_size = y_size * 7 + interval *8;
		
		int frame_x_size = panel_x_size + 50;
		int frame_y_size = panel_y_size + 50;
		
		main_panel = new JPanel();
		main_panel.setLayout(null);
		main_panel.setBounds(20, 0, panel_x_size, panel_y_size);
		
		this.setTitle("Mongo Filter Manager");
		this.setLayout(null);
		
		initComboBox(); // if you don't want to use combo box but text field for operator and option, just remove this line.
		initLabel();
		initTextField();
		initButton();
		initList();
		initPosition();
		
		this.add(main_panel);
		this.setSize(frame_x_size, frame_y_size);
		this.setVisible(true);
	}
	
	
	/////////////////////////////////////getters////////////////////////////////
	public JPanel getMainPanel(){
		return this.main_panel;
	}

	public JButton getAddButton() {
		return add_button;
	}

	public JButton getRemoveButton() {
		return remove_button;
	}

	public JTextField getFieldName() {
		return field_name;
	}

	public JComboBox<String> getOperator() {
		return operator_combobox;
	}

	public JComboBox<String> getOption() {
		return option_combobox;
	}

	public JTextField getFilterType() {
		return filter_type;
	}

	public JTextField getValue() {
		return value;
	}

	public ArrayList<JTextField> getPropertiesTextField() {
		return properties_text_field;
	}

	public List getFilterList() {
		return filter_list;
	}
	
	/////////////////////////////////////////////////////////////////////////////

	
	public void setActionListener(FilterActionListener fa){
		this.add_button.addActionListener(fa);
		this.remove_button.addActionListener(fa);
	}

}
