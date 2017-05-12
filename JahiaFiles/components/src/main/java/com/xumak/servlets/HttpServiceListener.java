package com.xumak.servlets;

import org.eclipse.gemini.blueprint.context.BundleContextAware;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;

/**
 * DESCRIPTION
 * This is a contextBundle test
 * For real uses - make sure to read corresponding documentation about it
 * -----------------------------------------------------------------------------
 * CHANGE	HISTORY
 * -----------------------------------------------------------------------------
 * Version	|	Date		|	Developer				|	Changes
 * 1.0		|	4/27/17 	|	isosa 				    |	Initial	Creation
 * -----------------------------------------------------------------------------
 */

public class HttpServiceListener implements BundleContextAware {

    public static final Logger logger = LoggerFactory.getLogger(HttpServiceListener.class);

    SimpleServlet simpleServlet;
    BundleContext bundleContext;

    public void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }

    public void setSimpleServlet(SimpleServlet simpleServlet) {
        this.simpleServlet = simpleServlet;
    }

    public void onBind(ServiceReference serviceReference) {
        // note : we don't use the passed service reference because it is a proxy class that we cannot use to retrieve the
        // real service object, so we simply look it up again
        final ServiceReference realServiceReference = bundleContext.getServiceReference(HttpService.class.getName());
        final HttpService httpService = (HttpService) bundleContext.getService(realServiceReference);
        try {
            httpService.registerServlet("/com.bootstrap.servlets", simpleServlet, null, null);
            logger.info("Successfully registered custom servlet at /modules/com.bootstrap.servlets");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (NamespaceException e) {
            e.printStackTrace();
        }

    }

    public void onUnbind(ServiceReference serviceReference) {
        // note : we don't use the passed service reference because it is a proxy class that we cannot use to retrieve the
        // real service object, so we simply look it up again
        // here we have a lot of null checks because in the case of a framework shutdown the service can disappear
        // at any time.
        final ServiceReference realServiceReference = bundleContext.getServiceReference(HttpService.class.getName());
        final HttpService httpService = (HttpService) bundleContext.getService(realServiceReference);

        if (serviceReference == null || realServiceReference == null || httpService == null) {
            return;
        }
        httpService.unregister("/com.bootstrap.servlets");
        logger.info("Successfully unregistered custom servlet from /modules/org.jahia.modules.samples.servlet.spring");
    }
}
