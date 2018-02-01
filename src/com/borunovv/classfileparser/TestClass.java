package com.borunovv.classfileparser;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author borunovv
 */
public class TestClass<E extends Runnable, S extends E> implements Runnable, Closeable {

    private static long longConst = 123456789L;
    private static double doubleConst = 3.141592;
    private static String strConst = "I Am Str Const";

    private E objE;
    private S objS;

    public TestClass(E objE, S objS) {
        this.objE = objE;
        this.objS = objS;
    }

    public String get() {
        return longConst + "-" + doubleConst + "=" + strConst;
    }

    @Override
    public void run() {
    }

    @Override
    public void close() throws IOException {
    }
}
