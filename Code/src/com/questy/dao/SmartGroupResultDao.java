package com.questy.dao;

import com.questy.domain.SmartGroupResult;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.helpers.SqlLimit;
import com.questy.helpers.UserScore;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SmartGroupResultDao extends  ParentDao {

    public static List<SmartGroupResult> getByNetworkIdAndRef (
            Connection conn,
            Integer networkId,
            Integer smartGroupRef,
            SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `smart_group_results` " +
            "where `network_id` = ? " +
            "and `ref` = ? " +
            "order by `score` desc, `latest_date` desc, `id` desc " +
            "limit ?, ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ps.setInt(3, limit.getStartFrom());
        ps.setInt(4, limit.getDuration());

        ResultSet rs = ps.executeQuery();

        List<SmartGroupResult> out = new ArrayList<SmartGroupResult>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static SmartGroupResult getByNetworkIdAndRefAndUserId (
            Connection conn,
            Integer networkId,
            Integer smartGroupRef,
            Integer userId) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `smart_group_results` " +
            "where `network_id` = ? " +
            "and `ref` = ? " +
            "and `user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ps.setInt(3, userId);
        ResultSet rs = ps.executeQuery();

        SmartGroupResult out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static Integer deleteByNetworkIdAndRef(
            Connection conn,
            Integer networkId,
            Integer smartGroupRef) throws SQLException {

        conn = start(conn);

        String sql =
            "delete " +
            "from `smart_group_results` " +
            "where `network_id` = ? " +
            "and `ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    public static Integer insert (
            Connection conn,
            Integer networkId,
            Integer smartGroupRef,
            UserScore score) throws SQLException {

        conn = start(conn);

        Timestamp sqlDate = new Timestamp(score.getLatestDate().getTime());

        String sql =
            "insert into `smart_group_results` (" +
            "`network_id`, " +
            "`ref`, " +
            "`user_id`, " +
            "`score`, " +
            "`lowest_visibility`, " +
            "`latest_date`, " +
            "`total_options_met`, " +
            "`total_questions_met`, " +
            "`query_met`, " +
            "`flare`" +
            ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ps.setInt(3, score.getUserId());
        ps.setDouble(4,  score.getScore());
        ps.setInt(5, score.getLowestVisibility().getId());
        ps.setTimestamp(6, sqlDate);
        ps.setInt(7,  score.getTotalOptionsMet());
        ps.setInt(8,  score.getTotalQuestionsMet());
        ps.setBoolean(9, score.isQueryMet());
        ps.setString(10, score.getFlare());
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static SmartGroupResult loadPrimitives (ResultSet rs) throws SQLException {
        SmartGroupResult out = new SmartGroupResult();
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setRef(DatabaseUtils.getInt(rs, "ref"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setScore(DatabaseUtils.getDouble(rs, "score"));
        out.setLowestVisibility(
            AnswerVisibilityEnum.getById(DatabaseUtils.getInt(rs, "lowest_visibility"))
        );
        out.setLatestDate(DatabaseUtils.getTimestamp(rs, "latest_date"));
        out.setTotalOptionsMet(DatabaseUtils.getInt(rs, "total_options_met"));
        out.setTotalQuestionsMet(DatabaseUtils.getInt(rs, "total_questions_met"));
        out.setQueryMet(DatabaseUtils.getBoolean(rs, "query_met"));
        out.setFlare(DatabaseUtils.getString(rs, "flare"));
        return out;
    }

}
