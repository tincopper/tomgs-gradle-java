package com.tomgs.hello.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * GoodbyePlugin
 *
 * @author tomgs
 * @since 2022/1/19
 */
public class GoodbyePlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        System.out.println("goodbye project: " + project.getName());
        project.getExtensions().add("user", UserExtension.class);
        project.getTasks().register("printUser", PrintUserTask.class, task -> {
            UserExtension userExtension = (UserExtension) (project.getExtensions().getByName("user"));
            task.getUser().set(userExtension);
        });
    }

}
