package org.eclipse.emf.emfatic.core.lang.gen.ast;

import org.eclipse.gymnast.runtime.core.ast.ASTNode;
import org.eclipse.gymnast.runtime.core.ast.ASTNodeImpl;
import org.eclipse.gymnast.runtime.core.ast.TokenInfo;

/**
 * The superclass of all ASTNodes for language Emfatic.
 *
 * @generated by Gymnast from Emfatic.ast on 06.02.2007 17:57:34
 */
public abstract class EmfaticASTNode extends ASTNodeImpl {

	protected EmfaticASTNode _parent;

	/**
	 * @return the parent of this ASTNode or null if this is the root node of a tree
	 */
	public ASTNode getParent() {
		return _parent;
	}

	/**
	 * Construct a new EmfaticASTNode.
	 */
	public EmfaticASTNode() {
		super();
	}

	/**
	 * Construct a new EmfaticASTNode.
	 *
	 * @param token a Token to initialize the offset and text for this node.
	 */
	public EmfaticASTNode(TokenInfo tokenInfo) {
		super(tokenInfo);
	}

	/**
	 * The external entry point used to initiate the visitor on this node.
	 * 
	 * @param visitor the Visitor to visit this node tree
	 */
	public final void accept(EmfaticASTNodeVisitor visitor) {
		visitor.preVisit(this);
		acceptImpl(visitor);
		visitor.postVisit(this);
	}

	/**
	 * This method can be overridden by subclasses which should provide exactly
	 * the same implementation.  Here <code>this</code> refers to the generic node
	 * class, but in the subclass implementations <code>this</code> will refer to
	 * the specific subclass type.  Thus the correct specific <code>beginVisit</code>
	 * and <code>endVisit</code> methods will be invoked for each subclass and the
	 * generic methods will be invoked for subclasses that don't need specific visitor
	 * behavior.
	 */
	public void acceptImpl(EmfaticASTNodeVisitor visitor) {
		boolean visitChildren = visitor.beginVisit(this);
		if (visitChildren) visitChildren(visitor);
		visitor.endVisit(this);
	}

	/**
	 * Iterate through the children of this node and accept the visitor on each.
	 */
	protected void visitChildren(EmfaticASTNodeVisitor visitor) {
		for (int i = 0; i < getChildCount(); i++) {
			ASTNode child = getChild(i);
			if (child instanceof EmfaticASTNode) {
				EmfaticASTNode c = (EmfaticASTNode)child;
				c.accept(visitor);
			}
		}
	}

}
