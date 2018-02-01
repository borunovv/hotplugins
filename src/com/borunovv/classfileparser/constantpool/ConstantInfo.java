package com.borunovv.classfileparser.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author borunovv
 */
public abstract class ConstantInfo {
    public final ConstantType type;

    public ConstantInfo(ConstantType type) {
        this.type = type;
    }

    public ConstantType getType() {
        return type;
    }

    public int howManyEntriesTakeInConstantPoolTable() {
        return 1;
    }

    public abstract void fromStream(DataInputStream input) throws IOException;
    public abstract String getResolvedValue(ConstantPool constantPool);
    protected abstract String getReference();
}
