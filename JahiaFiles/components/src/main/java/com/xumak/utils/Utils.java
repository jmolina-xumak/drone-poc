package com.xumak.utils;

import layerx.jahia.templating.TemplateContentModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static layerx.Constants.RESOURCE_CONTENT_KEY;

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

}
