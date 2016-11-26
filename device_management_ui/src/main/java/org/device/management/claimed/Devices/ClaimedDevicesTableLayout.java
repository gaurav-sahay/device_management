package org.device.management.claimed.Devices;

import javax.annotation.PostConstruct;

import org.device.management.common.tables.AbstractTableLayout;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

@SpringComponent
@ViewScope
public class ClaimedDevicesTableLayout extends AbstractTableLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6871541788619097106L;

//	@Autowired
//	private transient EventBus.SessionEventBus eventBus;

	@Autowired
	private ClaimedDevicesTableHeader claimedDevicesTableHeader;

	@Autowired
	private ClaimedDevicesTable claimedDevicesTable;
	
	
	@PostConstruct
	void init(){
		super.init(claimedDevicesTableHeader, claimedDevicesTable);
	}
	

}
