package com.github.moaxcp;

import com.github.moaxcp.xephyr.XephyrRunner;
import java.io.IOException;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class StartXephyrTask extends DefaultTask {
  @TaskAction
  public void start() throws IOException {
    XephyrRunner.builder().build().start();
  }
}
