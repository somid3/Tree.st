package com.questy.dao;

import com.questy.domain.Answer;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.helpers.SqlLimit;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDao extends ParentDao {

    /**
     * Provides the last answers that the user provided
     */
    public static List<Answer> getLastByUserIdAndNetworkId(Connection conn, Integer userId, Integer networkId, SqlLimit limit) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `answers` " +
            "where `user_id` = ? " +
            "and `network_id` = ? " +
            "order by `created_on` desc, `ref` desc " +
            "limit ?,?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ps.setInt(3, limit.getStartFrom());
        ps.setInt(4, limit.getDuration());
        ResultSet rs = ps.executeQuery();

        List<Answer> out = new ArrayList<Answer>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static List<Answer> getLastByUserIdAndNetworkIdAndAgain(Connection conn, Integer userId, Integer networkId, Boolean again, SqlLimit limit) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `answers` " +
            "where `user_id` = ? " +
            "and `network_id` = ? " +
            "and `again` = ? " +
            "order by `created_on` desc, `ref` desc " +
            "limit ?,?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ps.setBoolean(3, again);
        ps.setInt(4, limit.getStartFrom());
        ps.setInt(5, limit.getDuration());
        ResultSet rs = ps.executeQuery();

        List<Answer> out = new ArrayList<Answer>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static Answer getLastByUserIdAndNetworkIdAndAnsweredQuestionRef(Connection conn, Integer userId, Integer networkId, Integer questionRef) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `answers` " +
            "where `user_id` = ? " +
            "and `network_id` = ? " +
            "and `question_ref` = ? " +
            "order by `created_on` desc, `ref` desc " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ps.setInt(3, questionRef);
        ResultSet rs = ps.executeQuery();

        Answer out = null;
        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static Answer getLastByUserIdAndNetworkIdAndAnsweredQuestionRefAndAgain(Connection conn, Integer userId, Integer networkId, Integer questionRef, Boolean again) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `answers` " +
            "where `user_id` = ? " +
            "and `network_id` = ? " +
            "and `question_ref` = ? " +
            "and `again` = ? " +
            "order by `created_on` desc, `ref` desc " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ps.setInt(3, questionRef);
        ps.setBoolean(4, again);
        ResultSet rs = ps.executeQuery();

        Answer out = null;
        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static Integer deleteByUserIdAndNetworkId(Connection conn, Integer userId, Integer networkId) throws SQLException  {
       conn = start(conn);

       String sql =
           "delete " +
           "from `answers` " +
           "where `network_id` = ? " +
           "and `user_id` = ?;";

       PreparedStatement ps = conn.prepareStatement(sql);
       ps.setInt(1, networkId);
       ps.setInt(2, userId);
       Integer out = ps.executeUpdate();

       end(conn, ps, null);
       return out;
   }

    public static Integer deleteByNetworkIdAndUserIdAndRef(Connection conn, Integer networkId, Integer userId, Integer ref) throws SQLException {
        conn = start(conn);

        String sql =
            "delete from `answers` " +
            "where `user_id` = ? " +
            "and `network_id` = ? " +
            "and `ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ps.setInt(3, ref);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    public static Integer deleteByNetworkIdAndAnsweredQuestionRef (Connection conn, Integer networkId, Integer questionRef) throws SQLException {
        conn = start(conn);

        String sql =
            "delete " +
            "from `answers` " +
            "where `network_id` = ? " +
            "and `question_ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, questionRef);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    private static Integer getMaxRefByUserIdAndApp (
            Connection conn,
            Integer networkId,
            Integer userId) throws SQLException {

        conn = start(conn);

        String sql =
            "select max(`ref`) as `max_ref` " +
            "from `answers` " +
            "where `network_id` = ? " +
            "and `user_id` = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ResultSet rs = ps.executeQuery();

        Integer max = null;
        while (rs.next())
            max = DatabaseUtils.getInt(rs, "max_ref");

        end(conn, ps, rs);
        return max;
    }

    public static Integer getNextRefByNetworkIdAndUserId (
            Connection conn,
            Integer networkId,
            Integer userId) throws SQLException {

        // Retrieving max ref
        Integer maxRef = getMaxRefByUserIdAndApp(conn, networkId, userId);

        /* Creating next ref, starting with 1 and not 0 */
        Integer nextRef = 1;
        if (maxRef != null)
            nextRef = maxRef + 1;

        return nextRef;
    }

    public static Integer insert(Connection conn, Integer networkId, Integer questionRef, Integer userId, Integer ref, AnswerVisibilityEnum visibility, Boolean again) throws SQLException {
        conn = start(conn);

        String sql =
         "insert into `answers` (" +
         "`created_on`, " +
         "`question_ref`, " +
         "`network_id`, " +
         "`user_id`, " +
         "`ref`, " +
         "`visibility`, " +
         "`again`" +
         ") values (NOW(), ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, questionRef);
        ps.setInt(2, networkId);
        ps.setInt(3, userId);
        ps.setInt(4, ref);
        ps.setInt(5, visibility.getId());
        ps.setBoolean(6, again);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static Answer loadPrimitives (ResultSet rs) throws SQLException {
        Answer out = new Answer();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setRef(DatabaseUtils.getInt(rs, "ref"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setQuestionRef(DatabaseUtils.getInt(rs, "question_ref"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setAgain(DatabaseUtils.getBoolean(rs, "again"));
        out.setVisibility(
            AnswerVisibilityEnum.getById(DatabaseUtils.getInt(rs, "visibility"))
        );
        return out;
    }

}
