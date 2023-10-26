package ru.liga.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.geometric.PGpoint;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class PGPointType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[] {Types.VARCHAR};
    }


    @Override
    public Class<PGpoint> returnedClass() {
        return PGpoint.class;
    }

    @Override
    public boolean equals(Object o, Object o1) {
        return o.equals(o1);
    }

    @Override
    public int hashCode(Object o) {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        if (strings.length == 1) {
            if (resultSet.wasNull() || resultSet.getObject(strings[0]) == null) {
                return null;
            } else {
                return new PGpoint(resultSet.getObject(strings[0]).toString());
            }
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if (o == null) {
            preparedStatement.setNull(i, Types.OTHER);
        } else {
            preparedStatement.setObject(i, o, Types.OTHER);
        }
    }

    @Override
    public Object deepCopy(Object o){
        return o;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) {
        return serializable;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) {
        return o;
    }
}
