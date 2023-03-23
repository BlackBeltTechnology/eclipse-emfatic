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
import org.eclipse.gymnast.runtime.core.ast.TokenInfo;

/**
 *
 * @generated by Gymnast from Emfatic.ast on 06.02.2007 17:57:34
 */
public class EnumLiteral extends EmfaticASTNode  {


    private Annotations _leadingAnnotations;
    private EmfaticTokenNode _name;
    private EmfaticTokenNode _equals;
    private EmfaticTokenNode _val;
    private Annotations _trailingAnnotations;
    private EmfaticTokenNode _semi;

    public Annotations getLeadingAnnotations() {
        return _leadingAnnotations;
    }
    public EmfaticTokenNode getName() {
        return _name;
    }
    public EmfaticTokenNode getEquals() {
        return _equals;
    }
    public EmfaticTokenNode getVal() {
        return _val;
    }
    public Annotations getTrailingAnnotations() {
        return _trailingAnnotations;
    }
    public EmfaticTokenNode getSemi() {
        return _semi;
    }


    /**
     * @return the number of children of this ASTNode
     */
    public int getChildCount() {
        int count = 0;
        if (_leadingAnnotations != null) count++;
        if (_name != null) count++;
        if (_equals != null) count++;
        if (_val != null) count++;
        if (_trailingAnnotations != null) count++;
        if (_semi != null) count++;

        return count;
    }

    /**
     * @param index the index of a child ASTNode to get
     * @return the child ASTNode at the given index
     * @throws IndexOutOfBoundsException when the index is out of bounds
     */
    public ASTNode getChild(int index) {
        int count = -1;
        if ((_leadingAnnotations != null) && (++count == index)) return _leadingAnnotations;
        if ((_name != null) && (++count == index)) return _name;
        if ((_equals != null) && (++count == index)) return _equals;
        if ((_val != null) && (++count == index)) return _val;
        if ((_trailingAnnotations != null) && (++count == index)) return _trailingAnnotations;
        if ((_semi != null) && (++count == index)) return _semi;

        throw new IndexOutOfBoundsException();
    }

    /**
     * Construct a new EnumLiteral.
     */
    public EnumLiteral(
        Annotations leadingAnnotations,
        TokenInfo name,
        TokenInfo equals,
        TokenInfo val,
        Annotations trailingAnnotations,
        TokenInfo semi
    ) {
        super();

        if (leadingAnnotations != null) {
            _leadingAnnotations = leadingAnnotations;
            if (_leadingAnnotations._parent != null) throw new RuntimeException();
            _leadingAnnotations._parent = this;
        }
        if (name != null) {
            _name = new EmfaticTokenNode(name);
            if (_name._parent != null) throw new RuntimeException();
            _name._parent = this;
        }
        if (equals != null) {
            _equals = new EmfaticTokenNode(equals);
            if (_equals._parent != null) throw new RuntimeException();
            _equals._parent = this;
        }
        if (val != null) {
            _val = new EmfaticTokenNode(val);
            if (_val._parent != null) throw new RuntimeException();
            _val._parent = this;
        }
        if (trailingAnnotations != null) {
            _trailingAnnotations = trailingAnnotations;
            if (_trailingAnnotations._parent != null) throw new RuntimeException();
            _trailingAnnotations._parent = this;
        }
        if (semi != null) {
            _semi = new EmfaticTokenNode(semi);
            if (_semi._parent != null) throw new RuntimeException();
            _semi._parent = this;
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
