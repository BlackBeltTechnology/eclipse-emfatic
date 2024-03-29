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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OneToManyMap<K, V> {

    private Map<K, Set<V>> m = new HashMap<K, Set<V>>();
    private Map<V, K> snd2fst = new HashMap<V, K>();

    /**
     * only non-null args are stored
     */
    public void put(K k, V v) {
        if (k == null || v == null) {
            return;
        }
        snd2fst.put(v,k);
        Set<V> vs = m.get(k);
        if (vs != null) {
            vs.add(v);
        } else {
            vs = new HashSet<V>();
            vs.add(v);
            m.put(k, vs);
        }
    }

    /**
     * never returns null but empty set
     */
    public Set<V> get(K k) {
        if (m.get(k) == null) {
            return Collections.emptySet();
        } else {
            return m.get(k);
        }
    }

    public K getInv(V v) {
        return snd2fst.get(v);
    }

    public void clear() {
        m.clear();
    }

    public Set<K> keySet() {
        return m.keySet();
    }

    public Collection<V> values() {
        Collection<Set<V>> csv = m.values();
        Set<V> res = new HashSet<V>();
        for (Set<V> vs : csv) {
            res.addAll(vs);
        }
        return res;
    }

}
