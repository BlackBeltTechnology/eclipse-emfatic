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

package org.eclipse.gymnast.runtime.core.parser;

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

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.gymnast.runtime.core.ast.ASTNode;


/**
 * @author cjdaly@us.ibm.com
 *
 */
public class ParseContext {
    private ASTNode _parseRoot;
    private Object _parseResult;
    private ArrayList _messages;

    public ParseContext() {
        _messages = new ArrayList();
    }

    public ASTNode getParseRoot() {
        return _parseRoot;
    }

    public void setParseRoot(ASTNode parseRoot) {
        _parseRoot = parseRoot;
    }

    public Object getParseResult() {
        return _parseResult;
    }

    public void setParseResult(Object parseResult) {
        _parseResult = parseResult;
    }

    public ParseMessage[] getMessages() {
        return (ParseMessage[]) _messages.toArray(new ParseMessage[_messages.size()]);
    }

    public int getMessageCount() {
        return _messages.size();
    }

    public boolean hasErrors() {
        return getErrorCount() > 0;
    }

    public int getErrorCount() {
        int errorCount = 0;
        Iterator i = _messages.iterator();
        while (i.hasNext()) {
            Object o = i.next();
            if (o instanceof ParseError) errorCount++;
        }
        return errorCount;
    }

    public int getWarningCount() {
        int warningCount = 0;
        Iterator i = _messages.iterator();
        while (i.hasNext()) {
            Object o = i.next();
            if (o instanceof ParseWarning) warningCount++;
        }
        return warningCount;
    }

    public void addParseMessage(ParseMessage parseMessage) {
        _messages.add(parseMessage);
    }
}
