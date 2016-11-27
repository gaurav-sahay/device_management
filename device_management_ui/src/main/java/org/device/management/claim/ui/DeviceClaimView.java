package org.device.management.claim.ui;

import org.device.management.DeviceManagementUI;
import org.device.management.claimed.Devices.ClaimedDevicesTableLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = DeviceClaimView.VIEW_NAME,ui = DeviceManagementUI.class)
@ViewScope
public class DeviceClaimView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	private static final Logger  LOGGER = LoggerFactory.getLogger(DeviceClaimView.class); 

	public static final String VIEW_NAME = "deviceClaim";


//	@Autowired
//	private transient EventBus.SessionEventBus eventbus;
	
	@Autowired
	private ClaimedDevicesTableLayout claimedDevicesTableLayout ;

	private GridLayout mainLayout;


	@Override
	public void enter(final ViewChangeEvent event) {
		LOGGER.info("I am in "+VIEW_NAME);
		buildLayout();
	}


	private void buildLayout() {
		setSizeFull();
		createMainLayout();
		addComponent(mainLayout);
		setExpandRatio(mainLayout, 1.0F);
	}


	private void createMainLayout() {

		mainLayout = new GridLayout();
		layoutWidgets();
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);
		mainLayout.setRowExpandRatio(0, 1.0F);
	}


	private void layoutWidgets() {
		mainLayout.removeAllComponents();
		displayAllWidgets();

	}


	private void displayAllWidgets() {
		mainLayout.setColumns(1);
		//mainLayout.setRows(2);
		mainLayout.addComponent(claimedDevicesTableLayout,0,0);
//        mainLayout.addComponent(targetTableLayout, 1, 0);
//        mainLayout.addComponent(distributionTableLayoutNew, 2, 0);
		//mainLayout.setColumnExpandRatio(1, 0.55f);
		mainLayout.setColumnExpandRatio(0, 1.0F);
		
		//setSizeFull();
	}


}