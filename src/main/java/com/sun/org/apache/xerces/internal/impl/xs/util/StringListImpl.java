/***** Lobxxx Translate Finished ******/
/*
 * Copyright (c) 2007, 2015, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
/*
 * Copyright 2002,2003-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 *  版权所有2002,2003-2004 Apache软件基金会。
 * 
 *  根据Apache许可证2.0版("许可证")授权;您不能使用此文件,除非符合许可证。您可以通过获取许可证的副本
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  除非适用法律要求或书面同意,否则根据许可证分发的软件按"原样"分发,不附带任何明示或暗示的担保或条件。请参阅管理许可证下的权限和限制的特定语言的许可证。
 * 
 */

package com.sun.org.apache.xerces.internal.impl.xs.util;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Vector;

import com.sun.org.apache.xerces.internal.xs.StringList;

/**
 * Containts a list of Object's.
 *
 * @xerces.internal
 *
 * <p>
 *  包含Object的列表。
 * 
 *  @ xerces.internal
 * 
 * 
 * @author Sandy Gao, IBM
 *
 * @version $Id: StringListImpl.java,v 1.7 2010-11-01 04:40:06 joehw Exp $
 */
public final class StringListImpl extends AbstractList implements StringList {

    /**
     * An immutable empty list.
     * <p>
     *  一个不可变的空列表。
     * 
     */
    public static final StringListImpl EMPTY_LIST = new StringListImpl(new String[0], 0);

    // The array to hold all data
    private final String[] fArray;
    // Number of elements in this list
    private final int fLength;

    // REVISIT: this is temp solution. In general we need to use this class
    //          instead of the Vector.
    private final Vector fVector;

    public StringListImpl(Vector v) {
        fVector = v;
        fLength = (v == null) ? 0 : v.size();
        fArray = null;
    }

    /**
     * Construct an XSObjectList implementation
     *
     * <p>
     *  构造一个XSObjectList实现
     * 
     * 
     * @param array     the data array
     * @param length    the number of elements
     */
    public StringListImpl(String[] array, int length) {
        fArray = array;
        fLength = length;
        fVector = null;
    }

    /**
     * The number of <code>Objects</code> in the list. The range of valid
     * child node indices is 0 to <code>length-1</code> inclusive.
     * <p>
     *  列表中<code> Objects </code>的数量。有效子节点索引的范围是0到<code> length-1 </code>。
     * 
     */
    public int getLength() {
        return fLength;
    }

    /**
     *  Checks if the <code>GenericString</code> <code>item</code> is a member
     * of this list.
     * <p>
     *  检查<code> GenericString </code> <code>项</code>是否为此列表的成员。
     * 
     * 
     * @param item  <code>GenericString</code> whose presence in this list is
     *   to be tested.
     * @return  True if this list contains the <code>GenericString</code>
     *   <code>item</code>.
     */
    public boolean contains(String item) {
        if (fVector != null) {
            return fVector.contains(item);
        }
        if (item == null) {
            for (int i = 0; i < fLength; i++) {
                if (fArray[i] == null)
                    return true;
            }
        }
        else {
            for (int i = 0; i < fLength; i++) {
                if (item.equals(fArray[i]))
                    return true;
            }
        }
        return false;
    }

    public String item(int index) {
        if (index < 0 || index >= fLength) {
            return null;
        }
        if (fVector != null) {
            return (String)fVector.elementAt(index);
        }
        return fArray[index];
    }

    /*
     * List methods
     * <p>
     *  列表方法
     */

    public Object get(int index) {
        if (index >= 0 && index < fLength) {
            if (fVector != null) {
                return fVector.elementAt(index);
            }
            return fArray[index];
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    public int size() {
        return getLength();
    }

    public Object[] toArray() {
        if (fVector != null) {
            return fVector.toArray();
        }
        Object[] a = new Object[fLength];
        toArray0(a);
        return a;
    }

    public Object[] toArray(Object[] a) {
        if (fVector != null) {
            return fVector.toArray(a);
        }
        if (a.length < fLength) {
            Class arrayClass = a.getClass();
            Class componentType = arrayClass.getComponentType();
            a = (Object[]) Array.newInstance(componentType, fLength);
        }
        toArray0(a);
        if (a.length > fLength) {
            a[fLength] = null;
        }
        return a;
    }

    private void toArray0(Object[] a) {
        if (fLength > 0) {
            System.arraycopy(fArray, 0, a, 0, fLength);
        }
    }

} // class StringListImpl
