package com.hrrm.budget.application.account.service;

import static graphql.schema.GraphQLObjectType.newObject;
import static graphql.schema.GraphQLSchema.newSchema;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;
import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.preparsed.NoOpPreparsedDocumentProvider;
import graphql.execution.preparsed.PreparsedDocumentProvider;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;
import graphql.servlet.DefaultExecutionStrategyProvider;
import graphql.servlet.DefaultGraphQLContextBuilder;
import graphql.servlet.DefaultGraphQLErrorHandler;
import graphql.servlet.DefaultGraphQLRootObjectBuilder;
import graphql.servlet.DefaultGraphQLSchemaProvider;
import graphql.servlet.ExecutionStrategyProvider;
import graphql.servlet.GraphQLContext;
import graphql.servlet.GraphQLContextBuilder;
import graphql.servlet.GraphQLErrorHandler;
import graphql.servlet.GraphQLProvider;
import graphql.servlet.GraphQLQueryProvider;
import graphql.servlet.GraphQLRootObjectBuilder;
import graphql.servlet.GraphQLSchemaProvider;
import graphql.servlet.GraphQLServlet;
import graphql.servlet.GraphQLTypesProvider;
import graphql.servlet.InstrumentationProvider;
import graphql.servlet.NoOpInstrumentationProvider;

@Component(scope = ServiceScope.PROTOTYPE, service = Servlet.class, property = {
	HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN +
	    "=/accounts/*",
	HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_SELECT +
	    "=(" +
	    HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_NAME +
	    "=com.hrrm.budget.webapp)" })
public class AccountGraphQlServlet extends GraphQLServlet {

    private static final long serialVersionUID = 1L;

    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policyOption = ReferencePolicyOption.GREEDY, target = "(com.hrrm.graphql.provider=accounts)")
    private List<GraphQLProvider> providers;

    @Reference
    private LoggerFactory loggerFactory;

    private GraphQLContextBuilder contextBuilder = new DefaultGraphQLContextBuilder();
    private ExecutionStrategyProvider executionStrategyProvider = new DefaultExecutionStrategyProvider();
    private InstrumentationProvider instrumentationProvider = new NoOpInstrumentationProvider();
    private PreparsedDocumentProvider preparsedDocumentProvider = NoOpPreparsedDocumentProvider.INSTANCE;
    private GraphQLErrorHandler errorHandler = new DefaultGraphQLErrorHandler();
    private GraphQLRootObjectBuilder rootObjectBuilder = new DefaultGraphQLRootObjectBuilder();
    private GraphQLSchemaProvider schemaProvider;

    private Logger logger;

    @Activate
    public void activate() {
	logger = loggerFactory.getLogger(this.getClass());
	logger.info("Activated");

	GraphQLObjectType.Builder accountsQueryTypeBuilder = newObject().name("AccountsQuery");
	providers.stream()
	    .filter(GraphQLQueryProvider.class::isInstance)
	    .map(GraphQLQueryProvider.class::cast)
	    .map(GraphQLQueryProvider::getQueries)
	    .flatMap(Collection::stream)
	    .forEach(accountsQueryTypeBuilder::field);
	Set<GraphQLType> types = providers.stream()
	    .filter(GraphQLTypesProvider.class::isInstance)
	    .map(GraphQLTypesProvider.class::cast)
	    .map(GraphQLTypesProvider::getTypes)
	    .flatMap(Collection::stream)
	    .collect(Collectors.toSet());
	schemaProvider = new DefaultGraphQLSchemaProvider(
		newSchema().query(accountsQueryTypeBuilder.build()).build(types));
    }

    @Override
    protected GraphQLSchemaProvider getSchemaProvider() {
	return schemaProvider;
    }

    @Override
    protected GraphQLContext createContext(Optional<HttpServletRequest> request,
	    Optional<HttpServletResponse> response) {
	return contextBuilder.build(request, response);
    }

    @Override
    protected Object createRootObject(Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
	return rootObjectBuilder.build(request, response);
    }

    @Override
    protected ExecutionStrategyProvider getExecutionStrategyProvider() {
	return executionStrategyProvider;
    }

    @Override
    protected Instrumentation getInstrumentation() {
	return instrumentationProvider.getInstrumentation();
    }

    @Override
    protected GraphQLErrorHandler getGraphQLErrorHandler() {
	return errorHandler;
    }

    @Override
    protected PreparsedDocumentProvider getPreparsedDocumentProvider() {
	return preparsedDocumentProvider;
    }
}
