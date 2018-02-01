package com.borunovv.classfileparser.constantpool;

import com.borunovv.common.Assert;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author borunovv
 */
public class ConstantPool {

    private Map<Integer, ConstantInfo> constantPool = new LinkedHashMap<>();

    public ConstantPool(DataInputStream input) throws IOException {
        parse(input);
    }

    public ConstantInfo get(int index) {
        Assert.isTrue(constantPool.containsKey(index), "Constant not exists, index #" + index);
        return constantPool.get(index);
    }

    public String getResolvedValue(int index) {
        return get(index).getResolvedValue(this);
    }

    private void parse(DataInputStream input) throws IOException {
        int constantPoolCount = input.readUnsignedShort();
        int index = 1;
        while (index < constantPoolCount) {
            int tag = input.readUnsignedByte();
            ConstantInfo constant = createConstant(tag);
            constant.fromStream(input);
            constantPool.put(index, constant);
            index += (constant.howManyEntriesTakeInConstantPoolTable());
        }
    }

    private ConstantInfo createConstant(int tag) {
        ConstantType type = ConstantType.fromTag(tag);
        switch (type) {
            case Class:
                return new ConstantClass();
            case Fieldref:
                return new ConstantFieldRef();
            case MethodRef:
                return new ConstantMethodRef();
            case InterfaceMethodRef:
                return new ConstantInterfaceMethodRef();
            case String:
                return new ConstantString();
            case Integer:
                return new ConstantInteger();
            case Float:
                return new ConstantFloat();
            case Long:
                return new ConstantLong();
            case Double:
                return new ConstantDouble();
            case NameAndType:
                return new ConstantNameAndType();
            case Utf8:
                return new ConstantUtf8();
            case MethodHandle:
                return new ConstantMethodHandle();
            case MethodType:
                return new ConstantMethodType();
            case InvokeDynamic:
                return new ConstantInvokeDynamic();
            default:
                throw new RuntimeException("Unrecognized constant tag: " + tag);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Constant pool:");

        for (Map.Entry<Integer, ConstantInfo> entry : constantPool.entrySet()) {
            int index = entry.getKey();
            ConstantInfo info = entry.getValue();
            String reference = entry.getValue().getReference();
            String resolvedValue = entry.getValue().getResolvedValue(this);

            sb.append("\n  ").append(addSpaces("#" + index + ": " + info.getType(), 20));

            if (reference != null) {
                sb.append(addSpaces(reference, 16));
            }

            if (resolvedValue != null) {
                sb.append("// ").append(resolvedValue);
            }
        }

        return sb.toString();
    }

    private String addSpaces(String str, int len) {
        StringBuilder sb = new StringBuilder(str);
        int spacesToAdd = len - str.length();
        for (int i = 0; i < spacesToAdd; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }
}
