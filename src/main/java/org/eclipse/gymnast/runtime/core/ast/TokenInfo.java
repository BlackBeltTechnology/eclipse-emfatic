package org.eclipse.gymnast.runtime.core.ast;

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

public class TokenInfo {

    private final String _text;
    private final int _offset;
    private final int _type;

    public TokenInfo(String text, int offset, int type) {
        _text = text;
        _offset = offset;
        _type = type;
    }

    public String getText() {
        return _text;
    }

    public int getOffset() {
        return _offset;
    }

    public int getType() {
        return _type;
    }
}
