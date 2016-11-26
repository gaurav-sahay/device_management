package org.device.management.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.device.management.menu.DashboardEvent.PostViewChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@UIScope
@SpringComponent
public class DashboardMenu extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8460488081912924631L;


	private static final Logger  LOGGER = LoggerFactory.getLogger(DashboardMenu.class);

	private static final String ID = "dashboard-menu";

	@Autowired
	private final List<DashboardMenuItem> dashboardVaadinViews = new ArrayList<>();


	private boolean accessibleViewsEmpty;


	private String initialViewName;


	private final List<ValoMenuItemButton> menuButtons = new ArrayList<>();

	public void init(){
		LOGGER.info("Initialising the dashboard menu ");
		addStyleName("valo-menu");
		setId(ID);
		setSizeUndefined();
		setCompositionRoot(buildContent());
	}

	private Component buildContent() {
		final VerticalLayout dashboardMenuLayout = new VerticalLayout();
		dashboardMenuLayout.setSizeFull();
		final VerticalLayout menuContent = getMenuLayout();
		menuContent.addComponent(buildTitle());
		menuContent.addComponent(buildUserMenu());
		//		menuContent.addComponent(buildToggleButton());
		menuContent.addComponent(buildMenuItems());

		return menuContent;
	}


	private VerticalLayout getMenuLayout() {
		final VerticalLayout menuContent = new VerticalLayout();
		menuContent.addStyleName("sidebar");
		menuContent.addStyleName(ValoTheme.MENU_PART);
		menuContent.addStyleName("no-vertical-drag-hints");
		menuContent.addStyleName("no-horizontal-drag-hints");
		menuContent.setWidth(null);
		menuContent.setHeight("100%");
		return menuContent;
	}

	private Component buildTitle() {
		Label logo = new Label("<strong>Device Management</strong>",
				ContentMode.HTML);
		logo.setSizeUndefined();
		HorizontalLayout logoWrapper = new HorizontalLayout(logo);
		logoWrapper.setComponentAlignment(logo, Alignment.TOP_CENTER);
		logoWrapper.addStyleName("valo-menu-title");
		return logoWrapper;
	}

	private Component buildUserMenu() {
		final MenuBar settings = new MenuBar();
		settings.addStyleName("user-menu");
		settings.setHtmlContentAllowed(true);
		final MenuItem settingsItem = settings.addItem("", new ThemeResource("img/profile-pic-300px.jpg"), null);

		//        final String formattedTenant = UserDetailsFormatter.formatCurrentTenant();
		//        final String formattedUsername = UserDetailsFormatter.formatCurrentUsername();
		//        String tenantAndUsernameHtml = "";
		//        if (!StringUtils.isEmpty(formattedTenant)) {
		//            tenantAndUsernameHtml += formattedTenant + "<br>";
		//        }
		//        tenantAndUsernameHtml += formattedUsername;
		//        settingsItem.setText(tenantAndUsernameHtml);
		//        settingsItem.setDescription(formattedUsername);
		settingsItem.setStyleName("user-menuitem");

		settingsItem.addItem("Sign Out", selectedItem -> Page.getCurrent().setLocation("/UI/logout"));
		return settings;
	}

	private VerticalLayout buildMenuItems() {
		final VerticalLayout menuItemsLayout = new VerticalLayout();
		menuItemsLayout.addStyleName("valo-menuitems");
		menuItemsLayout.setHeight(100.0F, Unit.PERCENTAGE);

		final List<DashboardMenuItem> accessibleViews = getAccessibleViews();
		if (accessibleViews.isEmpty()) {
			accessibleViewsEmpty = true;
			return menuItemsLayout;
		}
		initialViewName = accessibleViews.get(0).getViewName();
		for (final DashboardMenuItem view : accessibleViews) {
			final ValoMenuItemButton menuItemComponent = new ValoMenuItemButton(view);
			menuButtons.add(menuItemComponent);
			menuItemsLayout.addComponent(menuItemComponent);
		}
		return menuItemsLayout;
	}

	private List<DashboardMenuItem> getAccessibleViews() {
		return this.dashboardVaadinViews.stream().collect(Collectors.toList());
	}
	
	/**
     * Returns the dashboard view type by a given view name.
     *
     * @param viewName
     *            the name of the view to retrieve
     * @return the dashboard view for a given viewname or {@code null} if view
     *         with given viewname does not exists
     */
    public DashboardMenuItem getByViewName(final String viewName) {
        final Optional<DashboardMenuItem> findFirst = dashboardVaadinViews.stream()
                .filter(view -> view.getViewName().equals(viewName)).findFirst();

        if (findFirst == null || !findFirst.isPresent()) {
            return null;
        }

        return findFirst.get();
    }
    
    /**
     * notifies the dashboard that the view has been changed and the button
     * needs to be re-styled.
     *
     * @param event
     *            the post view change event
     */
    public void postViewChange(final PostViewChangeEvent event) {
        menuButtons.forEach(button -> button.postViewChange(event));
    }
    
    /**
     * Returns the view name for the start page after login.
     *
     * @return the initialViewName of the start page
     */
    public String getInitialViewName() {
        return initialViewName;
    }

    /**
     * Is a View available.
     *
     * @return the accessibleViewsEmpty <true> no rights for any view <false> a
     *         view is available
     */
    public boolean isAccessibleViewsEmpty() {
        return accessibleViewsEmpty;
    }
    
    /**
     * Is the given view accessible.
     *
     * @param viewName
     *            the view name
     * @return <true> = denied, <false> = accessible
     */
    public boolean isAccessDenied(final String viewName) {
        final List<DashboardMenuItem> accessibleViews = getAccessibleViews();
        boolean accessDeined = Boolean.TRUE.booleanValue();
        for (final DashboardMenuItem dashboardViewType : accessibleViews) {
            if (dashboardViewType.getViewName().equals(viewName)) {
                accessDeined = Boolean.FALSE.booleanValue();
            }
        }
        return accessDeined;
    }

	/**
	 * An menu item button wrapper for the dashboard menu item.
	 */
	public static final class ValoMenuItemButton extends Button {

		private static final long serialVersionUID = 1L;

		private static final String STYLE_SELECTED = "selected";

		private final DashboardMenuItem view;

		/**
		 * creates a new button in case of pressed switches to the given
		 * {@code view}.
		 *
		 * @param view
		 *            the view to switch to in case the button is pressed
		 */
		public ValoMenuItemButton(final DashboardMenuItem view) {
			this.view = view;
			setPrimaryStyleName("valo-menu-item");
			setIcon(view.getDashboardIcon());
			setCaption(view.getDashboardCaption());
			setDescription(view.getDashboardCaptionLong());
			/* Avoid double click */
			setDisableOnClick(true);
			addClickListener(event -> event.getComponent().getUI().getNavigator().navigateTo(view.getViewName()));
		}

		/**
		 * notifies the button to change his style.
		 *
		 * @param event
		 *            the post view change event
		 */
		public void postViewChange(final PostViewChangeEvent event) {
			removeStyleName(STYLE_SELECTED);
			if (event.getView().equals(view)) {
				addStyleName(STYLE_SELECTED);
				/* disable current selected view */
				setEnabled(false);
			} else {
				/* Enable other views */
				setEnabled(true);
			}
		}
	}



}
