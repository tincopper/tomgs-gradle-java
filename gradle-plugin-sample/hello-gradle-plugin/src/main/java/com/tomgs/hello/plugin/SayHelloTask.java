package com.tomgs.hello.plugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

/**
 * SayHelloTask
 *
 * @author tomgs
 * @since 2022/1/20
 */
public abstract class SayHelloTask extends DefaultTask {

    @Input
    abstract Property<UserExtension> getUser();

    @TaskAction
    public void hello() {
        Property<UserExtension> user = getUser();
        System.out.println("Hello: " + user.get().getName().get());
    }

}
