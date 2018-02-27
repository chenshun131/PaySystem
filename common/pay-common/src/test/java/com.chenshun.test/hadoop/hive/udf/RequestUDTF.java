package com.chenshun.test.hadoop.hive.udf;

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
 * 原始数据:
 * "GET /static/image/common/faq.gif HTTP/1.1"
 * 输出数据：
 * method  url  protocol
 * <p>
 * Generates a variable number of output rows for a single input row.
 */
public class RequestUDTF extends GenericUDTF {

    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        if (argOIs.getAllStructFieldRefs().size() != 1) {
            throw new UDFArgumentException("参数只能有一个");
        }

        // List<String> structFieldNames
        ArrayList<String> fieldList = new ArrayList<String>();
        fieldList.add("method");
        fieldList.add("url");
        fieldList.add("protocol");
        // List<ObjectInspector> structFieldObjectInspectors
        ArrayList<ObjectInspector> inspectorList = new ArrayList<ObjectInspector>();
        inspectorList.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        inspectorList.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        inspectorList.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        // StandardStructObjectInspector
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldList, inspectorList);
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
            // 输入的值
            String inputStr = args[0].toString();
            // 替换"
            inputStr = inputStr.replaceAll("\"", "");
            // 分割
            String[] splited = inputStr.split(" ");
            // 输出的值
            if (3 == splited.length) {
                String method = splited[0];
                String url = splited[1];
                String protocol = splited[2];
                // 调用父类的forward方法
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
