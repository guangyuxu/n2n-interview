package com.n2n.api.config;

import com.n2n.application.config.AppConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppConfiguration.class})
public class ApiConfiguration {
}
