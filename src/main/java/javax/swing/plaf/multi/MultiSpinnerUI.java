/***** Lobxxx Translate Finished ******/
/*
 * Copyright (c) 2001, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package javax.swing.plaf.multi;

import java.util.Vector;
import javax.swing.plaf.SpinnerUI;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.accessibility.Accessible;

/**
 * A multiplexing UI used to combine <code>SpinnerUI</code>s.
 *
 * <p>This file was automatically generated by AutoMulti.
 *
 * <p>
 *  用于组合<code> SpinnerUI </code>的复用UI。
 * 
 *  <p>此文件由AutoMulti自动生成。
 * 
 * 
 * @author  Otto Multey
 * @since 1.4
 */
public class MultiSpinnerUI extends SpinnerUI {

    /**
     * The vector containing the real UIs.  This is populated
     * in the call to <code>createUI</code>, and can be obtained by calling
     * the <code>getUIs</code> method.  The first element is guaranteed to be the real UI
     * obtained from the default look and feel.
     * <p>
     *  包含真实UI的向量。这是在调用<code> createUI </code>时填充的,并且可以通过调用<code> getUIs </code>方法获得。第一个元素保证是从默认外观获得的真实UI。
     * 
     */
    protected Vector uis = new Vector();

////////////////////
// Common UI methods
////////////////////

    /**
     * Returns the list of UIs associated with this multiplexing UI.  This
     * allows processing of the UIs by an application aware of multiplexing
     * UIs on components.
     * <p>
     *  返回与此多路复用UI关联的UI列表。这允许通过应用程序处理UI来了解组件上的多路复用UI。
     * 
     */
    public ComponentUI[] getUIs() {
        return MultiLookAndFeel.uisToArray(uis);
    }

////////////////////
// SpinnerUI methods
////////////////////

////////////////////
// ComponentUI methods
////////////////////

    /**
     * Invokes the <code>contains</code> method on each UI handled by this object.
     *
     * <p>
     *  在此对象处理的每个UI上调用<code>包含</code>方法。
     * 
     * 
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public boolean contains(JComponent a, int b, int c) {
        boolean returnValue =
            ((ComponentUI) (uis.elementAt(0))).contains(a,b,c);
        for (int i = 1; i < uis.size(); i++) {
            ((ComponentUI) (uis.elementAt(i))).contains(a,b,c);
        }
        return returnValue;
    }

    /**
     * Invokes the <code>update</code> method on each UI handled by this object.
     * <p>
     *  在此对象处理的每个UI上调用<code> update </code>方法。
     * 
     */
    public void update(Graphics a, JComponent b) {
        for (int i = 0; i < uis.size(); i++) {
            ((ComponentUI) (uis.elementAt(i))).update(a,b);
        }
    }

    /**
     * Returns a multiplexing UI instance if any of the auxiliary
     * <code>LookAndFeel</code>s supports this UI.  Otherwise, just returns the
     * UI object obtained from the default <code>LookAndFeel</code>.
     * <p>
     *  如果任何辅助<code> LookAndFeel </code>支持此UI,则返回复用UI实例。否则,只返回从默认<code> LookAndFeel </code>获得的UI对象。
     * 
     */
    public static ComponentUI createUI(JComponent a) {
        ComponentUI mui = new MultiSpinnerUI();
        return MultiLookAndFeel.createUIs(mui,
                                          ((MultiSpinnerUI) mui).uis,
                                          a);
    }

    /**
     * Invokes the <code>installUI</code> method on each UI handled by this object.
     * <p>
     *  在此对象处理的每个UI上调用<code> installUI </code>方法。
     * 
     */
    public void installUI(JComponent a) {
        for (int i = 0; i < uis.size(); i++) {
            ((ComponentUI) (uis.elementAt(i))).installUI(a);
        }
    }

    /**
     * Invokes the <code>uninstallUI</code> method on each UI handled by this object.
     * <p>
     *  在此对象处理的每个UI上调用<code> uninstallUI </code>方法。
     * 
     */
    public void uninstallUI(JComponent a) {
        for (int i = 0; i < uis.size(); i++) {
            ((ComponentUI) (uis.elementAt(i))).uninstallUI(a);
        }
    }

    /**
     * Invokes the <code>paint</code> method on each UI handled by this object.
     * <p>
     *  在此对象处理的每个UI上调用<code> paint </code>方法。
     * 
     */
    public void paint(Graphics a, JComponent b) {
        for (int i = 0; i < uis.size(); i++) {
            ((ComponentUI) (uis.elementAt(i))).paint(a,b);
        }
    }

    /**
     * Invokes the <code>getPreferredSize</code> method on each UI handled by this object.
     *
     * <p>
     *  在此对象处理的每个UI上调用<code> getPreferredSize </code>方法。
     * 
     * 
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public Dimension getPreferredSize(JComponent a) {
        Dimension returnValue =
            ((ComponentUI) (uis.elementAt(0))).getPreferredSize(a);
        for (int i = 1; i < uis.size(); i++) {
            ((ComponentUI) (uis.elementAt(i))).getPreferredSize(a);
        }
        return returnValue;
    }

    /**
     * Invokes the <code>getMinimumSize</code> method on each UI handled by this object.
     *
     * <p>
     *  在此对象处理的每个UI上调用<code> getMinimumSize </code>方法。
     * 
     * 
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public Dimension getMinimumSize(JComponent a) {
        Dimension returnValue =
            ((ComponentUI) (uis.elementAt(0))).getMinimumSize(a);
        for (int i = 1; i < uis.size(); i++) {
            ((ComponentUI) (uis.elementAt(i))).getMinimumSize(a);
        }
        return returnValue;
    }

    /**
     * Invokes the <code>getMaximumSize</code> method on each UI handled by this object.
     *
     * <p>
     *  在此对象处理的每个UI上调用<code> getMaximumSize </code>方法。
     * 
     * 
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public Dimension getMaximumSize(JComponent a) {
        Dimension returnValue =
            ((ComponentUI) (uis.elementAt(0))).getMaximumSize(a);
        for (int i = 1; i < uis.size(); i++) {
            ((ComponentUI) (uis.elementAt(i))).getMaximumSize(a);
        }
        return returnValue;
    }

    /**
     * Invokes the <code>getAccessibleChildrenCount</code> method on each UI handled by this object.
     *
     * <p>
     * 在此对象处理的每个UI上调用<code> getAccessibleChildrenCount </code>方法。
     * 
     * 
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public int getAccessibleChildrenCount(JComponent a) {
        int returnValue =
            ((ComponentUI) (uis.elementAt(0))).getAccessibleChildrenCount(a);
        for (int i = 1; i < uis.size(); i++) {
            ((ComponentUI) (uis.elementAt(i))).getAccessibleChildrenCount(a);
        }
        return returnValue;
    }

    /**
     * Invokes the <code>getAccessibleChild</code> method on each UI handled by this object.
     *
     * <p>
     *  在此对象处理的每个UI上调用<code> getAccessibleChild </code>方法。
     * 
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public Accessible getAccessibleChild(JComponent a, int b) {
        Accessible returnValue =
            ((ComponentUI) (uis.elementAt(0))).getAccessibleChild(a,b);
        for (int i = 1; i < uis.size(); i++) {
            ((ComponentUI) (uis.elementAt(i))).getAccessibleChild(a,b);
        }
        return returnValue;
    }
}
