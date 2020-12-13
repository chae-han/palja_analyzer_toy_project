package com.chan.paljachance.crawler.libTestExamples;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//makeJdbcTemplete

public class JdbcContext {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy stat) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = dataSource.getConnection();
            // ps = c.prepareStatement("select count(*) from users");
            //ps = makeStatement(c);
            //StatementStrategy statementStrategy = new DeleteAllStatement();
            //ps = statementStrategy.makeStatement(c);
            ps = stat.makeStatement(c);

            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {

                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public void executeSqlWithoutParam(final String query) {
        workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection c) throws SQLException {
                return c.prepareStatement(query);
            }
        });
    }
}

public class userDao {
    ...
    private JdbcContext jdbcContext;
    private DataSource dataSource;

    userDao(JdbcContext jdbcContext, DataSource dataSource) {
        this.jdbcContext = jdbcContext;
        this.dataSource = dataSource;
        this.jdbcContext.setDataSource(dataSource);
    }

    public int deleteAll() throws SQLException {

        //StatementStrategy statementStrategy = new DeleteAllStatement();
        this.jdbcContext.executeSqlWithoutParam("delete from users");
    }

    public void add(final User user) throws SQLException {

        this.jdbcContext.workWithStatementStrategy(new StatementStrategy {

            public PreparedStatement makeStatement(Connection c) throws SQLException {
                PreparedStatement ps;
                ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());
                return ps;
            }
        });

    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stat) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = dataSource.getConnection();
            // ps = c.prepareStatement("select count(*) from users");
            //ps = makeStatement(c);
            //StatementStrategy statementStrategy = new DeleteAllStatement();
            //ps = statementStrategy.makeStatement(c);
            ps = stat.makeStatement(c);

            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {

                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }
    }
//    private PreparedStatement makeStatement(Connection c) throws SQLException {
//        PreparedStatement ps;
//        ps = c.prepareStatement("delete from users");
//        return ps;
//    }
//    abstract protected PreparedStatement makeStatement(Connection c) throws SQLException;
}

public interface StatementStrategy {
    PreparedStatement makeStatement(Connection c) throws SQLException;
}

public class DeleteAllStatement implements StatementStrategy {
    public PreparedStatement makeStatement(Connection c) throws SQLException {
        PreparedStatement ps;
        ps = c.prepareStatement("delete from users");
        return ps;
    }
}

public class AddStatement implements StatementStrategy {
    User user;

    public AddStatement(User user){
        this.user = user;
    }

    public PreparedStatement makeStatement(Connection c) throws SQLException {
        PreparedStatement ps;
        ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
        return ps;
    }
}


