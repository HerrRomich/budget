package com.hrrm.budget.domain.account.migrations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

public class V1_3__Accounts implements JdbcMigration {

  private static final String INSERT_INTO_ACCOUNT_STAEMENT = 
      "insert into account(\r\n"
      + "                    name,\r\n"
      + "                    group_id\r\n"
      + "                 ) "
      + "           values(\r\n"
      + "                    ?,"
      + "                    (select id\r\n"
      + "                       from account_group\r\n"
      + "                      where name = ?\r\n"
      + "                    )\r\n"
      + "                  )";


  private static final List<Pair<String, String>> ACCOUNT_NAME_LIST = Arrays.asList(
      Pair.of("Наличные деньги", "Кошелек Пупсика"), Pair.of("Наличные деньги", "Кошелек Масички"),
      Pair.of("Безналичные деньги", "Norisbank "));

  @Override
  public void migrate(Connection connection) throws Exception {
    PreparedStatement stmt = connection.prepareStatement(INSERT_INTO_ACCOUNT_STAEMENT);
    try {
      ACCOUNT_NAME_LIST.forEach(accountPair -> {
        insertAccount(stmt, accountPair.getRight(), accountPair.getLeft());
      });
    } finally {
      stmt.close();
    }

  }

  private void insertAccount(PreparedStatement stmt, String name, String groupName) {
    try {
        stmt.setString(1, name);
        stmt.setString(2, groupName);
        stmt.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

}
