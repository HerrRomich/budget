package com.hrrm.budget.application.account.providers;

import static graphql.Scalars.GraphQLID;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLList.list;
import static graphql.schema.GraphQLObjectType.newObject;
import static graphql.schema.GraphQLTypeReference.typeRef;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;
import com.hrrm.budget.application.account.service.NotFoundException;
import com.hrrm.budget.domain.account.AccountGroup;
import com.hrrm.budget.domain.account.repository.AccountGroupRepository;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLType;
import graphql.servlet.GraphQLProvider;
import graphql.servlet.GraphQLQueryProvider;
import graphql.servlet.GraphQLTypesProvider;

@Component(
    service = {GraphQLProvider.class, GraphQLQueryProvider.class, GraphQLTypesProvider.class},
    property = {"com.hrrm.graphql.provider=accounts", "name=AccountGroupProvider"})
public class AccountGroupProvider implements GraphQLQueryProvider, GraphQLTypesProvider {

  @Reference(target = "(name=AccountProvider)")
  private GraphQLTypesProvider accountProvider;

  @Reference
  private AccountGroupRepository accountGroupRepository;

  @Reference
  private LoggerFactory loggerFactory;

  private Collection<GraphQLFieldDefinition> queries;

  private Collection<GraphQLType> types;

  private Logger logger;

  @Activate
  public void activate() {
    logger = loggerFactory.getLogger(this.getClass());
    logger.info("Data provider initialization started.");
    initQueries();
    initTypes();
  }

  private void initQueries() {
    queries = Stream.of(buildAccountGroupDefinition()).collect(Collectors.toList());
  }

  private GraphQLFieldDefinition buildAccountGroupDefinition() {
    return newFieldDefinition().name("accountGroups").type(list(typeRef("AccountGroup")))
        .argument(newArgument().name("id").type(GraphQLID)).dataFetcher(this::fetchAccountGroupData)
        .build();
  }

  private List<AccountGroup> fetchAccountGroupData(DataFetchingEnvironment environment) {
    if (environment.containsArgument("id")) {
      Integer id = Integer.valueOf(environment.getArgument("id"));
      return accountGroupRepository.find(id).map(ag -> Stream.of(ag).collect(Collectors.toList()))
          .orElseThrow(() -> new NotFoundException());
    } else {
      return accountGroupRepository.findAll();
    }
  }

  private void initTypes() {
    types = Stream.concat(Stream.of(buildAccountGroupType()), accountProvider.getTypes().stream())
        .collect(Collectors.toSet());
  }

  private GraphQLType buildAccountGroupType() {
    return newObject().name("AccountGroup").field(newFieldDefinition().name("id").type(GraphQLID))
        .field(newFieldDefinition().name("name").type(GraphQLString))
        .field(newFieldDefinition().name("accounts").type(list(typeRef("Account")))).build();
  }

  @Override
  public Collection<GraphQLFieldDefinition> getQueries() {
    return queries;
  }

  @Override
  public Collection<GraphQLType> getTypes() {
    return types;
  }

}
