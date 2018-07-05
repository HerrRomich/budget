package com.hrrm.infrastructure.web.internal;

import java.io.IOException;
import java.net.URL;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

@Component(scope = ServiceScope.PROTOTYPE, service = Filter.class, property = {
	HttpWhiteboardConstants.HTTP_WHITEBOARD_FILTER_PATTERN + "=/*",
	HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_SELECT + "=("
		+ HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_NAME + "="
		+ WebContentContextHelper.WEB_APP_CONTEXT_NAME + ")",
	"service.ranking=100" })
public class SecureConnectionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	// TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	    throws IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest) req;
	HttpServletResponse response = (HttpServletResponse) res;
	if ("http".equals(request.getScheme())) {
	    String query = request.getQueryString() != null ? "?" + request.getQueryString() : "";
	    URL newUrl = new URL("https", request.getServerName(), 8443, request.getRequestURI() + query);
	    response.sendRedirect(newUrl.toString());
	    return;
	}
	chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
	// TODO Auto-generated method stub

    }

}
