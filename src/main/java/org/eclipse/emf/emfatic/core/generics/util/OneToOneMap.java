/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Miguel Garcia (Tech Univ Hamburg-Harburg) - customization for EMF Generics
 *******************************************************************************/

package org.eclipse.emf.emfatic.core.generics.util;

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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OneToOneMap<K, V> {

	private Map<K, V> fst2snd = new HashMap<K, V>();
	private Map<V, K> snd2fst = new HashMap<V, K>();

	/**
	 * only non-null args are stored
	 */
	public void put(K fst, V snd) {
		if (fst == null || snd == null) {
			return;
		}
		fst2snd.put(fst, snd);
		snd2fst.put(snd, fst);
	}

	public V get(K k) {
		return fst2snd.get(k);
	}

	public K getInv(V v) {
		return snd2fst.get(v);
	}

	public void clear() {
		fst2snd.clear();
		snd2fst.clear();
	}

	public Set<K> keySet() {
		return fst2snd.keySet();
	}

	public Collection<V> values() {
		return fst2snd.values();
	}

}
