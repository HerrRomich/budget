package com.hrrm.infrastructure.web.internal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.http.context.ServletContextHelper;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

@Component(service = ServletContextHelper.class, property = {
		HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_NAME + "=" + WebContentContextHelper.WEB_APP_CONTEXT_NAME,
		HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_PATH + "=" + WebContentContextHelper.WEB_APP_CONTEXT }, scope = ServiceScope.BUNDLE)
public class WebContentContextHelper extends DelegatedServletContextHalper {

    public static final String WEB_APP_CONTEXT_NAME = "com.hrrm.budget.webapp";
    public static final String WEB_APP_CONTEXT = "/";

}
