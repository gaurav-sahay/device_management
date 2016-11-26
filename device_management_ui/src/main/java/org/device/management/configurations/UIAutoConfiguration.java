package org.device.management.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vaadin.spring.annotation.EnableVaadinExtensions;
import org.vaadin.spring.events.annotation.EnableEventBus;

/**
 * The hawkbit-ui autoconfiguration.
 */
@Configuration
@EnableVaadinExtensions
@EnableEventBus
public class UIAutoConfiguration {

    /**
     * A message source bean to add distributed message sources.
     * 
     * @return the message bean.
     */
    @Bean(name = "messageSource")
    public DistributedResourceBundleMessageSource messageSource() {
        return new DistributedResourceBundleMessageSource();
    }


}
