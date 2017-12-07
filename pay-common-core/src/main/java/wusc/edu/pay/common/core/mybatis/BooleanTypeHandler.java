package wusc.edu.pay.common.core.mybatis;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义Boolean类型转换器.
 *
 * @描述: java中的boolean和jdbc中的int之间转换;true-1;false-0.
 * @作者: WuShuicheng .
 * @创建时间: 2013-7-25,下午4:19:11 .
 * @版本: 1.0 .
 */
public class BooleanTypeHandler implements TypeHandler {

    @Override
    public Object getResult(ResultSet arg0, int arg1) throws SQLException {
        int num = arg0.getInt(arg1);
        Boolean rt = Boolean.FALSE;
        if (num == 1) {
            rt = Boolean.TRUE;
        }
        return rt;
    }

    @Override
    public Object getResult(CallableStatement arg0, int arg1) throws SQLException {
        Boolean b = arg0.getBoolean(arg1);
        return b ? 1 : 0;
    }

    @Override
    public void setParameter(PreparedStatement arg0, int arg1, Object arg2, JdbcType arg3) throws SQLException {
        Boolean b = (Boolean) arg2;
        int value = b ? 1 : 0;
        arg0.setInt(arg1, value);
    }

    @Override
    public Object getResult(ResultSet arg0, String arg1) throws SQLException {
        int num = arg0.getInt(arg1);
        Boolean rt = Boolean.FALSE;
        if (num == 1) {
            rt = Boolean.TRUE;
        }
        return rt;
    }

}
