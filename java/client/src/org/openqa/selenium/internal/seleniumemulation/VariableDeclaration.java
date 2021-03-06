/*
Copyright 2010 Selenium committers

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */


package org.openqa.selenium.internal.seleniumemulation;

import java.util.regex.Pattern;

/**
 * Prepend a variable declaration to a script.
 */
public class VariableDeclaration implements ScriptMutator {
  private final Pattern pattern;
  private final String declaration;

  public VariableDeclaration(String raw, String declaration) {
    this.declaration = declaration;
    raw = raw.replace(".", "\\s*\\.\\s*")
        .replace("(", "\\(")
        .replace(")", "\\)");

    pattern = Pattern.compile(".*" + raw + ".*");
  }

  public void mutate(String script, StringBuilder outputTo) {
    if (!pattern.matcher(script).matches()) {
      return;
    }

    outputTo.append(declaration);
  }
}
