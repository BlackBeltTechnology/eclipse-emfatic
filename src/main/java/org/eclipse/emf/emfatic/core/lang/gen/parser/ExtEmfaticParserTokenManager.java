/**
 * @generated by Gymnast from Emfatic.ast on 09.02.2007 13:03:05
 */

package org.eclipse.emf.emfatic.core.lang.gen.parser;

public class ExtEmfaticParserTokenManager extends EmfaticParserTokenManager {

	private final ExtSimpleCharStream _stream;
	
	public ExtEmfaticParserTokenManager(ExtSimpleCharStream stream) {
		super(stream);
		_stream = stream;
	}

	protected Token jjFillToken() {
		ExtToken t = new ExtToken();
		t.kind = jjmatchedKind;
		String im = jjstrLiteralImages[jjmatchedKind];
		t.image = (im == null) ? input_stream.GetImage() : im;
		t.beginLine = input_stream.getBeginLine();
		t.beginColumn = input_stream.getBeginColumn();
		t.endLine = input_stream.getEndLine();
		t.endColumn = input_stream.getEndColumn();
		t.tokenOffset = _stream.tokenBeginOffset;
		return t;
	}

}
