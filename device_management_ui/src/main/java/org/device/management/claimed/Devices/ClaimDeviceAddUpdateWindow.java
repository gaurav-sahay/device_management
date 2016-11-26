package org.device.management.claimed.Devices;

import org.device.management.claim.ui.utils.I18N;
import org.device.management.claim.ui.utils.SPUIStyleDefinitions;
import org.device.management.common.builder.TextAreaBuilder;
import org.device.management.common.builder.TextFieldBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

@SpringComponent
@VaadinSessionScope
public class ClaimDeviceAddUpdateWindow extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
    private I18N i18n;
	private FormLayout formLayout;
	private TextField deviceId;
	private TextField deviceName;
	private TextArea  deviceDescription;
	private Window window;
	
	public void init(){
		createRequiredComponent();
		buildLayout();
		setCompositionRoot(formLayout);
	}


	private void createRequiredComponent() {
		deviceId = createTextField("prompt.device.id",SPUIStyleDefinitions.CLAIM_DEVICE_ID);
		deviceName = createTextField("prompt.device.name", SPUIStyleDefinitions.CLAIM_DEVICE_NAME);
		deviceDescription = new TextAreaBuilder().caption(i18n.get("claim.device.description")).style("text-area-style")
				.prompt(i18n.get("claim.device.description")).immediate(true).id(SPUIStyleDefinitions.CLAIM_DEVICE_DESCRIPTION).buildTextComponent();
		window = new Window();
	}
	
	
	private void buildLayout() {
		setSizeUndefined();
		formLayout = new FormLayout();
		formLayout.addComponents(deviceId,deviceName,deviceDescription);
		deviceId.focus();
	}
	
	private TextField createTextField(final String in18Key, final String id) {
        return new TextFieldBuilder().caption(i18n.get(in18Key)).required(true).prompt(i18n.get(in18Key))
                .immediate(true).id(id).buildTextComponent();
    }


	public void resetComponents() {
		// TODO Auto-generated method stub
		
	}
	
	public Window getWindow(){
		window.setCaption("Claim Device");
		window.setId(SPUIStyleDefinitions.CLAIM_DEVICE_POP_UP_WINDOW);
		window.setDraggable(false);
        window.setClosable(true);
		return window;
	}

}
