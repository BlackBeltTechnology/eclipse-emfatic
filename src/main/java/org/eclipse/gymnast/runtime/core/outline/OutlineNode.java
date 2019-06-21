/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.gymnast.runtime.core.outline;

import org.eclipse.gymnast.runtime.core.ast.ASTNode;

import java.util.ArrayList;


/**
 * 
 * @author cjdaly@us.ibm.com
 */
public class OutlineNode {
	
	private ASTNode _astNode;
	
	private OutlineNode _parent;
	private ArrayList _children = new ArrayList();
	
	private String _text;

	//
	// Constructors
	//
	
	public OutlineNode(String text) {
		this(null, text);
	}

	public OutlineNode(ASTNode astNode, String text) {
		_astNode = astNode;
		_text = text;
	}
	
	//
	//
	//
	
	public ASTNode getASTNode() {
		return _astNode;
	}
	
	public OutlineNode getParent() {
		return _parent;
	}
	
	public boolean hasChildren() {
		return _children.size() > 0;
	}
	
	public OutlineNode[] getChildren() {
		return (OutlineNode[]) _children.toArray(new OutlineNode[_children.size()]);
	}
	
	public void addChild(OutlineNode node) {
		node._parent = this;
		_children.add(node);
	}
	
	public String getText() {
		return _text;
	}
	
}
