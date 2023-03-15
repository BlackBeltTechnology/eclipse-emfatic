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

/**
 * @author cjdaly@us.ibm.com
 *
 */
public abstract class ParseMessage {
	
	private String _message;
	private int _offset;
	private int _length;
	
	public ParseMessage(String message, int offset, int length) {
		init(message, offset, length);
	}
	
	public ParseMessage(String message, int offset) {
		init(message, offset, 0);
	}
	
	protected ParseMessage() {
		// invoker must call init()!
	}
	
	protected void init(String message, int offset, int length) {
		_message = message;
		_offset = offset;
		_length = length;
	}
	
	public String getMessage() {
		return _message;
	}
	
	public int getOffset() {
		return _offset;
	}
	
	public int getLength() {
		return _length;
	}

}
