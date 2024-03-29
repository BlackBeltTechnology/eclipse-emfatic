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
public class BoundExceptWildcard extends TypeArg  {


    private QualifiedID _rawTNameOrTVarOrParamzedTName;
    private EmfaticTokenNode _lt;
    private OneOrMoreTypeArgs _oneOrMoreTypeArgs;
    private EmfaticTokenNode _gt;

    public QualifiedID getRawTNameOrTVarOrParamzedTName() {
        return _rawTNameOrTVarOrParamzedTName;
    }
    public EmfaticTokenNode getLt() {
        return _lt;
    }
    public OneOrMoreTypeArgs getOneOrMoreTypeArgs() {
        return _oneOrMoreTypeArgs;
    }
    public EmfaticTokenNode getGt() {
        return _gt;
    }


    /**
     * @return the number of children of this ASTNode
     */
    public int getChildCount() {
        int count = 0;
        if (_rawTNameOrTVarOrParamzedTName != null) count++;
        if (_lt != null) count++;
        if (_oneOrMoreTypeArgs != null) count++;
        if (_gt != null) count++;

        return count;
    }

    /**
     * @param index the index of a child ASTNode to get
     * @return the child ASTNode at the given index
     * @throws IndexOutOfBoundsException when the index is out of bounds
     */
    public ASTNode getChild(int index) {
        int count = -1;
        if ((_rawTNameOrTVarOrParamzedTName != null) && (++count == index)) return _rawTNameOrTVarOrParamzedTName;
        if ((_lt != null) && (++count == index)) return _lt;
        if ((_oneOrMoreTypeArgs != null) && (++count == index)) return _oneOrMoreTypeArgs;
        if ((_gt != null) && (++count == index)) return _gt;

        throw new IndexOutOfBoundsException();
    }

    /**
     * Construct a new BoundExceptWildcard.
     */
    public BoundExceptWildcard(
        QualifiedID rawTNameOrTVarOrParamzedTName,
        TokenInfo lt,
        OneOrMoreTypeArgs oneOrMoreTypeArgs,
        TokenInfo gt
    ) {
        super();

        if (rawTNameOrTVarOrParamzedTName != null) {
            _rawTNameOrTVarOrParamzedTName = rawTNameOrTVarOrParamzedTName;
            if (_rawTNameOrTVarOrParamzedTName._parent != null) throw new RuntimeException();
            _rawTNameOrTVarOrParamzedTName._parent = this;
        }
        if (lt != null) {
            _lt = new EmfaticTokenNode(lt);
            if (_lt._parent != null) throw new RuntimeException();
            _lt._parent = this;
        }
        if (oneOrMoreTypeArgs != null) {
            _oneOrMoreTypeArgs = oneOrMoreTypeArgs;
            if (_oneOrMoreTypeArgs._parent != null) throw new RuntimeException();
            _oneOrMoreTypeArgs._parent = this;
        }
        if (gt != null) {
            _gt = new EmfaticTokenNode(gt);
            if (_gt._parent != null) throw new RuntimeException();
            _gt._parent = this;
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
