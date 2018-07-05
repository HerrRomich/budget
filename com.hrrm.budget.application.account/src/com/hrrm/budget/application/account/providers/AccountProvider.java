package com.hrrm.budget.application.account.providers;

import static graphql.Scalars.GraphQLID;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LoggerFactory;
import com.hrrm.budget.domain.account.repository.AccountRepository;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLType;
import graphql.servlet.GraphQLProvider;
import graphql.servlet.GraphQLQueryProvider;
import graphql.servlet.GraphQLTypesProvider;

@Component(service = {GraphQLProvider.class, GraphQLQueryProvider.class, GraphQLTypesProvider.class},
    property = {"com.hrrm.graphql.provider=accounts", "name=AccountProvider"})
public class AccountProvider implements GraphQLQueryProvider, GraphQLTypesProvider {

  private static final String ACCOUNT_TYPE_NAME = "Account";

  @Reference
  private AccountRepository accountRepository;

  @Reference
  private LoggerFactory loggerFactory;

  private Collection<GraphQLType> types;

  @Activate
  public void activate() {
    initializeTypes();
  }

  private void initializeTypes() {
    types = Stream.of(buildAccountType()).collect(Collectors.toList());
  }

  private GraphQLType buildAccountType() {
    return newObject().name(getAccountTypeName()).field(newFieldDefinition().name("id").type(GraphQLID))
        .field(newFieldDefinition().name("name").type(GraphQLString)).build();
  }

  private String getAccountTypeName() {
    return ACCOUNT_TYPE_NAME;
  }

  @Override
  public Collection<GraphQLType> getTypes() {
    return types;
  }

  @Override
  public Collection<GraphQLFieldDefinition> getQueries() {
    return Collections.emptyList();
  }

}
