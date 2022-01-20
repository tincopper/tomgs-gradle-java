package com.tomgs.hello.plugin;

import org.gradle.api.provider.Property;

/**
 * User
 *
 * @author tomgs
 * @since 2022/1/20
 */
public interface UserExtension {

    Property<String> getName();

    Property<String> getCountry();
}
