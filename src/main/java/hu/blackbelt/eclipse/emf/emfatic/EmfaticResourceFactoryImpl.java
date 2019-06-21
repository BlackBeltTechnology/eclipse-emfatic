package hu.blackbelt.eclipse.emf.emfatic;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

public class EmfaticResourceFactoryImpl extends ResourceFactoryImpl {
    @Override
    public Resource createResource(URI uri) {
        return new EmfaticResourceImpl(uri);
    }
}

