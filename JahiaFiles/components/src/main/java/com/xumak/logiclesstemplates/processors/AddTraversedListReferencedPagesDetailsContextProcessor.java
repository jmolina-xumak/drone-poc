package com.xumak.logiclesstemplates.processors;

import com.google.common.collect.Sets;
import com.xumak.utils.Utils;
import layerx.Constants;
import layerx.api.ExecutionContext;
import layerx.api.exceptions.ProcessException;
import layerx.core.contextprocessors.AbstractCheckComponentCategoryContextProcessor;
import layerx.jahia.templating.TemplateContentModel;
import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.jahia.services.content.JCRNodeIteratorWrapper;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.xumak.Constants.TRAVERSED_LIST_XK_CATEGORY;
import static com.xumak.utils.Utils.getPageProperties;
import static layerx.Constants.HIGH_PRIORITY;
import static layerx.Constants.RESOURCE_CONTENT_KEY;
import static layerx.jahia.Constants.*;
import static org.jahia.api.Constants.JAHIANT_PAGE;

/**
 * DESCRIPTION
 * Reads a (root path | currentPage) and construct an array with a configured level of pages
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
public class AddTraversedListReferencedPagesDetailsContextProcessor extends AbstractCheckComponentCategoryContextProcessor<TemplateContentModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddTraversedListReferencedPagesDetailsContextProcessor.class);
    private static final String XK_DEPTH_KEY = "xk_depth";
    private static final String ROOT_PATH_KEY = "rootPath";
    private static final String N_DEEP_PAGE_PROPERTIES_KEY = "pages";
    private static final String CURRENT_PAGE_PROPERTIES_KEY = "pageProps";

    @Override
    public Set<String> anyOf() { // TODO define | allOf() | noneOf() |
        return Sets.newHashSet(TRAVERSED_LIST_XK_CATEGORY);
    }

    @Override
    public void process(ExecutionContext executionContext, TemplateContentModel contentModel) throws ProcessException {
        Map<String, Object> contentObject = (Map<String, Object>) contentModel.get(RESOURCE_CONTENT_KEY);
        RenderContext renderContext = (RenderContext)executionContext.get(JAHIA_RENDER_CONTEXT);
        Resource mainResource = renderContext.getMainResource();
        Resource resource = (Resource) executionContext.get(JAHIA_RESOURCE);
        Map<String, Object> contentMap = Utils.getContentResource(contentModel);

        Map<String, Object> configMap = Utils.getResourceAsMap(contentModel, Constants.CONFIG_PROPERTIES_KEY);
        String rootPathUUID = contentMap.containsKey(ROOT_PATH_KEY) ? contentMap.get(ROOT_PATH_KEY).toString() : null;
        String depthStr = Utils.getConfigPropertyAsString(configMap, XK_DEPTH_KEY);
        int depth = StringUtils.isNotBlank(depthStr) ? Integer.valueOf(depthStr) : 0;
        Map<String, Object> pageProperties;
        List<Map<String, Object>> mapList = new ArrayList<>();

        try {
            JCRNodeWrapper nodeWrapperFromResource = resource.getNode();
            JCRSessionWrapper sessionWrapper = nodeWrapperFromResource.getSession();

            if (StringUtils.isNotBlank(rootPathUUID) && depth > 0) {
                JCRNodeWrapper nodeWrapper = sessionWrapper.getNodeByIdentifier(rootPathUUID);
                pageProperties = getPageProperties(nodeWrapper, sessionWrapper);
                mapList.add(pageProperties);
                List<Map<String, Object>> mapListTmp = getDeepPageProperties(sessionWrapper, nodeWrapper, depth);
                mapList.addAll(mapListTmp);
                contentObject.put(N_DEEP_PAGE_PROPERTIES_KEY, mapList);
            } else {
                pageProperties = getPageProperties(mainResource.getNode(), sessionWrapper);
                if (null != pageProperties) {
                    contentObject.put(CURRENT_PAGE_PROPERTIES_KEY, pageProperties);
                }
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    /**
     * Traverses a given root path down-up until a given deep level
     * @param jcrSessionWrapper
     * @param jcrNodeWrapper
     * @param depth
     * @return a list of maps
     * @throws RepositoryException
     */
    private List<Map<String, Object>>
    getDeepPageProperties(JCRSessionWrapper jcrSessionWrapper, JCRNodeWrapper jcrNodeWrapper, int depth)
            throws RepositoryException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        JCRNodeIteratorWrapper nodeIteratorWrapper = jcrNodeWrapper.getNodes();
        if (depth > 0) {
            while (null != nodeIteratorWrapper && nodeIteratorWrapper.hasNext()) {
                Node childPage = nodeIteratorWrapper.nextNode();
                if (!childPage.isNodeType(JAHIANT_PAGE)) continue;
                JCRNodeWrapper node = (JCRNodeWrapper) childPage;
                List<Map<String, Object>>
                        childProperties = getDeepPageProperties(jcrSessionWrapper, node, depth - 1);
                Map<String, Object> map = getPageProperties(node, jcrSessionWrapper);
                if (null != childProperties && childProperties.size() > 0) {
                    map.put(N_DEEP_PAGE_PROPERTIES_KEY, childProperties);
                }
                mapList.add(map);
            }
        }
        return mapList;
    }

    @Override
    public int priority() {
        return HIGH_PRIORITY; //TODO define the correct priority
    }

}