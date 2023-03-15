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

/*-
 * #%L
 * Eclipse :: Emfatic
 * %%
 * Copyright (C) 2018 - 2023 BlackBelt Technology
 * %%
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is
 * available at https://www.gnu.org/software/classpath/license.html.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 * #L%
 */

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
