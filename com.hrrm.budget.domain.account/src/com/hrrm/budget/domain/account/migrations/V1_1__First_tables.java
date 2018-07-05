package com.hrrm.budget.domain.account.migrations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

public class V1_1__First_tables implements JdbcMigration {

	private static final String CREATE_ACCOUNT_GROUP_TABLE_STATEMENT = "create table account_group(\r\n  "
			+ "  id int not null auto_increment primary key comment 'Unique identifier of account group,',\r\n"
			+ "  name varchar(250) not null comment 'Name of an account group'" + ")";
	private static final String CREATE_ACCOUNT_TABLE_STATEMENT = "create table account(\r\n"
			+ "  id int not null auto_increment primary key,\r\n" 
			+ "  name varchar(250) not null comment 'Name of an account group',\r\n"
			+ "  group_id int not null references account_group(id)"
			+ ")";

	private static List<String> statements = Arrays.asList(CREATE_ACCOUNT_GROUP_TABLE_STATEMENT,
			CREATE_ACCOUNT_TABLE_STATEMENT);

	@Override
	public void migrate(Connection connection) throws Exception {
		Statement stmt = connection.createStatement();
		try {
			statements.stream().forEach(s -> {
				try {
					stmt.executeUpdate(s);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			});
		} finally {
			stmt.close();
		}
	}

}
