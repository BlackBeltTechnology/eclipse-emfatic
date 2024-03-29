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

package org.eclipse.emf.emfatic.core.generator.ecore;

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

import org.eclipse.emf.emfatic.core.lang.gen.ast.EmfaticTokenNode;
import org.eclipse.emf.emfatic.core.lang.gen.ast.ImportStmt;
import org.eclipse.emf.emfatic.core.lang.gen.ast.QualifiedID;
import org.eclipse.gymnast.runtime.core.parser.ParseError;

public abstract class EmfaticSemanticError extends ParseError
{
    public static class DuplicateTypeVariableName extends EmfaticSemanticError {
        DuplicateTypeVariableName(EmfaticTokenNode nameTokenNode)
        {
            String nameText = TokenText.Get(nameTokenNode);
            String message = "Duplicate type variable name: " + nameText;
            int rangeStart = nameTokenNode.getRangeStart();
            int rangeLength = nameTokenNode.getRangeLength();
            init(message, rangeStart, rangeLength);
        }
    }

    public static class ImportNotFound extends EmfaticSemanticError
    {

        ImportNotFound(ImportStmt importStmt)
        {
            String rawQIDText = TokenText.Get(importStmt.getUri());
            String message = "Failed to load import model: " + rawQIDText;
            init(message, importStmt.getRangeStart(), importStmt.getRangeLength());
        }
    }

    public static class DuplicatePackageMemberDeclaration extends EmfaticSemanticError
    {

        DuplicatePackageMemberDeclaration(EmfaticTokenNode nameTokenNode)
        {
            String nameText = TokenText.Get(nameTokenNode);
            String message = "Duplicate package member declaration: " + nameText;
            int rangeStart = nameTokenNode.getRangeStart();
            int rangeLength = nameTokenNode.getRangeLength();
            init(message, rangeStart, rangeLength);
        }
    }

    public static class DuplicateClassStructuralFeatureDeclaration extends EmfaticSemanticError
    {

        DuplicateClassStructuralFeatureDeclaration(EmfaticTokenNode nameTokenNode)
        {
            String nameText = TokenText.Get(nameTokenNode);
            String message = "Duplicate class structural feature declaration: " + nameText;
            int rangeStart = nameTokenNode.getRangeStart();
            int rangeLength = nameTokenNode.getRangeLength();
            init(message, rangeStart, rangeLength);
        }
    }

    public static class NameResolutionFailure extends EmfaticSemanticError
    {

        NameResolutionFailure(QualifiedID qualifiedID)
        {
            String rawQIDText = TokenText.Get(qualifiedID);
            String message = "Failed to resolve type: " + rawQIDText;
            init(message, qualifiedID.getRangeStart(), qualifiedID.getRangeLength());
        }
    }

    public static class IllegalSuperClassKind extends EmfaticSemanticError
    {

        IllegalSuperClassKind(QualifiedID qualifiedID)
        {
            String message = "Superclass must be an EClass!";
            init(message, qualifiedID.getRangeStart(), qualifiedID.getRangeLength());
        }
    }

    public static class IllegalAttributeKind extends EmfaticSemanticError
    {

        IllegalAttributeKind(QualifiedID qualifiedID)
        {
            String message = "Attribute type must be an EDataType!";
            init(message, qualifiedID.getRangeStart(), qualifiedID.getRangeLength());
        }
    }

    public static class IllegalReferenceKind extends EmfaticSemanticError
    {

        IllegalReferenceKind(QualifiedID qualifiedID)
        {
            String message = "Reference type must be an EClass!";
            init(message, qualifiedID.getRangeStart(), qualifiedID.getRangeLength());
        }
    }

    public EmfaticSemanticError()
    {
    }
}
