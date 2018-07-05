package com.hrrm.budget.domain.account.migrations;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.ops4j.pax.jdbc.hook.PreHook;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.wiring.BundleWiring;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(service = PreHook.class, property = {"name=accounts"})
public class AccountsMigration implements PreHook {

  private BundleContext context;

  @Activate
  public void activate(BundleContext context) {
    this.context = context;
  }

  @Override
  public void prepare(DataSource ds) throws SQLException {
    Bundle bundle = context.getBundle();
    ClassLoader classLoader = bundle.adapt(BundleWiring.class).getClassLoader();
    Flyway flyway = new Flyway(classLoader);
    flyway.setDataSource(ds);
    String locations = "classpath:com/hrrm/budget/domain/account/migrations";
    String[] locationArray = locations.split(",");
    flyway.setLocations(locationArray);
    flyway.migrate();
  }

}
