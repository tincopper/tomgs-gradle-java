package com.tomgs.hello.plugin;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

/**
 * HelloPlugin
 * <p>
 * Plugin 是一个泛型接口，有一个抽象方法 apply，它的参数类型可以是 Project, Settings, 或者 Gradle。
 * 类型为 Project，插件可以应用于 build.gradle；
 * 类型为 Settings，插件可应用于 settings.gradle；
 * 类型为 Gradle, 插件可应用于 Gradle 初始化脚本。
 *
 * @author tomgs
 * @since 2022/1/19
 */
public class HelloPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        System.out.println("hello project: " + project.getName());
        project.getTasks().register("hello", SayHelloTask.class, task -> {
            // 配置task
            // 获取task参数，通过Extension
            UserExtension userExtension = (UserExtension) (project.getExtensions().getByName("user"));
            // 设置task参数
            task.getUser().set(userExtension);
        });
        //project.getExtensions().add("user", UserExtension.class);

        PrintUserTask printUserTask = project.getTasks().create("task_root", PrintUserTask.class, new Action<PrintUserTask>() {
            @Override
            public void execute(PrintUserTask printUserTask) {
                // 设置参数
                UserExtension userExtension = (UserExtension) (project.getExtensions().getByName("user"));
                printUserTask.getUser().set(userExtension);
            }
        });

        project.getTasks().create("task_name", SayHelloTask.class, new Action<SayHelloTask>() {
            @Override
            public void execute(SayHelloTask task) {
                task.setGroup("groupName");
                task.setDescription("Pushes created Docker image to the repository.");
                // 设置依赖任务
                task.dependsOn(printUserTask);
                // 设置参数
                UserExtension userExtension = (UserExtension) (project.getExtensions().getByName("user"));
                task.getUser().set(userExtension);
            }
        });
    }

}
