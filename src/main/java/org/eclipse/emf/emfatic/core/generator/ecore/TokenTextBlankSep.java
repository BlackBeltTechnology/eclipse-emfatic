/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Miguel Garcia (Tech Univ Hamburg-Harburg) - customization for EMF Generics
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

import org.eclipse.emf.emfatic.core.lang.gen.ast.EmfaticASTNode;
import org.eclipse.emf.emfatic.core.lang.gen.ast.EmfaticASTNodeVisitor;


public class TokenTextBlankSep extends EmfaticASTNodeVisitor
{

    public TokenTextBlankSep()
    {
        _buf = new StringBuffer();
    }

    public static String Get(EmfaticASTNode node)
    {
        TokenTextBlankSep tt = new TokenTextBlankSep();
        tt.visit(node);
        return tt._buf.toString();
    }

    public boolean beginVisit(EmfaticASTNode node)
    {
        String text = node.getText();
        if(text != null) {
            String sepPre = "";
            String sepPost = "";
            String prevText = _buf.toString() +  text;
            if (prevText.endsWith("extends")
                    || prevText.endsWith("super")) {
                sepPost = " ";
            }
            if (text.equals("extends") || text.equals("super") ) {
                sepPre = " ";
            }
            _buf.append(sepPre + text + sepPost);
        }
        return true;
    }

    private StringBuffer _buf;


}
