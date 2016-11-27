package org.device.management.claimed.Devices;

import org.device.management.claim.ui.utils.I18N;
import org.device.management.claim.ui.utils.SPUIStyleDefinitions;
import org.device.management.common.builder.TextAreaBuilder;
import org.device.management.common.builder.TextFieldBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.vaadin.data.Item;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SpringComponent
@VaadinSessionScope
public class ClaimDeviceAddUpdateWindow extends CustomComponent implements ClickListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(ClaimDeviceAddUpdateWindow.class);

	@Autowired
	private I18N i18n;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
	
	
	private FormLayout formLayout;
	private TextField deviceId;
	private TextField deviceName;
	private TextArea  deviceDescription;
	private Button claimDevice;
	private ClaimedDevicesTable claimedDevicesTable;
	private Window window;

	public void init(){
		setSizeFull();
		claimedDevicesTable = applicationContext.getBean(ClaimedDevicesTable.class);
		createRequiredComponent();
		buildLayout();
		setCompositionRoot(formLayout);
	}


	private void createRequiredComponent() {
		deviceId = createTextField("prompt.device.id",SPUIStyleDefinitions.CLAIM_DEVICE_ID);
		deviceName = createTextField("prompt.device.name", SPUIStyleDefinitions.CLAIM_DEVICE_NAME);
		deviceDescription = new TextAreaBuilder().caption(i18n.get("claim.device.description")).style("text-area-style")
				.prompt(i18n.get("claim.device.description")).immediate(true).id(SPUIStyleDefinitions.CLAIM_DEVICE_DESCRIPTION).buildTextComponent();
		claimDevice = new Button("claim device");
		claimDevice.addClickListener(this);
	}


	private void buildLayout() {
		//setSizeUndefined();
		setWidth(340.0F, Unit.PIXELS);
		setHeight(300.0F, Unit.PIXELS);
		formLayout = new FormLayout();
		formLayout.addComponents(deviceId,deviceName,deviceDescription,claimDevice);
		deviceId.focus();

	}

	private TextField createTextField(final String in18Key, final String id) {
		return new TextFieldBuilder().caption(i18n.get(in18Key)).required(true).prompt(i18n.get(in18Key))
				.immediate(true).id(id).buildTextComponent();
	}


	public void resetComponents() {
		deviceId.clear();
		deviceName.clear();
		deviceDescription.clear();

	}

	public Window getWindow(){
		window = new Window();
		window.setCaption("Claim Device");
		window.setId(SPUIStyleDefinitions.CLAIM_DEVICE_POP_UP_WINDOW);
		window.setClosable(true);
		window.setContent(this);
		window.setResizable(false);
		window.setDraggable(false);
		window.setModal(true);
		window.setWidth(350.0F, Unit.PIXELS);
		window.center();
		window.setVisible(true);
		window.close();
		return window;
	}


	@Override
	public void buttonClick(ClickEvent event) {
		LOG.info("Claiming the device");
		Item item = claimedDevicesTable.addItem(deviceId.getValue());
		item.getItemProperty(SPUIStyleDefinitions.DEVICE_ID_COLUMN).setValue(deviceId.getValue());
		item.getItemProperty(SPUIStyleDefinitions.DEVICE_NAME_COLUMN).setValue(deviceName.getValue());
		item.getItemProperty(SPUIStyleDefinitions.DEVICE_STATUS_COLUMN).setValue("connected");
		item.getItemProperty(SPUIStyleDefinitions.DEVICE_CLAIMED_BY).setValue("Gaurav");
		window.close();
	}
	

}
