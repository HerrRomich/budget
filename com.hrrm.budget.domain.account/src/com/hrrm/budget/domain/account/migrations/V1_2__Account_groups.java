package com.hrrm.budget.domain.account.migrations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

public class V1_2__Account_groups implements JdbcMigration {

	private static final String INSERT_INTO_ACCOUNT_GROUP_STAEMENT = 
			  "insert into account_group(\r\n"
			+ "                          name\r\n"
			+ "                          ) "
			+ "            values(\r\n"
			+ "                   ?\r\n"
			+ "                  )";
	private static final List<String> ACCOUNT_GROUP_NAME_LIST = Arrays.asList("Наличные деньги", "Безналичные деньги");

	@Override
	public void migrate(Connection connection) throws Exception {
		PreparedStatement stmt = connection.prepareStatement(INSERT_INTO_ACCOUNT_GROUP_STAEMENT);
		try {
			ACCOUNT_GROUP_NAME_LIST.forEach(name -> {
				insertAccountGroup(stmt, name);
			});
		} finally {
			stmt.close();
		}
	}

	private void insertAccountGroup(PreparedStatement stmt, String name) {
		try {
			stmt.setString(1, name);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
