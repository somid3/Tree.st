package com.questy.dao;

import com.questy.domain.ActiveAnswer;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.helpers.SqlLimit;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActiveAnswerDao extends ParentDao {

    public static Integer deleteByUserIdAndNetworkIdAndQuestionRef(Connection conn, Integer userId, Integer networkId, Integer questionRef) throws SQLException  {
        conn = start(conn);

        String sql =
            "delete " +
            "from `active_answers` " +
            "where `network_id` = ? " +
            "and `user_id` = ? " +
            "and `question_ref` = ? ;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setInt(3, questionRef);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    public static List<ActiveAnswer> getByNetworkIdAndQuestionRefAndOptionRef(
            Connection conn,
            Integer networkId,
            Integer questionRef,
            Integer optionRef,
            AnswerVisibilityEnum lowestVisibility,
            Integer limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `active_answers` " +
            "where `network_id` = ? " +
            "and `question_ref` = ? " +
            "and `option_ref` = ? " +
            "and `visibility` >= ? " +
            "order by `created_on` desc, `id` desc limit ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, questionRef);
        ps.setInt(3, optionRef);
        ps.setInt(4, lowestVisibility.getValue());
        ps.setInt(5, limit);

        ResultSet rs = ps.executeQuery();

        List<ActiveAnswer> out = new ArrayList<ActiveAnswer>();
        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static List<ActiveAnswer> getByNetworkIdAndQuestionRefAndUserId(
                Connection conn,
                Integer networkId,
                Integer questionRef,
                Integer userId,
                AnswerVisibilityEnum lowestVisibility,
                SqlLimit limit) throws SQLException {

            conn = start(conn);

            String sql =
                "select * " +
                "from `active_answers` " +
                "where `network_id` = ? " +
                "and `question_ref` = ? " +
                "and `user_id` = ? " +
                "and `visibility` >= ? " +
                "order by `created_on` desc, `id` desc " +
                "limit ?, ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, networkId);
            ps.setInt(2, questionRef);
            ps.setInt(3, userId);
            ps.setInt(4, lowestVisibility.getValue());
            ps.setInt(5, limit.getStartFrom());
            ps.setInt(6, limit.getDuration());

            ResultSet rs = ps.executeQuery();

            List<ActiveAnswer> out = new ArrayList<ActiveAnswer>();
            while (rs.next())
                out.add(loadPrimitives(rs));

            end(conn, ps, rs);
            return out;
        }

    public static Integer deleteByNetworkIdAndUserId(
            Connection conn,
            Integer networkId,
            Integer userId,
            Integer questionRef) throws SQLException {

        conn = start(conn);

        String sql =
            "delete " +
            "from `active_answers` " +
            "where `user_id` = ? " +
            "and `network_id` = ? " +
            "and `question_ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ps.setInt(3, questionRef);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    public static Integer deleteByNetworkIdAndQuestionRef (
            Connection conn,
            Integer networkId,
            Integer questionRef) throws SQLException {

        conn = start(conn);

        String sql =
            "delete " +
            "from `active_answers` " +
            "where `network_id` = ? " +
            "and `question_ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, questionRef);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    public static Integer insert(
            Connection conn,
            Integer networkId,
            Integer questionRef,
            Integer optionRef,
            Integer userId,
            AnswerVisibilityEnum visibility) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `active_answers` (" +
            "`created_on`, " +
            "`network_id`, " +
            "`question_ref`, " +
            "`option_ref`, " +
            "`user_id`, " +
            "`visibility`" +
            ") values (NOW(), ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, networkId);
        ps.setInt(2, questionRef);
        ps.setInt(3, optionRef);
        ps.setInt(4, userId);
        ps.setInt(5, visibility.getValue());
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static ActiveAnswer loadPrimitives (ResultSet rs) throws SQLException {
        ActiveAnswer out = new ActiveAnswer();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setQuestionRef(DatabaseUtils.getInt(rs, "question_ref"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setOptionRef(DatabaseUtils.getInt(rs, "option_ref"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setVisibility(
            AnswerVisibilityEnum.getByValue(DatabaseUtils.getInt(rs, "visibility"))
        );
        return out;
    }

}
