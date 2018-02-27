package com.chenshun.test.hadoop.hive;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;

/**
 * 自定义UDTF,用于解析request url
 * 原始数据：
 * "GET /static/image/common/faq.gif HTTP/1.1"
 * 输出数据：
 * method      url                             protocol
 * GET       /static/image/common/faq.gif      HTTP/1.1
 */
public class RequestUDTF extends GenericUDTF {

    /**
     * 初始化的一个过程，通常再次定义函数的输出相关信息，比如输出的字段名称和类型
     *
     * @param argOIs
     * @return
     * @throws UDFArgumentException
     */
    public StructObjectInspector initialize(StructObjectInspector argOIs)
            throws UDFArgumentException {
        if (argOIs.getAllStructFieldRefs().size() != 1) {
            throw new UDFArgumentException("参数只能有一个！！！！！");
        }

        // structFieldNames
        ArrayList<String> fieldList = new ArrayList<String>();
        fieldList.add("method");
        fieldList.add("url");
        fieldList.add("protocol");

        // 指定字段的类型
        ArrayList<ObjectInspector> inspectorList = new ArrayList<ObjectInspector>();
        inspectorList.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        inspectorList.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        inspectorList.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(
                fieldList, inspectorList);
    }

    /**
     * Give a set of arguments for the UDTF to process.
     *
     * @param args
     *         object array of arguments
     */
    @Override
    public void process(Object[] args) throws HiveException {
        // "GET /static/image/common/faq.gif HTTP/1.1"
        if (args.length == 1) {
            // 输入值
            String inputStr = args[0].toString();
            // 替换"
            inputStr = inputStr.replaceAll("\"", "");
            // 分割
            String[] splited = inputStr.split(" ");
            // 输出值
            if (3 == splited.length) {
                String method = splited[0];
                String url = splited[1];
                String protocol = splited[2];
                // 调用父类的forward方法进行输出
                super.forward(new String[]{method, url, protocol});
            }
        }
    }

    /**
     * Called to notify the UDTF that there are no more rows to process.
     * Clean up code or additional forward() calls can be made here.
     */
    @Override
    public void close() throws HiveException {
        super.forward(new String[]{""});
    }

}
