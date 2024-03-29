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
public class EnumDecl extends TopLevelDecl  {

    public static final String KW_ENUM = "enum";

    private Annotations _annotations;
    private EmfaticTokenNode _enum_KW;
    private EmfaticTokenNode _name;
    private EmfaticTokenNode _lcurly;
    private EnumLiterals _enumLiterals;
    private EmfaticTokenNode _rcurly;

    public Annotations getAnnotations() {
        return _annotations;
    }
    public EmfaticTokenNode getEnum_KW() {
        return _enum_KW;
    }
    public EmfaticTokenNode getName() {
        return _name;
    }
    public EmfaticTokenNode getLcurly() {
        return _lcurly;
    }
    public EnumLiterals getEnumLiterals() {
        return _enumLiterals;
    }
    public EmfaticTokenNode getRcurly() {
        return _rcurly;
    }


    /**
     * @return the number of children of this ASTNode
     */
    public int getChildCount() {
        int count = 0;
        if (_annotations != null) count++;
        if (_enum_KW != null) count++;
        if (_name != null) count++;
        if (_lcurly != null) count++;
        if (_enumLiterals != null) count++;
        if (_rcurly != null) count++;

        return count;
    }

    /**
     * @param index the index of a child ASTNode to get
     * @return the child ASTNode at the given index
     * @throws IndexOutOfBoundsException when the index is out of bounds
     */
    public ASTNode getChild(int index) {
        int count = -1;
        if ((_annotations != null) && (++count == index)) return _annotations;
        if ((_enum_KW != null) && (++count == index)) return _enum_KW;
        if ((_name != null) && (++count == index)) return _name;
        if ((_lcurly != null) && (++count == index)) return _lcurly;
        if ((_enumLiterals != null) && (++count == index)) return _enumLiterals;
        if ((_rcurly != null) && (++count == index)) return _rcurly;

        throw new IndexOutOfBoundsException();
    }

    /**
     * Construct a new EnumDecl.
     */
    public EnumDecl(
        Annotations annotations,
        TokenInfo enum_KW,
        TokenInfo name,
        TokenInfo lcurly,
        EnumLiterals enumLiterals,
        TokenInfo rcurly
    ) {
        super();

        if (annotations != null) {
            _annotations = annotations;
            if (_annotations._parent != null) throw new RuntimeException();
            _annotations._parent = this;
        }
        if (enum_KW != null) {
            _enum_KW = new EmfaticTokenNode(enum_KW);
            if (_enum_KW._parent != null) throw new RuntimeException();
            _enum_KW._parent = this;
        }
        if (name != null) {
            _name = new EmfaticTokenNode(name);
            if (_name._parent != null) throw new RuntimeException();
            _name._parent = this;
        }
        if (lcurly != null) {
            _lcurly = new EmfaticTokenNode(lcurly);
            if (_lcurly._parent != null) throw new RuntimeException();
            _lcurly._parent = this;
        }
        if (enumLiterals != null) {
            _enumLiterals = enumLiterals;
            if (_enumLiterals._parent != null) throw new RuntimeException();
            _enumLiterals._parent = this;
        }
        if (rcurly != null) {
            _rcurly = new EmfaticTokenNode(rcurly);
            if (_rcurly._parent != null) throw new RuntimeException();
            _rcurly._parent = this;
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
