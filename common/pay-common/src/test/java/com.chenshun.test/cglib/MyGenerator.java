package com.chenshun.test.cglib;

import javassist.*;
import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
 * User: mew <p />
 * Time: 18/1/30 16:24  <p />
 * Version: V1.0  <p />
 * Description: 通过 ASM 创建 .class 文件 <p />
 */
public class MyGenerator {

    private static final String CLASS_SAVE_PATH = "/Users/mew/Desktop/";

    // ASM 创建类的字节码
    @Test
    public void generatorClass1() throws IOException {
        ClassWriter classWriter = new ClassWriter(0);
        // 通过visit方法确定类的头部信息
        classWriter.visit(Opcodes.V1_7, // java版本
                Opcodes.ACC_PUBLIC, // 类修饰符
                "Programmer", // 类的全限定名
                null, "java/lang/Object", null);

        // 创建构造函数
        MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        // 定义code方法
        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "code", "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitLdcInsn("I'm a Programmer,Just Coding.....");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(2, 2);
        methodVisitor.visitEnd();
        classWriter.visitEnd();
        // 使classWriter类已经完成
        // 将classWriter转换成字节数组写到文件里面去
        byte[] data = classWriter.toByteArray();
        File file = new File(CLASS_SAVE_PATH + "Programmer.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }

    // Javassist 创建类的字节码
    @Test
    public void generatorClass2() throws CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        // 创建Programmer类
        CtClass cc = pool.makeClass("com.samples.Programmer");
        // 定义code方法
        CtMethod method = CtNewMethod.make("public void code(){}", cc);
        // 插入方法代码
        method.insertBefore("System.out.println(\"I'm a Programmer,Just Coding.....\");");
        cc.addMethod(method);
        // 保存生成的字节码
        cc.writeFile(CLASS_SAVE_PATH);
    }

    @Test
    public void loadClass() throws IOException, IllegalAccessException, InstantiationException {
        // 读取本地的class文件内的字节码，转换成字节码数组
        InputStream input = new FileInputStream(CLASS_SAVE_PATH + "Programmer.class");
        byte[] result = new byte[1024];

        int count = input.read(result);
        // 使用自定义的类加载器将 byte字节码数组转换为对应的class对象
        MyClassLoader loader = new MyClassLoader();
        Class clazz = loader.defineMyClass(result, 0, count);
        // 测试加载是否成功，打印class 对象的名称
        System.out.println(clazz.getCanonicalName());

        // 实例化一个Programmer对象
        Object o = clazz.newInstance();
        try {
            // 调用Programmer的code方法
            clazz.getMethod("code", null).invoke(o, null);
        } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

}
