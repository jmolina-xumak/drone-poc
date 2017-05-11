package com.junitpoc.logiclesstemplates.processors;

import com.junitpoc.base.configuration.MockLayerXConfiguration;
import com.junitpoc.base.configuration.MockLayerXConfigurationProvider;
import com.junitpoc.base.templatingsupport.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * MockLayerXConfigurationProvider
 * <description>
 * -­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-‐
 * Change History
 * --------------------------------------------------------------------------------------
 * Version | Date       | Developer       | Changes
 * 1.1     | 10/05/5017 | Rainman Sián    | Initial creation
 * --------------------------------------------------------------------------------------
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleProcessorTest {

    Logger log = LoggerFactory.getLogger(ExampleProcessorTest.class);

    @Mock
    public MockLayerXConfigurationProvider configurationProvider;

    @Mock
    public MockLayerXConfiguration configuration;

    @InjectMocks
    private ExampleProcessor exampleProcessor = new ExampleProcessor();

    private BaseTest baseTest;

    @Before
    public void setUp() throws Exception {
        baseTest = new BaseTest();
        baseTest.initializeConfiguration(configurationProvider, configuration);

        // config propertieres
        /* section to add required configurations */
    }

    @Test
    public void testExampleTextIsNotNull() throws Exception {
        exampleProcessor.process(baseTest.executionContext, baseTest.contentModel);

        final Object exampleTextValue = baseTest.contentModel.get("content.exampleText");
        assertNotNull(exampleTextValue);
    }

    @Test
    public void testExampleTextEquals() throws Exception{
        exampleProcessor.process(baseTest.executionContext, baseTest.contentModel);

        final String exampleTextValue = baseTest.contentModel.getAsString("content.exampleText");

        log.info("******* exampleTextValue: " + exampleTextValue);

        assertEquals("This is a text from a processor :)", exampleTextValue);
    }
}
