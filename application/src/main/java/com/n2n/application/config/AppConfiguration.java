package com.n2n.application.config;

import com.n2n.application.alien.AlienClockConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AlienClockConfig.class)
public class AppConfiguration {
}
