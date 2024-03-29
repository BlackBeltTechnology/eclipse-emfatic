/*******************************************************************************
 * Copyright (c) 2004, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Miguel Garcia - Technischen Universitt Hamburg-Harburg (TUHH)
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

import org.eclipse.emf.ecore.*;
import org.eclipse.emf.emfatic.core.lang.gen.ast.*;
import org.eclipse.emf.emfatic.core.util.EmfaticKeywords;
import org.eclipse.gymnast.runtime.core.ast.ASTNode;
import org.eclipse.gymnast.runtime.core.parser.ParseContext;

import java.util.Iterator;

/**
 *
 * @author cjdaly@us.ibm.com
 * @author miguel.garcia@tuhh.de
 */
public abstract class GenerationPhase {

    public GenerationPhase() {
    }

    protected void initParseContext(ParseContext parseContext) {
        _parseContext = parseContext;
    }

    public void logError(EmfaticSemanticError error) {
        _parseContext.addParseMessage(error);
    }

    public void logWarning(EmfaticSemanticWarning warning) {
        _parseContext.addParseMessage(warning);
    }

    protected EPackage getSubPackage(EPackage ePackage, String name) {
        for (Iterator<EPackage> ip = ePackage.getESubpackages().iterator(); ip.hasNext();) {
            EPackage subPackage = ip.next();
            if (name.equals(subPackage.getName()))
                return subPackage;
        }

        return null;
    }

    protected EOperation getOperation(EClass eClass, String name) {
        for (Iterator<EOperation> iop = eClass.getEOperations().iterator(); iop.hasNext();) {
            EOperation eOp = iop.next();
            if (name.equals(eOp.getName()))
                return eOp;
        }

        return null;
    }

    protected EParameter getParam(EOperation eOp, String name) {
        for (Iterator<EParameter> ip = eOp.getEParameters().iterator(); ip.hasNext();) {
            EParameter param = ip.next();
            if (name.equals(param.getName()))
                return param;
        }

        return null;
    }

    protected String stripQuotes(String quotedString) {
        return quotedString.substring(1, quotedString.length() - 1);
    }

    protected String getValue(StringLiteralOrQualifiedID literal) {
        String value = null;
        if (literal instanceof QualifiedIDContainer) {
            QualifiedIDContainer qidContaier = (QualifiedIDContainer) literal;
            value = getIDText(qidContaier.getQualifiedID());
        } else if (literal instanceof StringLiteralContainer)
            value = stripQuotes(TokenText.Get(literal));
        return value;
    }

    protected String getValue(DefaultValueExpr defaultValueExpr) {
        String value = null;
        if (defaultValueExpr instanceof BoolExpr)
            value = TokenText.Get(defaultValueExpr);
        else if (defaultValueExpr instanceof IntExpr)
            value = TokenText.Get(defaultValueExpr);
        else if (defaultValueExpr instanceof StringExpr)
            value = stripQuotes(TokenText.Get(defaultValueExpr));
        else if (defaultValueExpr instanceof CharExpr)
            value = stripQuotes(TokenText.Get(defaultValueExpr));
        return value;
    }

    protected String getValue(BoundExceptWildcard nonWildBound) {
        String value = null;
        if (nonWildBound.getOneOrMoreTypeArgs() != null) {
            value = getIDText(nonWildBound.getRawTNameOrTVarOrParamzedTName());
            value += "<";
            ASTNode[] tas = nonWildBound.getOneOrMoreTypeArgs().getChildren();
            for (int i = 0; i < tas.length; i++) {
                if (tas[i] instanceof TypeArg) {
                    TypeArg ta = (TypeArg) tas[i];
                    value += getValue(ta) + (i < tas.length ? ", " : "");
                }
            }
            value += ">";
        } else {
            // must be a RawTypeOrTypeVar
            value = unescape(TokenText.Get(nonWildBound));
        }
        return value;
    }

    protected String getValue(TypeArg ta) {
        String value = "";
        if (ta instanceof BoundExceptWildcard) {
            value = getValue((BoundExceptWildcard) ta);
        } else {
            // must be a wildcard
            // sequence wildcard : QMARK ( extendsOrSuper boundExceptWildcard )?
            value = "?";
            Wildcard w = (Wildcard) ta;
            BoundExceptWildcard nonWildBound = w.getBoundExceptWildcard();
            if (nonWildBound != null ) {
                value += " " + getValue(nonWildBound);
            }
        }
        return value;
    }

    public static String getIDText(EmfaticTokenNode idToken) {
        return unescape(idToken.getText());
    }

    public static String getIDText(QualifiedID qualifiedID) {
        return unescape(TokenText.Get(qualifiedID));
    }

    protected static String unescape(String id) {
        return id.replaceAll(EmfaticKeywords.KEYWORD_ESCAPE_STRING, "");
    }

    protected void setMultiplicity(Multiplicity multiplicity, ETypedElement eTypedElement) {
        if (multiplicity == null) {
            eTypedElement.setLowerBound(0);
            eTypedElement.setUpperBound(1);
            return;
        }
        MultiplicityExpr mExpr = multiplicity.getMultiplicityExpr();
        if (mExpr == null) {
            eTypedElement.setLowerBound(0);
            eTypedElement.setUpperBound(-1);
            return;
        }
        String lowerBoundText = mExpr.getLowerBound().getText();
        if (lowerBoundText.equals("?")) {
            eTypedElement.setLowerBound(0);
            eTypedElement.setUpperBound(1);
        } else if (lowerBoundText.equals("*")) {
            eTypedElement.setLowerBound(0);
            eTypedElement.setUpperBound(-1);
        } else if (lowerBoundText.equals("+")) {
            eTypedElement.setLowerBound(1);
            eTypedElement.setUpperBound(-1);
        } else {
            int lowerBound = Integer.parseInt(lowerBoundText);
            eTypedElement.setLowerBound(lowerBound);
            if (mExpr.getUpperBound() == null) {
                eTypedElement.setUpperBound(lowerBound);
            } else {
                String upperBoundText = mExpr.getUpperBound().getText();
                if (upperBoundText.equals("?"))
                    eTypedElement.setUpperBound(-2);
                else if (upperBoundText.equals("*"))
                    eTypedElement.setUpperBound(-1);
                else if (upperBoundText.equals("+")) {
                    eTypedElement.setUpperBound(-1);
                } else {
                    int upperBound = Integer.parseInt(upperBoundText);
                    eTypedElement.setUpperBound(upperBound);
                }
            }
        }
    }

    private ParseContext _parseContext;
}
