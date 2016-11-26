package org.device.management;

import org.device.management.menu.DashboardEvent.PostViewChangeEvent;
import org.device.management.menu.DashboardMenu;
import org.device.management.menu.DashboardMenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Push;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.ClientConnector.DetachListener;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI(path="/claimDevice")
@Push(value = PushMode.AUTOMATIC,transport = Transport.WEBSOCKET)
public class DeviceManagementUI extends AbstractDeviceManagementUI implements DetachListener{
	private static final long serialVersionUID = 1L;
	private static final Logger  LOGGER = LoggerFactory.getLogger(DeviceManagementUI.class);


	@Autowired
	private SpringViewProvider viewProvider;
	
//	@Autowired
//    protected transient EventBus.SessionEventBus eventBus;

	@Autowired
	private DashboardMenu dashboardMenu;
	
	private HorizontalLayout content;


	@Override
	protected void init(VaadinRequest request) {
		LOGGER.info("DeviceManagementUI init starts uiid - {}", getUI().getUIId());
		Responsive.makeResponsive(this);
		addStyleName(ValoTheme.UI_WITH_MENU);
		setResponsive(true);
		final HorizontalLayout rootLayout = new HorizontalLayout();
        rootLayout.setSizeFull();
        dashboardMenu.init();
		dashboardMenu.setResponsive(true);
		
		final VerticalLayout contentVerticalLayout = new VerticalLayout();
        contentVerticalLayout.addComponent(buildHeader());
        contentVerticalLayout.setSizeFull();
        
        
        rootLayout.addComponent(dashboardMenu);
        rootLayout.addComponent(contentVerticalLayout);
        
        content = new HorizontalLayout();
        contentVerticalLayout.addComponent(content);
        content.setStyleName("view-content");
        
        rootLayout.setExpandRatio(contentVerticalLayout, 1.0F);
        contentVerticalLayout.setStyleName("main-content");
        contentVerticalLayout.setExpandRatio(content, 1.0F);
        setContent(rootLayout);
        
		final Navigator navigator = new Navigator(this, content);
		navigator.addViewChangeListener(new ViewChangeListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				return true;
			}
			
			@Override
			public void afterViewChange(ViewChangeEvent event) {
				final DashboardMenuItem view = dashboardMenu.getByViewName(event.getViewName());
                dashboardMenu.postViewChange(new PostViewChangeEvent(view));
                if (view == null) {
                    content.setCaption(null);
                    return;
                }
                content.setCaption(view.getDashboardCaptionLong());
			}
		});
		navigator.addProvider(new ManagementViewProvider());
        setNavigator(navigator);
	}


	private Component buildHeader() {
		final CssLayout cssLayout = new CssLayout();
        cssLayout.setStyleName("view-header");
        return cssLayout;
	}
	
	@Override
	public void detach(DetachEvent event) {
		LOGGER.info("DeviceManagementUI is detached uiid - {}", getUIId());
		//eventBus.unsubscribe(this);
	}
	
	
	
	
	
	private class ManagementViewProvider implements ViewProvider {

        private static final long serialVersionUID = 1L;

        @Override
        public String getViewName(final String viewAndParameters) {
            return viewProvider.getViewName(getStartView(viewAndParameters));
        }

        @Override
        public View getView(final String viewName) {
            return viewProvider.getView(getStartView(viewName));
        }

        private String getStartView(final String viewName) {
            final DashboardMenuItem view = dashboardMenu.getByViewName(viewName);
            if ("".equals(viewName) && !dashboardMenu.isAccessibleViewsEmpty()) {
                return dashboardMenu.getInitialViewName();
            }
            if (view == null || dashboardMenu.isAccessDenied(viewName)) {
                return " ";
            }
            return viewName;
        }

    }

}
