/*
 * Copyright 2020-2022 the original author or authors.
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

package com.moran.conf.mybatis.handler;

import cn.hutool.json.JSONUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LongListTypeHandler extends BaseTypeHandler<List<Long>> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, List<Long> parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(i, parameter != null ? JSONUtil.toJsonStr(parameter) : null);
  }

  @Override
  public List<Long> getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String string = rs.getString(columnName);
    return string != null ? JSONUtil.toList(string, Long.class) : null;
  }

  @Override
  public List<Long> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String string = rs.getString(columnIndex);
    return string != null ? JSONUtil.toList(string, Long.class) : null;
  }

  @Override
  public List<Long> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    String string = cs.getString(columnIndex);
    return string != null ? JSONUtil.toList(string, Long.class) : null;
  }
}
