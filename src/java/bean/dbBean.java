package bean;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbBean {

    private String dbDriver = "org.postgresql.Driver";
    private Connection dbCon;

    public boolean connect() throws ClassNotFoundException, SQLException {
        Class.forName(dbDriver);
        System.out.println("-------------DB bean---------");
//        dbCon = DriverManager.getConnection("jdbc:oracle:thin://@10.179.0.68:1521:orcl", "vat", "vat");
//         dbCon = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521/orcl", "vat", "vat@123");
        dbCon = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MegVat", "vat", "vat");

        System.out.println("-------------dbCon----------" + dbCon);
//jdbc:postgresql://localhost:5432/thebestdatabase
        return true;
    }

    public void close() throws SQLException {
        dbCon.close();
    }

    public ResultSet execSQL(String sql) {
        ResultSet r = null;
        PreparedStatement st1 = null;
        try {
            System.out.println(sql);

            System.out.println("--------sql in db bean-------" + sql);
            Statement s = dbCon.createStatement();
            r = s.executeQuery(sql);
//            s.close();
            System.out.println("----executeQuery---" + r);

        } catch (SQLException ex) {
            System.out.println(" Exception in execSQL " + ex);
        }
        return (r == null) ? null : r;
    }
    
    public int execSQL2(String sql) {
        int r =0;
        PreparedStatement st1 = null;
        try {
            System.out.println(sql);

            System.out.println("--------create exec2 in db bean-------" + sql);
            Statement s = dbCon.createStatement();
            r = s.executeUpdate(sql);
            s.close();

            System.out.println("----executeQuery---" + r);

        } catch (SQLException ex) {
            System.out.println(" Exception in execSQL2 " + ex);
        }
        return r;
    }

    public int updateSQL(String sql) throws SQLException {
         System.out.println("--------update sql in db bean2-------" + sql);
        Statement s = dbCon.createStatement();
        int r = s.executeUpdate(sql);
        return (r == 0) ? 0 : r;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return dbCon.prepareStatement(sql);
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        return dbCon.prepareCall(sql);
    }

    public Statement createStatement() throws SQLException {
        return dbCon.createStatement();
    }

    public String nativeSQL(String sql) throws SQLException {
        return dbCon.nativeSQL(sql);
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        dbCon.setAutoCommit(autoCommit);
    }

    public boolean getAutoCommit() throws SQLException {
        return dbCon.getAutoCommit();
    }

    public void commit() throws SQLException {
        dbCon.commit();
    }

    public void rollback() throws SQLException {
        dbCon.rollback();
    }

    public boolean isClosed() throws SQLException {
        return dbCon.isClosed();
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return dbCon.getMetaData();
    }

    public void setReadOnly(boolean readOnly) throws SQLException {
        dbCon.setReadOnly(readOnly);
    }

    public boolean isReadOnly() throws SQLException {
        return dbCon.isReadOnly();
    }

    public void setCatalog(String catalog) throws SQLException {
        dbCon.setCatalog(catalog);
    }

    public String getCatalog() throws SQLException {
        return dbCon.getCatalog();
    }

    public void setTransactionIsolation(int level) throws SQLException {
        dbCon.setTransactionIsolation(level);
    }

    public int getTransactionIsolation() throws SQLException {
        return dbCon.getTransactionIsolation();
    }

    public SQLWarning getWarnings() throws SQLException {
        return dbCon.getWarnings();
    }

    public void clearWarnings() throws SQLException {
        dbCon.clearWarnings();
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Map<String, Class<?>> getTypeMap() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setHoldability(int holdability) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getHoldability() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Savepoint setSavepoint() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Clob createClob() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Blob createBlob() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public NClob createNClob() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SQLXML createSQLXML() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isValid(int timeout) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getClientInfo(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Properties getClientInfo() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
