package org.eclipse.emf.emfatic.core.lang.gen.ast;

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

/**
 *
 * @generated by Gymnast from Emfatic.ast on 06.02.2007 17:57:34
 */
public class TypeWithMulti extends ResultType  {


    private BoundExceptWildcard _name;
    private Multiplicity _multiplicity;

    public BoundExceptWildcard getName() {
        return _name;
    }
    public Multiplicity getMultiplicity() {
        return _multiplicity;
    }


    /**
     * @return the number of children of this ASTNode
     */
    public int getChildCount() {
        int count = 0;
        if (_name != null) count++;
        if (_multiplicity != null) count++;

        return count;
    }

    /**
     * @param index the index of a child ASTNode to get
     * @return the child ASTNode at the given index
     * @throws IndexOutOfBoundsException when the index is out of bounds
     */
    public ASTNode getChild(int index) {
        int count = -1;
        if ((_name != null) && (++count == index)) return _name;
        if ((_multiplicity != null) && (++count == index)) return _multiplicity;

        throw new IndexOutOfBoundsException();
    }

    /**
     * Construct a new TypeWithMulti.
     */
    public TypeWithMulti(
        BoundExceptWildcard name,
        Multiplicity multiplicity
    ) {
        super();

        if (name != null) {
            _name = name;
            if (_name._parent != null) throw new RuntimeException();
            _name._parent = this;
        }
        if (multiplicity != null) {
            _multiplicity = multiplicity;
            if (_multiplicity._parent != null) throw new RuntimeException();
            _multiplicity._parent = this;
        }

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
