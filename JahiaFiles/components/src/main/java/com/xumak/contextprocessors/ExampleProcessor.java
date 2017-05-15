package com.xumak.contextprocessors;

import layerx.core.contextprocessors.AbstractCheckComponentCategoryContextProcessor;
import layerx.jahia.templating.TemplateContentModel;
import com.google.common.collect.Sets;
import layerx.api.ExecutionContext;
import layerx.api.exceptions.ProcessException;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import org.jahia.services.render.Resource;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Map;
import java.util.Set;

import static layerx.jahia.Constants.JAHIA_RESOURCE;
import static layerx.Constants.LOW_PRIORITY;
import static layerx.Constants.CONTENT;
import static com.xumak.Constants.EXAMPLE_KEY;
import static com.xumak.Constants.CATEGORY_NAME_EXAMPLE;
import static com.xumak.Constants.EXAMPLE_TEXT_VALUE;


/**
 * This processor retrieve all the images path added by the author using the dialog of a component and stored it
 * in a Map Object.
 *
 * The key map returned is the same that the property name.
 * The value map returned is the image path selected by the user.
 *
 * Example map result.
 * componentMap= {
 *  Externalized accordingly: (default | live)
 *  image=/files/default/sites/marcosantil/files/triangulos_314x308.jpg,
 *  imagetwo=/files/default/sites/marcosantil/files/triangulos_759x308.jpg,
 *  imagethree=/files/default/sites/marcosantil/files/triangulos_750x314.jpg
 *  ...
 *  }
 *
 *  Example to display a specific image stored in the Map Object in a component.
 *  {% content.componentContext.image %}
 *
 *  The process to retrieve the image node path in the repository is to retrieve each id image node
 *  and with the jcr session it retrieve the node using the id node of each image and finally it used
 *  the node path of each node.
 *	-----------------------------------------------------------------------------
 * 	CHANGE	HISTORY
 *	-----------------------------------------------------------------------------
 *	Version	|	Date		|	Developer				|	Changes
 *	1.0		|	2016/10/31	|	Juan J. Pol				|	Initial	Creation
 *	-----------------------------------------------------------------------------
 * */



@Component
@Service
public class ExampleProcessor extends AbstractCheckComponentCategoryContextProcessor<TemplateContentModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleProcessor.class);

    @Override
    public Set<String> anyOf() {
        return Sets.newHashSet(CATEGORY_NAME_EXAMPLE);
    }

    @Override
    public int priority() {
        return LOW_PRIORITY;
    }

    @Override
    public void process(final ExecutionContext executionContext, final TemplateContentModel contentModel) throws ProcessException {

        LOGGER.info("Example processor started  >>>>");

        final Resource resource = (Resource) executionContext.get(JAHIA_RESOURCE);
        if (null != resource) {
            Object contentObject = contentModel.get(CONTENT);
            ((Map) contentObject).put(EXAMPLE_KEY, EXAMPLE_TEXT_VALUE);
        }
        LOGGER.info("Example processor finished >>>> \n");
    }
}
