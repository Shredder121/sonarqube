/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2014 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.sonar.server.computation.source;

import com.google.common.base.Optional;
import org.sonar.core.util.CloseableIterator;
import org.sonar.server.computation.batch.BatchReportReader;
import org.sonar.server.computation.component.Component;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;
import static org.sonar.server.computation.component.Component.Type.FILE;

public class SourceLinesRepositoryImpl implements SourceLinesRepository {

  private final BatchReportReader reportReader;

  public SourceLinesRepositoryImpl(BatchReportReader reportReader) {
    this.reportReader = reportReader;
  }

  @Override
  public CloseableIterator<String> readLines(Component component) {
    requireNonNull(component, "Component should not be bull");
    checkArgument(component.getType() == FILE, "Component '%s' is not a file", component);

    Optional<CloseableIterator<String>> linesIteratorOptional = reportReader.readFileSource(component.getReportAttributes().getRef());

    checkState(linesIteratorOptional.isPresent(), String.format("File '%s' has no source code", component));
    int numberOfLines = reportReader.readComponent(component.getReportAttributes().getRef()).getLines();
    CloseableIterator<String> lineIterator = linesIteratorOptional.get();

    return new ComponentLinesCloseableIterator(lineIterator, numberOfLines);
  }

  private static class ComponentLinesCloseableIterator extends CloseableIterator<String> {
    private static final String EXTRA_END_LINE = "";
    private final CloseableIterator<String> delegate;
    private final int numberOfLines;
    private int currentLine = 0;
    private boolean addedExtraLine = false;

    public ComponentLinesCloseableIterator(CloseableIterator<String> lineIterator, int numberOfLines) {
      this.delegate = lineIterator;
      this.numberOfLines = numberOfLines;
    }

    @Override
    public boolean hasNext() {
      return delegate.hasNext() || (currentLine < numberOfLines && !addedExtraLine);
    }

    @Override
    public String next() {
      if (!hasNext()) {
        // will throw NoSuchElementException
        return delegate.next();
      }

      currentLine++;
      if (delegate.hasNext()) {
        return delegate.next();
      }
      addedExtraLine = true;
      return EXTRA_END_LINE;
    }

    @Override
    protected String doNext() {
      throw new UnsupportedOperationException("No implemented because hasNext and next are override");
    }

    @Override
    protected void doClose() throws Exception {
      delegate.close();
    }
  }
}
