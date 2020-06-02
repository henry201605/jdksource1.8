/***** Lobxxx Translate Finished ******/
/*
 * Copyright (c) 2000, 2004, Oracle and/or its affiliates. All rights reserved.
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
package javax.print.attribute.standard;

import javax.print.attribute.Attribute;
import javax.print.attribute.IntegerSyntax;
import javax.print.attribute.PrintServiceAttribute;

/**
 * Class PagesPerMinuteColor is an integer valued printing attribute that
 * indicates the nominal number of pages per minute to the nearest whole number
 * which may be generated by this printer when printing color (e.g., simplex,
 * color). For purposes of this attribute, "color" means the same as for the
 * {@link ColorSupported ColorSupported} attribute, namely, the device is
 * capable of any type of color printing at all, including highlight color as
 * well as full process color. This attribute is informative, not a service
 * guarantee. Generally, it is the value used in the marketing literature to
 * describe the color capabilities of this device. A value of 0 indicates a
 * device that takes more than two minutes to process a page. If a color device
 * has several color modes, it may use the pages-per- minute value for this
 * attribute that corresponds to the mode that produces the highest number.
 * <P>
 * A black and white only printer must not include the PagesPerMinuteColor
 * attribute in its attribute set or service registration. If this attribute is
 * present, then the {@link ColorSupported ColorSupported} printer description
 * attribute must also be present and have a value of SUPPORTED.
 * <P>
 * <B>IPP Compatibility:</B> The integer value gives the IPP integer value. The
 * category name returned by <CODE>getName()</CODE> gives the IPP attribute
 * name.
 * <P>
 *
 * <p>
 *  类PagesPerMinuteColor是整数值打印属性,其指示当打印颜色(例如,单色,彩色)时可由该打印机产生的最近整数的每分钟页面的标称页数。
 * 出于此属性的目的,"颜色"意味着与{@link ColorSupported ColorSupported}属性相同,即,设备能够进行任何类型的彩色打印,包括高亮颜色以及全过程颜色。
 * 此属性是信息性的,而不是服务保证。一般来说,在营销文献中使用的值用于描述该设备的颜色能力。值为0表示处理页面所需的时间超过两分钟的设备。
 * 如果彩色设备具有几种颜色模式,则它可以使用对应于产生最高数字的模式的该属性的每分钟值。
 * <P>
 *  仅限黑白打印机在其属性集或服务注册中不得包含PagesPerMinuteColor属性。
 * 如果此属性存在,则{@link ColorSupported ColorSupported}打印机描述属性也必须存在,并且值为SUPPORTED。
 * <P>
 *  <B> IPP兼容性：</B>整数值给出IPP整数值。由<CODE> getName()</CODE>返回的类别名称给出了IPP属性名称。
 * <P>
 * 
 * 
 * @author  Alan Kaminsky
 */
public final class PagesPerMinuteColor extends IntegerSyntax
        implements PrintServiceAttribute {

    static final long serialVersionUID = 1684993151687470944L;

    /**
     * Construct a new pages per minute color attribute with the given integer
     * value.
     *
     * <p>
     * 使用给定的整数值构造每分钟新的页面颜色属性。
     * 
     * 
     * @param  value  Integer value.
     *
     * @exception  IllegalArgumentException
     *    (Unchecked exception) Thrown if <CODE>value</CODE> is less than 0.
     */
    public PagesPerMinuteColor(int value) {
        super(value, 0, Integer.MAX_VALUE);
    }

    /**
     * Returns whether this pages per minute color attribute is equivalent to
     * the passed in object. To be equivalent, all of the following conditions
     * must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is an instance of class PagesPerMinuteColor.
     * <LI>
     * This pages per minute attribute's value and <CODE>object</CODE>'s
     * value are equal.
     * </OL>
     *
     * <p>
     *  返回此页面每分钟颜色属性是否等同于传入的对象。为了等效,所有以下条件必须为真：
     * <OL TYPE=1>
     * <LI>
     *  <CODE>对象</CODE>不为空。
     * <LI>
     *  <CODE>对象</CODE>是PagesPerMinuteColor类的实例。
     * <LI>
     *  此每分钟属性值和<CODE>对象</CODE>的值相等。
     * </OL>
     * 
     * 
     * @param  object  Object to compare to.
     *
     * @return  True if <CODE>object</CODE> is equivalent to this pages per
     *          minute color attribute, false otherwise.
     */
    public boolean equals(Object object) {
        return (super.equals(object) &&
                object instanceof PagesPerMinuteColor);
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <P>
     * For class PagesPerMinuteColor, the
     * category is class PagesPerMinuteColor itself.
     *
     * <p>
     * 
     * @return  Printing attribute class (category), an instance of class
     *          {@link java.lang.Class java.lang.Class}.
     */
    public final Class<? extends Attribute> getCategory() {
        return PagesPerMinuteColor.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <P>
     * For class PagesPerMinuteColor, the
     * category name is <CODE>"pages-per-minute-color"</CODE>.
     *
     * <p>
     *  获取要用作此打印属性值的"类别"的打印属性类。
     * <P>
     *  对于PagesPerMinuteColor类,类别是PagesPerMinuteColor本身。
     * 
     * 
     * @return  Attribute category name.
     */
    public final String getName() {
        return "pages-per-minute-color";
    }

}
