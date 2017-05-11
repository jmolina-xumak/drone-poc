package com.junitpoc.base.templatingsupport;


import layerx.jahia.templating.TemplateContentModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

/**
 * MockTemplateContentModel
 * <description>
 * -­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-‐
 * Change History
 * --------------------------------------------------------------------------------------
 * Version | Date       | Developer       | Changes
 * 1.0     | 05/09/2014 | JFlores         | Initial Creation
 * 1.0     | 17/11/2014 | Mario Rivas     | Adapt to Latam Commons
 * 1.1     | 10/05/5017 | Rainman Sián    | Adapted to LayerX + Jahia
 * --------------------------------------------------------------------------------------
 */

public class MockTemplateContentModel extends TemplateContentModel {

    public MockTemplateContentModel(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
        super(request, response);
    }
}
