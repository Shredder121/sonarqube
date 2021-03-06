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

package org.sonar.server.permission.ws.template;

import com.google.common.collect.HashBasedTable;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.sonar.api.resources.Qualifiers;

import static java.util.Collections.singletonList;
import static org.sonar.db.permission.PermissionTemplateTesting.newPermissionTemplateDto;

public class SearchTemplatesDataTest {
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  SearchTemplatesData.Builder underTest = SearchTemplatesData.newBuilder()
    .defaultTemplates(singletonList(new DefaultPermissionTemplateFinder.TemplateUuidQualifier("template_uuid", Qualifiers.PROJECT)))
    .templates(singletonList(newPermissionTemplateDto()))
    .userCountByTemplateIdAndPermission(HashBasedTable.<Long, String, Integer>create())
    .groupCountByTemplateIdAndPermission(HashBasedTable.<Long, String, Integer>create());

  @Test
  public void fail_if_templates_is_null() {
    expectedException.expect(IllegalStateException.class);
    underTest.templates(null);

    underTest.build();
  }

  @Test
  public void fail_if_default_templates_are_null() {
    expectedException.expect(IllegalStateException.class);
    underTest.defaultTemplates(null);

    underTest.build();
  }

  @Test
  public void fail_if_user_count_is_null() {
    expectedException.expect(IllegalStateException.class);
    underTest.userCountByTemplateIdAndPermission(null);

    underTest.build();
  }

  @Test
  public void fail_if_group_count_is_null() {
    expectedException.expect(IllegalStateException.class);
    underTest.groupCountByTemplateIdAndPermission(null);

    underTest.build();
  }
}
