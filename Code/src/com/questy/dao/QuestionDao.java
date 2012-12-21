package com.questy.dao;

import com.questy.domain.Question;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao extends ParentDao {

    public static final Integer ROOT_QUESTION_REF = 0;

    public static Integer getMaxRefByNetworkId(
            Connection conn,
            Integer networkId) throws SQLException {

        conn = start(conn);

        String sql =
            "select max(`ref`) as `max_ref` " +
            "from `questions` " +
            "where `network_id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ResultSet rs = ps.executeQuery();

        Integer max = null;
        while (rs.next())
            max = DatabaseUtils.getInt(rs, "max_ref");

        end(conn, ps, rs);
        return max;
    }

    public static List<Question> findByNetworkIdAndText(
            Connection conn,
            Integer networkId,
            String searchText,
            Integer limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `questions` " +
            "where `network_id` = ? " +
            "and upper(`text`) like upper(?) " +
            "order by `total_answers` desc, `id` desc " +
            "limit ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setString(2, searchText);
        ps.setInt(3, limit);
        ResultSet rs = ps.executeQuery();

        List<Question> out = new ArrayList<Question>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static Question getByNetworkIdAndRef(
            Connection conn,
            Integer networkId,
            Integer ref) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `questions` " +
            "where `network_id` = ? " +
            "and `ref` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, ref);
        ResultSet rs = ps.executeQuery();

        Question out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static List<Question> getByNetworkId(
            Connection conn,
            Integer networkId) throws SQLException {

        conn = start(conn);

        Statement st = conn.createStatement();
        String sql = "select * from `questions` where `network_id` = " + networkId;
        ResultSet rs = st.executeQuery(sql);

        List<Question> q = new ArrayList<Question>();
        while (rs.next())
            q.add(loadPrimitives(rs));

        end(conn, st, rs);
        return q;
    }

    public static void updateTotalAnswersAndTotalOptionAnswers(
            Connection conn,
            Integer networkId,
            Integer questionRef,
            Integer totalAnswers,
            Integer totalOptionAnswers) throws SQLException {

        conn = start(conn);

        String sql =
            "update `questions` " +
            "set `total_answers` = ?, " +
            "`total_option_answers` = ? " +
            "where `network_id` = ? " +
            "and `ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, totalAnswers);
        ps.setInt(2, totalOptionAnswers);
        ps.setInt(3, networkId);
        ps.setInt(4, questionRef);
        ps.execute();

        end(conn, ps, null);
    }

    public static Integer insert(
            Connection conn,
            Integer userId,
            Integer networkId,
            Integer ref,
            String text,
            Integer points,
            Integer maxSelectedOptions,
            AnswerVisibilityEnum maxVisibility,
            AnswerVisibilityEnum defaultVisibility,
            Boolean allowAddOptions) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `questions` (" +
            "`created_on`, " +
            "`created_by`, " +
            "`network_id`, " +
            "`ref`, " +
            "`text`, " +
            "`points`, " +
            "`max_selected_options`, " +
            "`max_visibility`, " +
            "`default_visibility`, " +
            "`allow_add_options`" +
            ") values (NOW(), ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ps.setInt(3, ref);
        ps.setString(4, text.trim());
        ps.setInt(5, points);
        ps.setInt(6, maxSelectedOptions);
        ps.setInt(7, maxVisibility.getId());

        if (defaultVisibility == null)
            ps.setNull(8, Types.INTEGER);
        else
            ps.setInt(8, defaultVisibility.getId());

        ps.setBoolean(9, allowAddOptions);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    public static Integer deleteByNetworkIdAndRef (
            Connection conn,
            Integer networkId,
            Integer ref) throws SQLException {

        conn = start(conn);

        String sql =
            "delete " +
            "from `questions` " +
            "where `network_id` = ? " +
            "and `ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, ref);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }


    private static Question loadPrimitives (ResultSet rs) throws SQLException {
        Question out = new Question();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setRef(DatabaseUtils.getInt(rs, "ref"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setCreatedBy(DatabaseUtils.getInt(rs, "created_by"));
        out.setText(DatabaseUtils.getString(rs, "text"));
        out.setTotalAnswers(DatabaseUtils.getInt(rs, "total_answers"));
        out.setTotalOptionAnswers(DatabaseUtils.getInt(rs, "total_option_answers"));
        out.setPoints(DatabaseUtils.getInt(rs, "points"));
        out.setMaxSelectedOptions(DatabaseUtils.getInt(rs, "max_selected_options"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setMaxVisibility(DatabaseUtils.getInt(rs, "max_visibility"));
        out.setDefaultVisibility(DatabaseUtils.getInt(rs, "default_visibility"));
        out.setAllowAddOptions(DatabaseUtils.getBoolean(rs, "allow_add_options"));
        return out;
    }


}
