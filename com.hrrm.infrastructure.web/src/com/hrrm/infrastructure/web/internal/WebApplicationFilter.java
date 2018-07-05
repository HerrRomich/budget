package com.hrrm.infrastructure.web.internal;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

@Component(scope = ServiceScope.PROTOTYPE, service = Filter.class, property = {
	HttpWhiteboardConstants.HTTP_WHITEBOARD_FILTER_PATTERN + "=/*",
	HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_SELECT + "=("
		+ HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_NAME + "="
		+ WebContentContextHelper.WEB_APP_CONTEXT_NAME + ")", "service.ranking=10" })
public class WebApplicationFilter implements Filter {

    private static final String INDEX_HTML = "/index.html";
    private static final String USER_ROLE_SESSION_ATTRIBUTE = "com.ebase.eox.userrole";
    public static final String USER_ROLE = "eox";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	    throws IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest) req;
	HttpServletResponse response = (HttpServletResponse) res;

	if (StringUtils.isEmpty(request.getRequestURI()) ||
		"/".equals(request.getRequestURI())) {
	    RequestDispatcher requestDispatcherToIndexHtml = request.getRequestDispatcher(INDEX_HTML);
	    requestDispatcherToIndexHtml.forward(request, response);
	    return;
	}

	// Boolean isFirstCall = (Boolean)
	// request.getAttribute(FIRST_CALL_REQUEST_ATTR);
	// if (isFirstCall != null && !isFirstCall)
	// return;
	// request.setAttribute(FIRST_CALL_REQUEST_ATTR, true);

	HttpSession session = request.getSession(false);
	if (session == null) {
	    session = request.getSession();
	}
	String userRole = (String) session.getAttribute(USER_ROLE_SESSION_ATTRIBUTE);
	if (!USER_ROLE.equals(userRole)) {
	    request.logout();
	    session.setAttribute(USER_ROLE_SESSION_ATTRIBUTE, USER_ROLE);
	}

	chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
	// No need to initialise
    }

    @Override
    public void destroy() {
	// Nothing to destroy
    }

}
