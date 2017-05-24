package com.xumak.utils;

import layerx.jahia.templating.TemplateContentModel;
import org.apache.commons.lang3.StringUtils;
import org.jahia.services.content.JCRItemWrapper;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import java.util.HashMap;
import java.util.Map;

import static layerx.Constants.RESOURCE_CONTENT_KEY;
import static layerx.jahia.Constants.NODE_COLON_SEPARATOR;
import static org.jahia.api.Constants.JCR_PATH;
import static org.jahia.api.Constants.JCR_UUID;

/**
 * DESCRIPTION
 * Utility methods that can be used through th whole project
 * -----------------------------------------------------------------------------
 * CHANGE	HISTORY
 * -----------------------------------------------------------------------------
 * Version	|	Date		|	Developer				|	Changes
 * 1.0		|	5/15/17 	|	isosa 				    |	Initial	Creation
 * -----------------------------------------------------------------------------
 */
public class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    /**
     * This method is used to retrieve the content resource from the current content execution.
     * returning a Map Object with all properties/values from this component context.
     *
     */
    public static Map<String, Object> getContentResource (final TemplateContentModel contentModel){

        Map<String, Object> contentMap = null;
        if (contentModel.has(RESOURCE_CONTENT_KEY)) {
            Object contentObject = contentModel.get(RESOURCE_CONTENT_KEY);
            contentMap = (contentObject instanceof  Map) ? (Map) contentObject : contentMap ;
        }
        return contentMap;
    }

    /**
     * This method is used to retrieve resource in the content model according to resourceKey parameter; the content
     * retrieved is returned as a map object.
     * @param contentModel    The object that contains the whole content in the content model.
     * @param resourceKey     The resource key that will retrieve in the content model.
     * @author mcali
     * */
    public static Map<String, Object> getResourceAsMap (final TemplateContentModel contentModel, final String resourceKey){

        Map<String, Object> contentMap = null;

        if (contentModel.has(resourceKey)) {

            Object contentObject = contentModel.get(resourceKey);
            contentMap = (contentObject instanceof  Map) ? (Map) contentObject : contentMap ;
        }

        return contentMap;
    }

    /**
     * This method is used to look for a key in the xk-config.json and return it as a simple string.
     * @param configMap       The object that contains the whole configuration set in the xk-config.json file.
     * @param propertyName    The resource key that will retrieve in the configMap.
     * @author mcali
     */
    public static String getConfigPropertyAsString(final Map configMap, final String propertyName){
        String property = "";
        try {
            if (configMap.containsKey(propertyName)){
                property = configMap.get(propertyName).toString();
            }
        } catch (Exception e) {
            LOGGER.error("Error accessing the configuration Object: " + e);
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

    /**
     *
     * Retrieves the properties of a given page node
     * @param node
     * @param jcrSessionWrapper
     * @return a map of all properties
     * @throws RepositoryException
     */
    public static Map<String, Object>
    getPageProperties(final JCRNodeWrapper node, final JCRSessionWrapper jcrSessionWrapper) throws RepositoryException {
        Map<String, Object> map = new HashMap();

        if (null != node) {
            PropertyIterator propertyIterator = node.getProperties();

            while (null != propertyIterator && propertyIterator.hasNext()) {
                javax.jcr.Property property = propertyIterator.nextProperty();
                if (property.isMultiple()) continue;

                String propertyStr = property.getValue().getString();
                String name = property.getName();

                if (name.equals(JCR_UUID)) {
                    propertyStr = getResourceNodePath(jcrSessionWrapper, propertyStr);
                    name = JCR_PATH;
                }

                if (StringUtils.isNotBlank(name) && name.contains(NODE_COLON_SEPARATOR)) {
                    name = escapeColonChar(name);
                }
                map.put(name, propertyStr);
            }
        }
        return map;
    }

    /**
     * Escapes the ':' e.g. j:templateName by jTemplateName
     * @param property
     * @return a string without ':' character
     */
    public static String escapeColonChar(final String property){
        String name = property;
        int indexToUpperCase = name.indexOf(NODE_COLON_SEPARATOR) + 1;
        String charToReplace = name.substring(indexToUpperCase, indexToUpperCase + 1);
        String charInUpperCase = charToReplace.toUpperCase();
        name = name.replaceFirst(charToReplace, charInUpperCase);
        name = name.replace(NODE_COLON_SEPARATOR, "");
        return name;
    }     

}
