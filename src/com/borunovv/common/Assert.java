package com.borunovv.common;

/**
 * @author borunovv
 */
public final class Assert {

    public static void isTrue(boolean cond, String msg) {
        if (! cond) {
            throw new AssertException(msg);
        }
    }

    public static void notNull(Object obj, String msg) {
        if (obj == null) {
            throw new AssertException(msg);
        }
    }

    public static void arraysAreEqual(byte[] ar1, byte[] ar2) {
        Assert.isTrue(ar1.length == ar2.length, "Array lengths are different!");

        for (int i = 0; i < ar1.length; ++i) {
            Assert.isTrue(ar1[i] == ar2[i], "Arrays items with index #" + i + " are different");
        }
    }
}