package com.tomgs.hello.plugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Generate
 *
 * @author tomgs
 * @since 2022/1/24
 */
public abstract class Generate extends DefaultTask {

    @Input
    public abstract Property<Integer> getFileCount();

    @Input
    public abstract Property<String> getContent();

    @OutputDirectory
    public abstract RegularFileProperty getGeneratedFileDir();

    @TaskAction
    public void perform() throws IOException {
        for (int i = 1; i <= getFileCount().get(); i++) {
            writeFile(new File(getGeneratedFileDir().get().getAsFile(), i + ".txt"), getContent().get());
        }
    }

    private void writeFile(File destination, String content) throws IOException {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(destination))) {
            output.write(content);
        }
    }

}
