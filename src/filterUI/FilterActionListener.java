package filterUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mongoFilter.FilterManager;

public class FilterActionListener implements ActionListener{
	
	FilterManager filter_manager ;
	
	public FilterActionListener(FilterManager fm){
		filter_manager = fm;
	}

	public void actionPerformed(ActionEvent e) {
		String action_command = e.getActionCommand();
		if (action_command.contentEquals("add")){
			filter_manager.add();
		}
		else if (action_command.contentEquals("remove")){
			filter_manager.remove();
		}
		else if (action_command.contentEquals("modify")){
			
		}
	}

}
