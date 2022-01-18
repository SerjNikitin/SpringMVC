package com.example.springmvc.mvcLayer.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@ContextConfiguration(classes = {MvcConfiguration.class})
@ExtendWith(SpringExtension.class)
class MvcConfigurationTest {
    @Autowired
    private MvcConfiguration mvcConfiguration;

    @Test
    void testAddResourceHandlers() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by addResourceHandlers(ResourceHandlerRegistry)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        AnnotationConfigReactiveWebApplicationContext applicationContext = new AnnotationConfigReactiveWebApplicationContext();
        this.mvcConfiguration
                .addResourceHandlers(new ResourceHandlerRegistry(applicationContext, new MockServletContext()));
    }
}

