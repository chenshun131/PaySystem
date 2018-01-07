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
    public void setParameter(PreparedStatement rs, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        Boolean b = (Boolean) parameter;
        int value = b ? 1 : 0;
        rs.setInt(i, value);
    }

    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        int num = rs.getInt(columnName);
        Boolean rt = Boolean.FALSE;
        if (num == 1) {
            rt = Boolean.TRUE;
        }
        return rt;
    }

    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        int num = rs.getInt(columnIndex);
        Boolean rt = Boolean.FALSE;
        if (num == 1) {
            rt = Boolean.TRUE;
        }
        return rt;
    }

    @Override
    public Object getResult(CallableStatement rs, int columnIndex) throws SQLException {
        Boolean b = rs.getBoolean(columnIndex);
        return b ? 1 : 0;
    }

}
