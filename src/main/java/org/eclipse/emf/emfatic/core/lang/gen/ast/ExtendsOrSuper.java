package org.eclipse.emf.emfatic.core.lang.gen.ast;

import org.eclipse.gymnast.runtime.core.ast.TokenInfo;

/**
 *
 * @generated by Gymnast from Emfatic.ast on 06.02.2007 17:57:34
 */
public class ExtendsOrSuper extends EmfaticTokenNode {

	public static final String KW_EXTENDS = "extends";
	public static final String KW_SUPER = "super";

	
	/**
	 * Construct a new ExtendsOrSuper.
	 */
	public ExtendsOrSuper(TokenInfo tokenInfo) {
		super(tokenInfo);
	}

	/**
	 * This method overrides the superclass <code>acceptImpl</code> providing
	 * the same implementation.  Here <code>this</code> refers to this specific node
	 * class, so the <code>beginVisit</code> and <code>endVisit</code> methods
	 * specific to this type in the visitor will be invoked.
	 */
	public void acceptImpl(EmfaticASTNodeVisitor visitor) {
		boolean visitChildren = visitor.beginVisit(this);
		if (visitChildren) visitChildren(visitor);
		visitor.endVisit(this);
	}

}