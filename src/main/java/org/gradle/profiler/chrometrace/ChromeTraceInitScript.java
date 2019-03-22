/*
 * Copyright 2003-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.profiler.chrometrace;

import org.gradle.profiler.GradleInstrumentation;
import org.gradle.profiler.GradleScenarioDefinition;
import org.gradle.profiler.ScenarioSettings;

import java.io.File;
import java.io.PrintWriter;

public class ChromeTraceInitScript extends GradleInstrumentation {
    private final File traceFile;

    public ChromeTraceInitScript(ScenarioSettings scenarioSettings) {
        GradleScenarioDefinition scenario = scenarioSettings.getScenario();
        traceFile = new File(scenario.getOutputDir(), scenario.getProfileName() + "-trace.html");
    }

    @Override
    protected String getJarBaseName() {
        return "chrome-trace";
    }

    @Override
    protected void generateInitScriptBody(PrintWriter writer) {
        writer.write("rootProject { ext.chromeTraceFile = new File(new URI(\"" + traceFile.toURI() + "\")) }\n");
        writer.write("apply plugin: org.gradle.trace.GradleTracingPlugin\n");
    }
}
