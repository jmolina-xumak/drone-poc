package com.xumak.utils;

import layerx.jahia.templating.TemplateContentModel;
import org.apache.commons.lang3.StringUtils;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import java.util.List;
import java.util.Map;

/**
 *  DESCRIPTION
 *  -------------------------------------------------------------------------------------------------------------------
 *  This Utils class contains generics methods that are used in XumaK.com project.
 *
 *	-------------------------------------------------------------------------------------------------------------------
 * 	CHANGE	HISTORY
 *	-------------------------------------------------------------------------------------------------------------------
 *	Version	|	Date		|	Developer				|	Changes
 *	1.0		|	05/18/2017	|	Marco Cali  			|	Initial	Creation
 *	-------------------------------------------------------------------------------------------------------------------
 */

public class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    /**
     * This method is used to retrieve resource in the content model according to resourceKey parameter; the content
     * retrieved is returned as a map object.
     * @param contentModel    The object that contains the whole content in the content model.
     * @param resourceKey     The resource key that will retrieve in the content model.
     * @author mcali
     * */
    public static Map<String, Object> getResourceAsMap(final TemplateContentModel contentModel, final String resourceKey){

        Map<String, Object> contentMap = null;

        if (contentModel.has(resourceKey)) {

            Object contentObject = contentModel.get(resourceKey);
            contentMap = (contentObject instanceof  Map) ? (Map) contentObject : contentMap ;
        }

        return contentMap;
    }

    /**
     * This method is used to look for a key in the xk-config.json and return it as a list of strings.
     * @param configMap    The object that contains the whole configuration set in the xk-config.json file.
     * @param propArray    The resource key that will retrieve in the configMap and that resource key would be set
     *                     as a list of strings in the xk-config.json file.
     * @author mcali
     * */
    public static List<String> getConfigPropertyAsList(final Map configMap, final String propArray){
        List<String> listProperties = null;
        try {
            if (configMap.containsKey(propArray)){
                listProperties = (List<String>)configMap.get(propArray);
            }
        } catch (ClassCastException e) {
            LOGGER.error("Error accessing the configuration object: " + e);
        }
        return listProperties;
    }

    /**
     * This method is used to look for a key in the xk-config.json and return it as a simple string.
     * @param configMap       The object that contains the whole configuration set in the xk-config.json file.
     * @param propertyName    The resource key that will retrieve in the configMap.
     * @author mcali
     * */
    public static String getConfigPropertyAsString(final Map configMap, final String propertyName){
        String property = "";
        if (configMap.containsKey(propertyName)){
            property = configMap.get(propertyName).toString();
        }
        return property;
    }

    /**
     * This method is used to return the path of the resource using JCRNodeWrapper to get the path of the resource
     * according to the different workspace tha Jahia has.
     * @param session             The object to get the node from its UUID.
     * @param resourceNodeUUID    The UUID of the resource.
     * @author mcali
     * */
    public static String getResourceNodePath(final JCRSessionWrapper session, final String resourceNodeUUID) {
        String resourcePath = "";
        try {
            if (StringUtils.isNotEmpty(resourceNodeUUID)) {
                JCRNodeWrapper resourceNode = session.getNodeByIdentifier(resourceNodeUUID);
                resourcePath = resourceNode.getUrl();
            }
        } catch (RepositoryException repException) {
            LOGGER.error("An error occurred in the repository " + repException.toString());
        }
        return resourcePath;
    }
}
