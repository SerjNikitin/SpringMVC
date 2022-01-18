package com.example.springmvc.mvcLayer.configuration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

class SecurityConfigurationTest {
    @Test
    void testPasswordEncoder() {
        // TODO: This test is incomplete.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     SecurityConfiguration.userDetailsService
        //     WebSecurityConfigurerAdapter.authenticationBuilder
        //     WebSecurityConfigurerAdapter.authenticationConfiguration
        //     WebSecurityConfigurerAdapter.authenticationManager
        //     WebSecurityConfigurerAdapter.authenticationManagerInitialized
        //     WebSecurityConfigurerAdapter.contentNegotiationStrategy
        //     WebSecurityConfigurerAdapter.context
        //     WebSecurityConfigurerAdapter.disableDefaults
        //     WebSecurityConfigurerAdapter.disableLocalConfigureAuthenticationBldr
        //     WebSecurityConfigurerAdapter.http
        //     WebSecurityConfigurerAdapter.localConfigureAuthenticationBldr
        //     WebSecurityConfigurerAdapter.logger
        //     WebSecurityConfigurerAdapter.objectPostProcessor
        //     WebSecurityConfigurerAdapter.trustResolver
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.random
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version

        (new SecurityConfiguration(new JdbcDaoImpl())).passwordEncoder();
    }

    @Test
    void testAuthenticationProvider() {
        DaoAuthenticationProvider actualAuthenticationProviderResult = (new SecurityConfiguration(new JdbcDaoImpl()))
                .authenticationProvider();
        assertTrue(actualAuthenticationProviderResult
                .getUserCache() instanceof org.springframework.security.core.userdetails.cache.NullUserCache);
        assertTrue(actualAuthenticationProviderResult.isHideUserNotFoundExceptions());
        assertFalse(actualAuthenticationProviderResult.isForcePrincipalAsString());
    }
}

