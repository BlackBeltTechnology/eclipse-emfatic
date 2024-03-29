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
public class MapEntryDecl extends TopLevelDecl  {

    public static final String KW_MAPENTRY = "mapentry";

    private Annotations _annotations;
    private EmfaticTokenNode _mapentry_KW;
    private EmfaticTokenNode _name;
    private EmfaticTokenNode _colon;
    private TypeWithMulti _key;
    private EmfaticTokenNode _minus_gt;
    private TypeWithMulti _value;
    private EmfaticTokenNode _semi;

    public Annotations getAnnotations() {
        return _annotations;
    }
    public EmfaticTokenNode getMapentry_KW() {
        return _mapentry_KW;
    }
    public EmfaticTokenNode getName() {
        return _name;
    }
    public EmfaticTokenNode getColon() {
        return _colon;
    }
    public TypeWithMulti getKey() {
        return _key;
    }
    public EmfaticTokenNode getMinus_gt() {
        return _minus_gt;
    }
    public TypeWithMulti getValue() {
        return _value;
    }
    public EmfaticTokenNode getSemi() {
        return _semi;
    }


    /**
     * @return the number of children of this ASTNode
     */
    public int getChildCount() {
        int count = 0;
        if (_annotations != null) count++;
        if (_mapentry_KW != null) count++;
        if (_name != null) count++;
        if (_colon != null) count++;
        if (_key != null) count++;
        if (_minus_gt != null) count++;
        if (_value != null) count++;
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
        if ((_annotations != null) && (++count == index)) return _annotations;
        if ((_mapentry_KW != null) && (++count == index)) return _mapentry_KW;
        if ((_name != null) && (++count == index)) return _name;
        if ((_colon != null) && (++count == index)) return _colon;
        if ((_key != null) && (++count == index)) return _key;
        if ((_minus_gt != null) && (++count == index)) return _minus_gt;
        if ((_value != null) && (++count == index)) return _value;
        if ((_semi != null) && (++count == index)) return _semi;

        throw new IndexOutOfBoundsException();
    }

    /**
     * Construct a new MapEntryDecl.
     */
    public MapEntryDecl(
        Annotations annotations,
        TokenInfo mapentry_KW,
        TokenInfo name,
        TokenInfo colon,
        TypeWithMulti key,
        TokenInfo minus_gt,
        TypeWithMulti value,
        TokenInfo semi
    ) {
        super();

        if (annotations != null) {
            _annotations = annotations;
            if (_annotations._parent != null) throw new RuntimeException();
            _annotations._parent = this;
        }
        if (mapentry_KW != null) {
            _mapentry_KW = new EmfaticTokenNode(mapentry_KW);
            if (_mapentry_KW._parent != null) throw new RuntimeException();
            _mapentry_KW._parent = this;
        }
        if (name != null) {
            _name = new EmfaticTokenNode(name);
            if (_name._parent != null) throw new RuntimeException();
            _name._parent = this;
        }
        if (colon != null) {
            _colon = new EmfaticTokenNode(colon);
            if (_colon._parent != null) throw new RuntimeException();
            _colon._parent = this;
        }
        if (key != null) {
            _key = key;
            if (_key._parent != null) throw new RuntimeException();
            _key._parent = this;
        }
        if (minus_gt != null) {
            _minus_gt = new EmfaticTokenNode(minus_gt);
            if (_minus_gt._parent != null) throw new RuntimeException();
            _minus_gt._parent = this;
        }
        if (value != null) {
            _value = value;
            if (_value._parent != null) throw new RuntimeException();
            _value._parent = this;
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
