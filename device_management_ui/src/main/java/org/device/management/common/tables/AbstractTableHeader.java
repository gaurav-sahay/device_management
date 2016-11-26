package org.device.management.common.tables;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.device.management.claim.ui.utils.I18N;
import org.device.management.claim.ui.utils.SPUIStyleDefinitions;
import org.device.management.common.builder.LabelBuilder;
import org.device.management.common.builder.TextFieldBuilder;
import org.device.management.ui.components.SPUIButton;
import org.device.management.ui.components.SPUIComponentProvider;
import org.device.management.ui.decorators.SPUIButtonStyleSmallNoBorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractTableHeader extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	@Autowired
	//	protected transient EventBus.SessionEventBus eventbus;

	@Autowired
	protected I18N i18n;

	private Label headerCaption;
	private Button addDeviceIcon;
	private Button deleteDeviceIcon;
	private TextField searchField;
	private SPUIButton searchResetIcon;

	@PostConstruct
	protected void init() {
		//		setWidth("500px");
		setSizeFull();
		createComponents();
		buildLayout();
		restoreState();
		//eventbus.subscribe(this);
	}

	@PreDestroy
	void destroy() {
		//eventbus.unsubscribe(this);
	}

	private void createComponents() {
		headerCaption = createHeaderCaption();
		searchField = createSearchField();
		addDeviceIcon = createAddDeviceIcon();
		searchResetIcon = createResetIcon();
		deleteDeviceIcon = createDeleteDeviceIcon();
	}

	private void buildLayout() {
		final HorizontalLayout titleFilterIconsLayout = createHeaderFilterIconLayout();
		final HorizontalLayout icons = new HorizontalLayout();
		icons.addComponents(addDeviceIcon,searchField, searchResetIcon, deleteDeviceIcon);


		titleFilterIconsLayout.addComponents(headerCaption,icons);
		titleFilterIconsLayout.setComponentAlignment(headerCaption,
				Alignment.TOP_LEFT);
		//		titleFilterIconsLayout.setComponentAlignment(addDeviceIcon,
		//				Alignment.TOP_RIGHT);
		//		titleFilterIconsLayout.setComponentAlignment(searchField,
		//				Alignment.TOP_RIGHT);
		//		titleFilterIconsLayout.setComponentAlignment(searchResetIcon,
		//				Alignment.TOP_RIGHT);
		titleFilterIconsLayout.setComponentAlignment(icons,
				Alignment.TOP_RIGHT);
		titleFilterIconsLayout.setExpandRatio(headerCaption, 0.6F);
		titleFilterIconsLayout.setExpandRatio(icons, 0.4F);
		//		titleFilterIconsLayout.setSizeFull();
		addComponent(titleFilterIconsLayout);
		setSizeFull();
		addStyleName("bordered-layout");
		addStyleName("no-border-bottom");
	}

	private void restoreState() {
		// TODO Auto-generated method stub

	}

	private Label createHeaderCaption() {
		return new LabelBuilder().name(getHeaderCaption()).buildCaptionLabel();
	}

	private Button createAddDeviceIcon() {
		final Button button = SPUIComponentProvider.getButton(getAddIconId(),
				"", "", null, false, FontAwesome.PLUS,
				SPUIButtonStyleSmallNoBorder.class);
		button.addClickListener(this::addNewItem);
		return button;
	}

	private TextField createSearchField() {
		return searchField = new TextFieldBuilder(getSearchBoxId())
		.createSearchField(event -> searchBy(event.getText()));
	}

	private SPUIButton createResetIcon() {
		final SPUIButton button = (SPUIButton) SPUIComponentProvider.getButton(
				getSearchRestIconId(), "", "", null, false, FontAwesome.SEARCH,
				SPUIButtonStyleSmallNoBorder.class);
		button.addClickListener(event -> onSearchResetClick());
		button.setData(Boolean.FALSE);
		return button;
	}


	private Button createDeleteDeviceIcon() {
		final Button button = SPUIComponentProvider.getButton(getAddIconId(),
				"", "", null, false, FontAwesome.TRASH_O,
				SPUIButtonStyleSmallNoBorder.class);
		button.addClickListener(this::deleteItem);
		return button;
	}

	private HorizontalLayout createHeaderFilterIconLayout() {
		final HorizontalLayout titleFilterIconsLayout = new HorizontalLayout();
		titleFilterIconsLayout.addStyleName(SPUIStyleDefinitions.WIDGET_TITLE);
		titleFilterIconsLayout.setSpacing(false);
		titleFilterIconsLayout.setMargin(false);
		titleFilterIconsLayout.setSizeFull();
		//titleFilterIconsLayout.setWidth("500px");
		return titleFilterIconsLayout;
	}

	private void onSearchResetClick() {
		final Boolean flag = isSearchFieldOpen();
		if (flag == null || Boolean.FALSE.equals(flag)) {
			// Clicked on search Icon
			openSearchTextField();
		} else {
			// Clicked on rest icon
			closeSearchTextField();
		}
	}

	protected Boolean isSearchFieldOpen() {
		return (Boolean) searchResetIcon.getData();
	}

	private void openSearchTextField() {
		searchResetIcon.addStyleName(SPUIStyleDefinitions.FILTER_RESET_ICON);
		searchResetIcon.togleIcon(FontAwesome.TIMES);
		searchResetIcon.setData(Boolean.TRUE);
		searchField.removeStyleName(SPUIStyleDefinitions.FILTER_BOX_HIDE);
		searchField.focus();
	}

	private void closeSearchTextField() {
		searchField.setValue("");
		searchField.addStyleName(SPUIStyleDefinitions.FILTER_BOX_HIDE);
		searchResetIcon.removeStyleName(SPUIStyleDefinitions.FILTER_RESET_ICON);
		searchResetIcon.togleIcon(FontAwesome.SEARCH);
		searchResetIcon.setData(Boolean.FALSE);
		resetSearchText();
	}

	protected abstract String getHeaderCaption();

	protected abstract String getSearchBoxId();

	protected abstract void searchBy(String newSearchText);

	protected abstract void addNewItem(final Button.ClickEvent event);

	protected abstract String getAddIconId();

	protected abstract void deleteItem(final Button.ClickEvent event);

	protected abstract void resetSearchText();

	protected abstract String getSearchRestIconId();

}
