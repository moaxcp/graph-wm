package com.github.moaxcp;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class XephyrPlugin implements Plugin<Project> {
  @Override
  public void apply(Project project) {
    project.getTasks().create("startXephyr", StartXephyrTask.class);
  }
}
