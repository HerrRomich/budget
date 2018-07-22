package com.hrrm.budget.domain.account.migrations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;

import org.apache.commons.lang3.tuple.Pair;
import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

public class V1_2__Accounts implements JdbcMigration {

    private static final String INSERT_INTO_ACCOUNT_STAEMENT = "" +
	"insert into account(\r\n" +
	"                    name\r\n" +
	"                 )\r\n" +
	"           values(\r\n" +
	"                    ?\r\n" +
	"                  )";

    private static final String INSERT_INTO_ACCOUNT_TAG_STAEMENT = "" +
	"insert into account_tag(\r\n" +
	"                        account_id,\r\n" +
	"                        tag\r\n" +
	"                       )\r\n" +
	"                 values(\r\n" +
	"                        ?,\r\n" +
	"                        ?\r\n" +
	"                       )";

    private static final List<Pair<String, List<String>>> ACCOUNT_NAME_LIST = Arrays.asList(
	    Pair.of("Кошелек Пупсика", Arrays.asList("Избраное", "Наличные деньги")),
	    Pair.of("Кошелек Масички", Arrays.asList("Избраное", "Наличные деньги")),
	    Pair.of("Norisbank (Top-Girokonto)", Arrays.asList("Избраное", "Безналичные деньги")),
	    Pair.of("Norisbank (Top-Zinskonto)", Arrays.asList("Безналичные деньги")));

    @Override
    public void migrate(Connection connection) throws Exception {
	JsonParser parser = Json.createParser(this.getClass().getResourceAsStream("V1_2__Accounts.json"));

	JsonObject rootJsonObject = parser.getObject();
	JsonArray accountsJsonArray = rootJsonObject.getJsonArray("accounts");

	try (PreparedStatement insertAccountStmt = connection.prepareStatement(INSERT_INTO_ACCOUNT_STAEMENT,
		new String[] { "id" });
		PreparedStatement insertTagStmt = connection.prepareStatement(INSERT_INTO_ACCOUNT_TAG_STAEMENT)) {
	    accountsJsonArray.forEach(accountJsonValue -> {
		JsonObject accountJsonObject = accountJsonValue.asJsonObject();
		Integer accountId = insertAccount(insertAccountStmt, accountJsonObject.getString("name"));
		accountJsonObject.getJsonArray("tags")
		    .forEach(accountTagValue -> insertAccountTag(insertTagStmt, accountId, accountTagValue.toString()));
	    });
	}
    }

    private void insertAccountTag(PreparedStatement stmt, Integer accountId, String accountTag) {
	try {
	    stmt.setInt(1, accountId);
	    stmt.setString(2, accountTag);
	    stmt.executeUpdate();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    private Integer insertAccount(PreparedStatement stmt, String name) {
	try {
	    stmt.setString(1, name);
	    stmt.executeUpdate();
	    ResultSet generatedKeys = stmt.getGeneratedKeys();
	    if (generatedKeys.next()) {
		return generatedKeys.getInt(1);
	    }
	    return null;
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

}
