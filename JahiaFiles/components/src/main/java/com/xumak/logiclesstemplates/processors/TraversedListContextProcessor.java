package com.xumak.logiclesstemplates.processors;

import com.google.common.collect.Sets;
import com.xumak.utils.Utils;
import layerx.Constants;
import layerx.api.ExecutionContext;
import layerx.api.exceptions.ProcessException;
import layerx.core.contextprocessors.AbstractCheckComponentCategoryContextProcessor;
import layerx.jahia.templating.TemplateContentModel;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.jahia.services.render.Resource;
/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
*/

import java.util.Map;
import java.util.Set;

import static com.xumak.Constants.TRAVERSED_LIST_XK_CATEGORY;
import static layerx.Constants.HIGH_PRIORITY;
import static layerx.jahia.Constants.JAHIA_RESOURCE;

/**
 * DESCRIPTION
 * Reads a root path and construct an array with a configured level of pages
 * containing all page properties
 *
 * How to use it:
 * it is executed by        'traversedList'
 * depth to traverse        'xk_depth' is 1 based
 * 'xk_depth' can be        >= 1 | 0 | ø
 * -----------------------------------------------------------------------------
 * CHANGE	HISTORY
 * -----------------------------------------------------------------------------
 * Version	|	Date		|	Developer				|	Changes
 * 1.0		|	5/18/17 	|	isosa 				    |	Initial	Creation
 * -----------------------------------------------------------------------------
 */

@Component
@Service
public class TraversedListContextProcessor extends AbstractCheckComponentCategoryContextProcessor<TemplateContentModel> {

    //private static final Logger LOGGER = LoggerFactory.getLogger(TraversedListContextProcessor.class);
    private static final String XK_DEPTH_KEY = "xk_depth";
    private static final String ROOT_PATH_KEY = "rootPath";

    @Override
    public Set<String> anyOf() { // TODO define | allOf() | noneOf() |
        return Sets.newHashSet(TRAVERSED_LIST_XK_CATEGORY);
    }

    @Override
    public void process(ExecutionContext executionContext, TemplateContentModel contentModel) throws ProcessException {
        Resource resource = (Resource) executionContext.get(JAHIA_RESOURCE);
        Map<String, Object> contentMap = Utils.getContentResource(contentModel);
        Map<String, Object> configMap = Utils.getResourceAsMap(contentModel, Constants.CONFIG_PROPERTIES_KEY);
        String depth = Utils.getConfigPropertyAsString(configMap, XK_DEPTH_KEY);

        if (null != contentMap) {
            if (configMap.containsKey(ROOT_PATH_KEY)) {
                String rootPath = configMap.get(ROOT_PATH_KEY).toString();

            }
        }
    }

    @Override
    public int priority() {
        return HIGH_PRIORITY; //TODO define the correct priority
    }

}