/*
 * Copyright 2021 The Bazel Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.idea.blaze.base.model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.idea.blaze.base.model.primitives.Label;
import com.intellij.openapi.project.Project;
import java.util.Optional;

/**
 * Allows to pass a map of {@link LibraryKey} to {@link Label} at instance creation that is used to
 * resolve {@link Label}s.
 */
public final class FakeLibraryToTargetResolver implements LibraryToTargetResolver {

  private final ImmutableMap<LibraryKey, Label> libraryKeyToLabelMap;

  public FakeLibraryToTargetResolver(ImmutableMap<LibraryKey, Label> libraryKeyToLabelMap) {
    this.libraryKeyToLabelMap = libraryKeyToLabelMap;
  }

  @Override
  public ImmutableSet<Label> doGetAllTargetLabelsForProject(Project project) {
    return ImmutableSet.copyOf(libraryKeyToLabelMap.values());
  }

  @Override
  public Optional<Label> resolveLibraryToTarget(Project project, LibraryKey library) {
    return Optional.ofNullable(libraryKeyToLabelMap.get(library));
  }
}
