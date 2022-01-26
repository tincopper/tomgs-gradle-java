package com.tomgs.hello.plugin;

import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * HelloPluginTest
 *
 * @author tomgs
 * @since 2022/1/20
 */
public class HelloPluginTest {

    @Test
    public void hello() {
        Project project = ProjectBuilder.builder().build();
        // 应用插件
        project.getPluginManager().apply("com.tomgs.hello");
        assertTrue(project.getTasks().getByName("hello") instanceof SayHelloTask);

        UserExtension userExtension = (UserExtension) project.getExtensions().getByName("user");
        userExtension.getName().set("tomgs");
        userExtension.getCountry().set("china");

        // 获取插件task
        Task task = project.getTasks().getByName("hello", taskConfig -> {
            ((SayHelloTask) taskConfig).getUser().set(userExtension);
        });
        // 获取task的action
        List<Action<? super Task>> actions = task.getActions();
        System.out.println(actions);

        // 执行对应task的action
        actions.get(0).execute(task);
    }

    @Test
    public void testJavaPlugin() {
        Project project = ProjectBuilder.builder().build();
        project.getPluginManager().apply(JavaPlugin.class);
        SourceSetContainer sourceSets = project.getExtensions().getByType(SourceSetContainer.class);
        SourceSet main = sourceSets.getByName(SourceSet.MAIN_SOURCE_SET_NAME);
        main.getJava().setSrcDirs(Collections.singletonList("src"));

    }

}
