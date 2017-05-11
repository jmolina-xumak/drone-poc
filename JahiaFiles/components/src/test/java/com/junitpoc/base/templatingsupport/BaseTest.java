package com.junitpoc.base.templatingsupport;

import com.junitpoc.base.configuration.MockLayerXConfiguration;
import com.junitpoc.base.configuration.MockLayerXConfigurationProvider;
import layerx.api.ExecutionContext;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.jahia.services.render.Resource;
import org.mockito.Mockito;

import javax.jcr.Node;
import java.util.HashMap;
import java.util.Map;

import static layerx.Constants.*;
import static layerx.jahia.Constants.JAHIA_RESOURCE;
import static org.mockito.Mockito.when;

/**
 * BaseTest
 * Helps to configure all the objects needed as minimum in a test.
 * It includes:
 *      - Request
 *      - Response
 *      - Resource
 *      - Resource Resolver
 *      - Content Model
 *          * Config
 *          * Content
 *          * Design
 *          * Global
 *      - LayerX Configuration Provider
 *      - LayerX Configuration
 * -­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-‐
 * Change History
 * --------------------------------------------------------------------------------------
 * Version | Date       | Developer       | Changes
 * 1.0     | 17/02/2015 | Lesly Quiñonez  | Initial Creation
 * 1.0     | 11/04/216  | Isaías Sosa     | Added Node mock object
 * 1.1     | 10/05/5017 | Rainman Sián    | Adapted to LayerX + Jahia
 * --------------------------------------------------------------------------------------
 */

public class BaseTest {
    public MockTemplateContentModel contentModel;
    public String resourceType = JAHIA_RESOURCE;

    public ExecutionContext executionContext;
    public SlingHttpServletRequest request;
    public SlingHttpServletResponse response;
    public ResourceResolver resourceResolver;
//    public Resource resource;
    public Resource resource;
    public Node node;

    public Map<String, Object> config;
    public Map<String, Object> content;
    public Map<String, Object> design;
    public Map<String, Object> global;
    public Map<String, Object> page;

    public void initializeConfiguration(final MockLayerXConfigurationProvider configurationProvider,
                                        final MockLayerXConfiguration configuration) throws Exception {
        // Init Objects
        initializeConfiguration();
        when(configurationProvider.getFor(JAHIA_RESOURCE)).thenReturn(configuration);
    }

    public void initializeConfiguration() {
        // Init Objects
        executionContext = Mockito.mock(ExecutionContext.class);
        request = Mockito.mock(SlingHttpServletRequest.class);
        response = Mockito.mock(SlingHttpServletResponse.class);
        resource = Mockito.mock(Resource.class);
  ///      resource = Mockito.mock(Resource.class);
        node = Mockito.mock(Node.class);
        resourceResolver = Mockito.mock(ResourceResolver.class);
        contentModel = new MockTemplateContentModel(request, response);
        config = new HashMap<>();
        content = new HashMap<>();
        global = new HashMap<>();
        design = new HashMap<>();
        page = new HashMap<>();


        when(executionContext.get(JAHIA_RESOURCE)).thenReturn(resource);

        //Training
//        when(request.getResource()).thenReturn(resource);
        when(request.getResourceResolver()).thenReturn(resourceResolver);
 //       when(resource.getResourceResolver()).thenReturn(resourceResolver);
  //      when(resource.getResourceType()).thenReturn(resourceType);
        when(request.getAttribute(TEMPLATE_CONTENT_MODEL_ATTR_NAME))
                .thenReturn(contentModel);

        //Setting up the content model
        contentModel.set(DESIGN_PROPERTIES_KEY, design);
        contentModel.set(GLOBAL_PROPERTIES_KEY, global);
        contentModel.set(RESOURCE_CONTENT_KEY, content);
        contentModel.set(CONFIG_PROPERTIES_KEY, config);
//        contentModel.set(GLOBAL_PAGE_CONTENT_KEY, page);  //TODO is it valid in Jahia?
    }

    public void setResourceType(final String resourceType) {
        this.resourceType = resourceType;
    }
}
