package com.borunovv.classfileparser;

import com.borunovv.classfileparser.common.Attribute;
import com.borunovv.classfileparser.common.ClassAccessFlags;
import com.borunovv.classfileparser.common.FieldInfo;
import com.borunovv.classfileparser.common.MethodInfo;
import com.borunovv.classfileparser.constantpool.ConstantPool;
import com.borunovv.common.Assert;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * From here: https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html
 *
 * @author borunovv
 */
public class ClassFile {

    private int minorVersion;
    private int majorVersion;
    private ConstantPool constantPool;
    private Set<ClassAccessFlags> accessFlags;
    private String fullClassNameWithSlashes;
    private String fullSuperClassNameWithSlashes;
    private List<String> interfaces;
    private List<FieldInfo> fields;
    private List<MethodInfo> methods;
    private List<Attribute> attributes;

    public ClassFile(byte[] classFileContent) throws IOException {
        Assert.notNull(classFileContent, "classFileContent is null");
        parse(classFileContent);
    }

    public String getFullClassNameWithSlashes() {
        return fullClassNameWithSlashes;
    }

    private void parse(byte[] classFileContent) throws IOException {
        try (DataInputStream input = new DataInputStream(new ByteArrayInputStream(classFileContent))) {
            parse(input);
        }
    }

    private void parse(DataInputStream input) throws IOException {
        // Check magic constant at the file begin to ensure it is valid *.class file.
        int magic12 = input.readUnsignedShort();
        int magic34 = input.readUnsignedShort();
        long magic = magic12 << 16 | magic34;
        Assert.isTrue(magic == 0xCAFEBABE, "Wrong file format: magic != 0xCAFEBABE");

        // Version
        this.minorVersion = input.readUnsignedShort();
        this.majorVersion = input.readUnsignedShort();

        // Constant pool
        this.constantPool = new ConstantPool(input);

        // Access flags
        int accessFlags = input.readUnsignedShort();
        this.accessFlags = ClassAccessFlags.disassemble(accessFlags);

        // Class name
        int thisClassIndex = input.readUnsignedShort();
        this.fullClassNameWithSlashes = constantPool.getResolvedValue(thisClassIndex);

        // Super class
        int superClassIndex = input.readUnsignedShort();
        this.fullSuperClassNameWithSlashes = superClassIndex > 0 ?
                constantPool.getResolvedValue(superClassIndex) :
                "";

        // Interfaces
        int interfaceCount = input.readUnsignedShort();
        this.interfaces = new ArrayList<>(interfaceCount);
        for (int i = 0; i < interfaceCount; ++i) {
            int interfaceIndex = input.readUnsignedShort();
            String interfaceNameWithSlashes = constantPool.getResolvedValue(interfaceIndex);
            this.interfaces.add(interfaceNameWithSlashes);
        }

        // Fields
        int fieldCount = input.readUnsignedShort();
        this.fields = new ArrayList<>(fieldCount);
        for (int i = 0; i < fieldCount; ++i) {
            FieldInfo field = new FieldInfo(input, constantPool);
            this.fields.add(field);
        }

        // Methods
        int methodCount = input.readUnsignedShort();
        this.methods = new ArrayList<>(methodCount);
        for (int i = 0; i < methodCount; ++i) {
            MethodInfo field = new MethodInfo(input, constantPool);
            this.methods.add(field);
        }

        // Attributes
        int attributesCount = input.readUnsignedShort();
        this.attributes = new ArrayList<>(attributesCount);

        for (int i = 0; i < attributesCount; ++i) {
            Attribute attribute = new Attribute(input, constantPool);
            this.attributes.add(attribute);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClassFile");
        sb.append("\nclass name: ").append(fullClassNameWithSlashes);
        sb.append("\nmajor version: ").append(majorVersion);
        sb.append("\nminor version: ").append(minorVersion);
        sb.append("\naccess flags: ").append(ClassAccessFlags.toString(accessFlags));
        sb.append("\nsuper class: ").append(fullSuperClassNameWithSlashes);
        sb.append("\ninterfaces: ").append(Arrays.toString(interfaces.toArray()));

        sb.append("\n").append(constantPool);

        sb.append("\nFields:");
        for (FieldInfo field : fields) {
            sb.append("\n").append(field);
        }

        sb.append("\nMethods:");
        for (MethodInfo method : methods) {
            sb.append("\n").append(method);
        }

        sb.append("\nAttributes:");
        for (Attribute attr : attributes) {
            sb.append("\n").append(attr);
        }

        return sb.toString();
    }
}
