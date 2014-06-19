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

package org.sonar.server.activity;

import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sonar.api.utils.DateUtils;
import org.sonar.core.activity.Activity;
import org.sonar.server.qualityprofile.QProfileActivityQuery;
import org.sonar.server.qualityprofile.QProfileService;
import org.sonar.server.search.QueryOptions;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RubyActivityServiceTest {

  @Mock
  QProfileService service;

  @Captor
  ArgumentCaptor<QProfileActivityQuery> activityArgumentCaptor;

  @Captor
  ArgumentCaptor<QueryOptions> queryOptionsArgumentCaptor;

  RubyActivityService rubyActivityService;

  @Before
  public void setUp() throws Exception {
    rubyActivityService = new RubyActivityService(service);
  }

  @Test
  public void search() throws Exception {
    Date since = DateUtils.parseDate("2014-05-19");
    Date to = DateUtils.parseDate("2014-06-19");
    rubyActivityService.search(ImmutableMap.<String, Object>of("profileKeys", "PROFILE_KEY", "since", since, "to", to));

    verify(service).findActivities(activityArgumentCaptor.capture(), queryOptionsArgumentCaptor.capture());

    assertThat(queryOptionsArgumentCaptor.getValue().getLimit()).isEqualTo(QueryOptions.MAX_LIMIT);

    assertThat(activityArgumentCaptor.getValue().getQprofileKeys()).containsOnly("PROFILE_KEY");
    assertThat(activityArgumentCaptor.getValue().getTypes()).containsOnly(Activity.Type.QPROFILE);
    assertThat(activityArgumentCaptor.getValue().getSince()).isEqualTo(since);
    assertThat(activityArgumentCaptor.getValue().getTo()).isEqualTo(to);
  }

  @Test
  public void search_with_empty_fields() throws Exception {
    rubyActivityService.search(ImmutableMap.<String, Object>of());

    verify(service).findActivities(activityArgumentCaptor.capture(), queryOptionsArgumentCaptor.capture());

    assertThat(queryOptionsArgumentCaptor.getValue().getLimit()).isEqualTo(QueryOptions.MAX_LIMIT);

    assertThat(activityArgumentCaptor.getValue().getQprofileKeys()).isEmpty();
    assertThat(activityArgumentCaptor.getValue().getSince()).isNull();
    assertThat(activityArgumentCaptor.getValue().getTo()).isNull();
  }
}
