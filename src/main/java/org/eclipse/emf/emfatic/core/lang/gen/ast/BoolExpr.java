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
public class BoolExpr extends DefaultValueExpr  {


    private TrueOrFalse _trueOrFalse;

    public TrueOrFalse getTrueOrFalse() {
        return _trueOrFalse;
    }


    /**
     * @return the number of children of this ASTNode
     */
    public int getChildCount() {
        int count = 0;
        if (_trueOrFalse != null) count++;

        return count;
    }

    /**
     * @param index the index of a child ASTNode to get
     * @return the child ASTNode at the given index
     * @throws IndexOutOfBoundsException when the index is out of bounds
     */
    public ASTNode getChild(int index) {
        int count = -1;
        if ((_trueOrFalse != null) && (++count == index)) return _trueOrFalse;

        throw new IndexOutOfBoundsException();
    }

    /**
     * Construct a new BoolExpr.
     */
    public BoolExpr(
        TrueOrFalse trueOrFalse
    ) {
        super();

        if (trueOrFalse != null) {
            _trueOrFalse = trueOrFalse;
            if (_trueOrFalse._parent != null) throw new RuntimeException();
            _trueOrFalse._parent = this;
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
