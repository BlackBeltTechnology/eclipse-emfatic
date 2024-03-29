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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.emfatic.core.generics.util.OneToManyMap;
import org.eclipse.emf.emfatic.core.generics.util.OneToOneMap;
import org.eclipse.gymnast.runtime.core.ast.ASTNode;
import org.eclipse.gymnast.runtime.core.outline.OutlineNode;

import java.util.Map;


/**
 *
 * @generated by Gymnast from Emfatic.ast on 06.02.2007 17:57:34
 */
public class CompUnit extends EmfaticASTNode  {

    private PackageDecl _packageDecl;
    private ImportStmts _importStmts;
    private TopLevelDecls _topLevelDecls;

    public PackageDecl getPackageDecl() {
        return _packageDecl;
    }

    public ImportStmts getImportStmts() {
        return _importStmts;
    }

    public TopLevelDecls getTopLevelDecls() {
        return _topLevelDecls;
    }


    /**
     * @return the number of children of this ASTNode
     */
    public int getChildCount() {
        int count = 0;
        if (_packageDecl != null) count++;
        if (_importStmts != null) count++;
        if (_topLevelDecls != null) count++;

        return count;
    }

    /**
     * @param index the index of a child ASTNode to get
     * @return the child ASTNode at the given index
     * @throws IndexOutOfBoundsException when the index is out of bounds
     */
    public ASTNode getChild(int index) {
        int count = -1;
        if ((_packageDecl != null) && (++count == index)) return _packageDecl;
        if ((_importStmts != null) && (++count == index)) return _importStmts;
        if ((_topLevelDecls != null) && (++count == index)) return _topLevelDecls;

        throw new IndexOutOfBoundsException();
    }

    /**
     * Construct a new CompUnit.
     */
    public CompUnit(
        PackageDecl packageDecl,
        ImportStmts importStmts,
        TopLevelDecls topLevelDecls
    ) {
        super();

        if (packageDecl != null) {
            _packageDecl = packageDecl;
            if (_packageDecl._parent != null) throw new RuntimeException();
            _packageDecl._parent = this;
        }
        if (importStmts != null) {
            _importStmts = importStmts;
            if (_importStmts._parent != null) throw new RuntimeException();
            _importStmts._parent = this;
        }
        if (topLevelDecls != null) {
            _topLevelDecls = topLevelDecls;
            if (_topLevelDecls._parent != null) throw new RuntimeException();
            _topLevelDecls._parent = this;
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

    public void setAST(EPackage rootPackage) {
        ast = rootPackage;
    }

    public EPackage getAST() {
        return ast;
    }

    EPackage ast = null;
    /**
     * The contents of the map cst2outline are as follows:
     *
     *
     * Attribute at -> new OutlineNode(at.getName(), ...
     *
     * Reference c -> new OutlineNode(c.getName(), ...
     *
     * Annotation astAnn -> new OutlineNode(astAnn, ...
     *
     * kv instanceof KeyEqualsValue -> new OutlineNode(kv, ...
     *
     * Operation o -> new OutlineNode(o.getName(), ...
     *
     * TypeParam tp -> new OutlineNode(tp, ...
     *
     * BoundExceptWildcard bound -> new OutlineNode(bound, ...
     *
     * Param p -> new OutlineNode(p, ...
     *
     */
    public void setCst2Outline(Map<ASTNode, OutlineNode> cst2outline) {
        this.cst2outline = cst2outline;
    }

    public Map<ASTNode, OutlineNode> getCst2Outline() {
        return cst2outline;
    }

    public void setMaps(OneToOneMap<ASTNode, EObject> cstDecl2EcoreAST, OneToManyMap<EObject, ASTNode> ecoreDecl2cstUse) {
        this.cstDecl2EcoreAST = cstDecl2EcoreAST;
        this.ecoreDecl2cstUse = ecoreDecl2cstUse;
    }

    private OneToOneMap<ASTNode, EObject> cstDecl2EcoreAST = null;
    private OneToManyMap<EObject, ASTNode> ecoreDecl2cstUse = null;
    private Map<ASTNode, OutlineNode> cst2outline = null;

    public OneToOneMap<ASTNode, EObject> getCstDecl2EcoreAST() {
        return cstDecl2EcoreAST;
    }

    public OneToManyMap<EObject, ASTNode> getEcoreDecl2CstUse() {
        return ecoreDecl2cstUse;
    }

}
