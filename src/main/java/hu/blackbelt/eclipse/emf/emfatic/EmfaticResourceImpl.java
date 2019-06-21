package hu.blackbelt.eclipse.emf.emfatic;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.emfatic.core.generator.ecore.Builder;
import org.eclipse.emf.emfatic.core.lang.gen.parser.EmfaticParserDriver;
import org.eclipse.gymnast.runtime.core.parser.ParseContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class EmfaticResourceImpl extends ResourceImpl  {
	
	public EmfaticResourceImpl() {
	}

	public EmfaticResourceImpl(URI uri) {
		this();
		this.uri = uri;
	}

	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		EmfaticParserDriver parser = new EmfaticParserDriver(getURI());
		ParseContext parseContext = parser.parse(reader);
		Builder builder = new Builder();
		builder.build(parseContext, this);

		if (parseContext.getErrorCount() > 0) {
			String message = parseContext.getMessages()[0].getMessage();
			message = message.replaceAll("\\r|\\n", " ");
			message = message.trim();
			throw new IOException("Syntax error: " + message);
		}
	}

	/*
	public Resource loadEmfatic(ResourceSet resourceSet, URI emfaticUri, URI ecoreUri, boolean writeEcore) throws Exception {
		Resource resource = resourceSet.createResource(ecoreUri);

		InputStream inputStream = null;
		for (URIHandler uriHandler : resourceSet.getURIConverter().getURIHandlers()) {
			if (uriHandler.canHandle(emfaticUri)) {
				inputStream = uriHandler.createInputStream(emfaticUri, ImmutableMap.of());
			}
		}

		if (inputStream == null) {
			throw new Exception("Could not load from URI: " + emfaticUri);
		}

		NullProgressMonitor monitor = new NullProgressMonitor();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		EmfaticParserDriver parser = new EmfaticParserDriver(emfaticUri);
		ParseContext parseContext = parser.parse(reader);
		Builder builder = new Builder();
		builder.build(parseContext, resource, monitor);

		if (!parseContext.hasErrors() && writeEcore) {
			Connector connector = new Connector(builder);
			connector.connect(parseContext, resource, monitor);
			resource.save(null);
		} else {
			String message = parseContext.getMessages()[0].getMessage();
			message = message.replaceAll("\\r|\\n", " ");
			message = message.trim();
			throw new Exception("Syntax error: " + message);
		}
	} */
	
}
