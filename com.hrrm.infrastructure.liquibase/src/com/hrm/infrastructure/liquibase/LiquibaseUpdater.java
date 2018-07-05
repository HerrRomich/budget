package com.hrm.infrastructure.liquibase;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.ops4j.pax.jdbc.hook.PreHook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

@Component(service = PreHook.class, scope = ServiceScope.BUNDLE, property = {
		PreHook.KEY_NAME + "=liquibase" })
public class LiquibaseUpdater implements PreHook {

	@Override
	public void prepare(DataSource ds) throws SQLException {
		ds.getClass();
	}

}
