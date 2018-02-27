package com.chenshun.test.hadoop.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * User: chenshun131 <p />
 * Time: 16/12/14 05:52  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class HClient {

    public static HTable getTable(String name) throws Exception {
        // create a hbase configuration instance
        Configuration conf = HBaseConfiguration.create();
        // create a htabel instance
        return new HTable(conf, name);
    }

    /**
     * get data from hbase table
     * get:
     * get 'student:stu_info','20161204_1001'
     * get 'student:stu_info','20161204_1001','info'
     * get 'student:stu_info','20161204_1001','info:name'
     *
     * @throws Exception
     */
    public static void getData(HTable table) throws Exception {
        // create a get instance
        Get get = new Get(Bytes.toBytes("20161204_1003"));
        // configure the get
        // get.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"));
        // get.addFamily(family);
        // add to table
        Result rs = table.get(get);
        // print
        for (Cell cell : rs.rawCells()) {
            System.out.println(Bytes.toString(CellUtil.cloneRow(cell))
                    + "->" +
                    Bytes.toString(CellUtil.cloneFamily(cell))
                    + "->" +
                    Bytes.toString(CellUtil.cloneQualifier(cell))
                    + "->" +
                    Bytes.toString(CellUtil.cloneValue(cell))
                    + "->" +
                    cell.getTimestamp()
            );
            System.out.println("---------------------------------------------------");
        }
    }

    /**
     * put the data to hbase table
     * put:
     * put 'student:stu_info','20161204_1003','info:name','laosan'
     *
     * @param table
     * @throws Exception
     * @throws RetriesExhaustedWithDetailsException
     * @throws Exception
     */

    public static void putData(HTable table) throws RetriesExhaustedWithDetailsException, Exception {
        // create a put instance
        Put put = new Put(Bytes.toBytes("20161204_1003"));
        put.add(
                Bytes.toBytes("info"),
                Bytes.toBytes("name"),
                Bytes.toBytes("laosan")
        );
        // add to table
        table.put(put);
        getData(table);
    }

    /**
     * delete data from hbase table
     * delete
     * delete 'student:stu_info', '20161204_1003', 'info:age'
     *
     * @param table
     * @throws Exception
     * @throws Exception
     */
    public static void deleteData(HTable table) throws Exception {
        // create a delete instance
        Delete delete = new Delete(Bytes.toBytes("20161204_1003"));
        // conf the delete
        delete.deleteColumn(Bytes.toBytes("info"), Bytes.toBytes("age"));
        // delete.deleteColumns(family, qualifier);
        // add the delete
        table.delete(delete);
        getData(table);
    }

    /**
     * scan the data from the hbase table
     * scan :
     * scan 'student:stu_info'
     *
     * @param table
     * @throws Exception
     * @throws Exception
     */

    public static void scanData(HTable table) throws Exception {
        // create a scan instance
        Scan scan = new Scan();
        // add to table
        ResultScanner rsscan = table.getScanner(scan);
        // print
        for (Result rs : rsscan) {
            System.out.println(Bytes.toString(rs.getRow()));
            for (Cell cell : rs.rawCells()) {
                System.out.println(
                        Bytes.toString(CellUtil.cloneRow(cell))
                                + "->" +
                                Bytes.toString(CellUtil.cloneFamily(cell))
                                + "->" +
                                Bytes.toString(CellUtil.cloneQualifier(cell))
                                + "->" +
                                Bytes.toString(CellUtil.cloneValue(cell))
                                + "->" +
                                cell.getTimestamp()
                );
            }
            System.out.println("------------------------------");
        }
    }

    /**
     * range scan
     *
     * @param table
     * @throws Exception
     */
    public static void rangeScan(HTable table) throws Exception {
        // create a scan
        Scan scan = new Scan();
        // conf the scan
        // scan.addColumn(family, qualifier);
        // scan.addFamily(Bytes.toBytes("info"));
        // scan.setStartRow(Bytes.toBytes("20161204_1002"));
        // scan.setStopRow(Bytes.toBytes("20161204_1003"));

        Filter filter = new PrefixFilter(Bytes.toBytes("2016121"));
        scan.setFilter(filter);

        // 特殊配置
        // 设置是否开启缓存
        scan.setCacheBlocks(true);
        // 缓存的条数
        scan.setCaching(100);
        // 设置每次取多少条
        scan.setBatch(10);
        // 这两个参数共同决定了RPC请求次数

        // add to table
        ResultScanner rsscan = table.getScanner(scan);
        // print
        for (Result rs : rsscan) {
            System.out.println(Bytes.toString(rs.getRow()));
            for (Cell cell : rs.rawCells()) {
                System.out.println(
                        Bytes.toString(CellUtil.cloneRow(cell))
                                + "->" +
                                Bytes.toString(CellUtil.cloneFamily(cell))
                                + "->" +
                                Bytes.toString(CellUtil.cloneQualifier(cell))
                                + "->" +
                                Bytes.toString(CellUtil.cloneValue(cell))
                                + "->" +
                                cell.getTimestamp()
                );
            }
            System.out.println("------------------------------");
        }
    }

    public static void main(String[] args) throws Exception {
        HTable table = getTable("student:stu_info");
        // getData(table);
        // putData(table);
        // deleteData(table);
        // scanData(table);
        rangeScan(table);
    }

}
