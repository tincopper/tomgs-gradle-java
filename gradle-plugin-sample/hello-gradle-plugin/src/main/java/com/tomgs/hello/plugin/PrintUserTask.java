package com.tomgs.hello.plugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

/**
 * PrintUserTask
 *
 * @author tomgs
 * @since 2022/1/20
 */
public abstract class PrintUserTask extends DefaultTask {

    @Input
    public abstract Property<UserExtension> getUser();

    @TaskAction
    public void print() {
        UserExtension userExtension = getUser().get();
        System.out.println("User[" + userExtension.getName().get() + ", " + userExtension.getCountry().get() + "]");
    }

}
