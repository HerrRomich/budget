package com.hrrm.budget.domain.account.repository.impl;

import java.sql.SQLException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.ops4j.pax.jdbc.pool.common.PooledDataSourceFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jdbc.DataSourceFactory;
import org.osgi.service.transaction.control.TransactionControl;
import org.osgi.service.transaction.control.jpa.JPAEntityManagerProviderFactory;

@Component
public class EntityMnagerProvider {

    @Reference
    private JPAEntityManagerProviderFactory jpaPproviderFactory;
    
    @Reference(target = "(&(xa=true)(pool=aries))")
    private PooledDataSourceFactory pdsf;
    
    @Reference(target = "(osgi.jdbc.driver.name=mariadb)")
    private DataSourceFactory dsf;
    
    @Reference(target = "(osgi.unit.name=accounts)")
    private EntityManagerFactory emf;
    
    @Reference
    private TransactionControl txControl;

    private ServiceRegistration<EntityManager> entityManagerRegistration;

    @Activate
    public void activate(BundleContext context) throws SQLException {
	Properties config = new Properties();
	config.put("url", "jdbc:mariadb://localhost:3306/budget");
	config.put("user", "root");
	config.put("password", "welcome1");
	DataSource ds = pdsf.create(dsf, config);
	
	Map<String, Object> jpaProperties = new HashMap<>();
	jpaProperties.put("javax.persistence.nonJtaDataSource", ds);
	EntityManager entityManager = jpaPproviderFactory.getProviderFor(emf, jpaProperties).getResource(txControl);
	
	Dictionary<String, String> props = new Hashtable<>();
	props.put("name", "accounts");
	entityManagerRegistration = context.registerService(EntityManager.class, entityManager, props);
    }
    
    @Deactivate
    public void deactivate() {
	entityManagerRegistration.unregister();
    }
    
}
