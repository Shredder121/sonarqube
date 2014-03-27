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

package org.sonar.server.rule;

public final class RuleDocument {

  public static final String FIELD_ID = "id";
  public static final String FIELD_REPOSITORY_KEY = "repositoryKey";
  public static final String FIELD_KEY = "key";
  public static final String FIELD_LANGUAGE = "language";
  public static final String FIELD_NAME = "name";
  public static final String FIELD_SEVERITY = "severity";
  public static final String FIELD_DESCRIPTION = "description";
  public static final String FIELD_TEMPLATE_ID = "templateId";
  public static final String FIELD_STATUS = "status";
  public static final String FIELD_SYSTEM_TAGS = "systemTags";
  public static final String FIELD_ADMIN_TAGS = "adminTags";
  public static final String FIELD_PARAMS = "params";
  public static final String FIELD_UPDATED_AT = "updatedAt";
  public static final String FIELD_CREATED_AT = "createdAt";

  public static final String FIELD_CHARACTERISTIC_ID = "characteristicId";
  public static final String FIELD_CHARACTERISTIC_KEY = "characteristicKey";
  public static final String FIELD_CHARACTERISTIC_NAME = "characteristicName";
  public static final String FIELD_SUB_CHARACTERISTIC_ID = "subCharacteristicId";
  public static final String FIELD_SUB_CHARACTERISTIC_KEY = "subCharacteristicKey";
  public static final String FIELD_SUB_CHARACTERISTIC_NAME = "subCharacteristicName";
  public static final String FIELD_REMEDIATION_FUNCTION = "remediationFunction";
  public static final String FIELD_REMEDIATION_COEFFICIENT = "remediationCoefficient";
  public static final String FIELD_REMEDIATION_OFFSET = "remediationOffset";

  public static final String FIELD_PARAM_KEY = "key";
  public static final String FIELD_PARAM_TYPE = "type";
  public static final String FIELD_PARAM_DEFAULT_VALUE = "defaultValue";
  public static final String FIELD_PARAM_DESCRIPTION = "description";
  public static final String FIELD_CARDINALITY = "cardinality";

  public static final String FIELD_NOTE = "note";
  public static final String FIELD_NOTE_DATA = "data";
  public static final String FIELD_NOTE_USER_LOGIN = "userLogin";
  public static final String FIELD_NOTE_CREATED_AT = "createdAt";
  public static final String FIELD_NOTE_UPDATED_AT = "updatedAt";

  private RuleDocument() {
    // Only constants
  }

}
