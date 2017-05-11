package com.junitpoc.logiclesstemplates.processors;

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

@Component
@Service
public class ExampleProcessor extends AbstractCheckComponentCategoryContextProcessor<TemplateContentModel> {

    Logger log = LoggerFactory.getLogger(ExampleProcessor.class);

    @Override
    public Set<String> anyOf() {
        return Sets.newHashSet("exampleprocessor-bootstrap");
    }

    @Override
    public int priority() {
        return LOW_PRIORITY;
    }

    @Override
    public void process(ExecutionContext executionContext, TemplateContentModel contentModel) throws ProcessException {

        log.info("******* Process Execute for ExamplePRocessor ********");

        Resource resource = (Resource)executionContext.get(JAHIA_RESOURCE);
        if( null != resource) {
            Object contentObject = contentModel.get("content");
            ((Map)contentObject).put("exampleText", "This is a text from a processor :)");
        }
    }
}