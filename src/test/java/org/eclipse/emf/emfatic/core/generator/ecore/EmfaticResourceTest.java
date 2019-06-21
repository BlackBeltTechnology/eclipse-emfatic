package org.eclipse.emf.emfatic.core.generator.ecore;

import hu.blackbelt.eclipse.emf.emfatic.EcoreEmfaticWriter;
import hu.blackbelt.eclipse.emf.emfatic.EmfaticResourceFactoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
class EmfaticResourceTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testEmfaticResourceLoad() {
        final ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("emf", new EmfaticResourceFactoryImpl());
        final File rdbmsEmfatic = new File("target/test-classes/rdbms.emf");
        final Resource resource = resourceSet.getResource(URI.createFileURI(rdbmsEmfatic.getAbsolutePath()), true);

        if (!resource.getErrors().isEmpty()) {
            final StringBuilder sb = new StringBuilder();
            for (final Resource.Diagnostic diagnostic : resource.getErrors()) {
                sb.append(diagnostic.getMessage()).append("\n");
            }
            fail(sb.toString());
        }


        final EClass rdbmsClass = (EClass) resourceSet.getResources().get(0).getEObject("//RdbmsField");
        assertEquals(rdbmsClass.getName(), "RdbmsField");

        EcoreEmfaticWriter ecoreEmfaticWriter = new EcoreEmfaticWriter();
        log.info(ecoreEmfaticWriter.write(resource));
    }


    @Test
    public void testEcorResourceWrite() {
        final ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
        final File rdbmsEmfatic = new File("target/test-classes/rdbms.ecore");
        final Resource resource = resourceSet.getResource(URI.createFileURI(rdbmsEmfatic.getAbsolutePath()), true);

        if (!resource.getErrors().isEmpty()) {
            final StringBuilder sb = new StringBuilder();
            for (final Resource.Diagnostic diagnostic : resource.getErrors()) {
                sb.append(diagnostic.getMessage()).append("\n");
            }
            fail(sb.toString());
        }


        final EClass rdbmsClass = (EClass) resourceSet.getResources().get(0).getEObject("//RdbmsField");
        assertEquals(rdbmsClass.getName(), "RdbmsField");

        EcoreEmfaticWriter ecoreEmfaticWriter = new EcoreEmfaticWriter();
        log.info(ecoreEmfaticWriter.write(resource));
    }

}