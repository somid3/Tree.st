package com.questy.dao;

import com.questy.domain.Question;
import com.questy.domain.QuestionOption;
import com.questy.helpers.SqlLimit;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionOptionDao extends ParentDao {

    public static List<QuestionOption> findByNetworkIdAndText(
            Connection conn,
            Integer networkId,
            String searchText,
            SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `question_options` " +
            "where `network_id` = ? " +
            "and upper(`text`) like upper(?) " +
            "order by `total_answers` desc, `id` desc " +
            "limit ?,?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setString(2, searchText);
        ps.setInt(3, limit.getStartFrom());
        ps.setInt(4, limit.getDuration());
        ResultSet rs = ps.executeQuery();

        List<QuestionOption> out = new ArrayList<QuestionOption>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static List<QuestionOption> getByNetworkIdAndQuestionRef(
            Connection conn,
            Integer networkId,
            Integer questionRef) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `question_options` " +
            "where `network_id` = ? " +
            "and `question_ref` = ? ;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, questionRef);
        ResultSet rs = ps.executeQuery();

        List<QuestionOption> out = new ArrayList<QuestionOption>();

        while (rs.next()) {
            out.add(loadPrimitives(rs));
        }

        end(conn, ps, rs);
        return out;
    }

    public static QuestionOption getByNetworkIdAndQuestionRefAndOptionRef(
            Connection conn,
            Integer networkId,
            Integer questionRef,
            Integer ref) throws SQLException {

        conn = start(conn);

        Statement st = conn.createStatement();
        String sql = "select * from `question_options` where `network_id` = " + networkId + " and `question_ref` = " + questionRef + " and `ref` = " + ref +";";
        ResultSet rs = st.executeQuery(sql);

        QuestionOption qo = null;
        while (rs.next())
            qo = loadPrimitives(rs);

        end(conn, st, rs);
        return qo;
    }

    public static Integer insert(
            Connection conn,
            Integer networkId,
            Integer questionRef,
            Integer ref,
            Integer userId,
            String text) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `question_options` (" +
            "`created_on`, " +
            "`network_id`, " +
            "`question_ref`, " +
            "`ref`, " +
            "`text`, " +
            "`created_by` " +
            ") values (NOW(), ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, networkId);
        ps.setInt(2, questionRef);
        ps.setInt(3, ref);
        ps.setString(4, text.trim());
        ps.setInt(5, userId);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    public static void incrementTotalAnswers(
            Connection conn,
            Integer networkId,
            Integer questionRef,
            List<Integer> refs) throws SQLException {

        conn = start(conn);

        String sql =
            "update `question_options` " +
            "set `total_answers` = `total_answers` + 1 " +
            "where `network_id` = ? " +
            "and `question_ref` = ? " +
            "and `ref` = ? ;";

        PreparedStatement ps = conn.prepareStatement(sql);
        for (Integer ref : refs) {
            ps.setInt(1, networkId);
            ps.setInt(2, questionRef);
            ps.setInt(3, ref);
            ps.execute();
        }

        end(conn, ps, null);
    }

    public static void decreaseTotalAnswers(
            Connection conn,
            Integer networkId,
            Integer questionRef,
            List<Integer> refs) throws SQLException {

        conn = start(conn);

        String sql =
            "update `question_options` " +
            "set `total_answers` = `total_answers` - 1 " +
            "where `network_id` = ? " +
            "and `question_ref` = ? " +
            "and `ref` = ? ;";

        PreparedStatement ps = conn.prepareStatement(sql);
        for (Integer ref : refs) {
            ps.setInt(1, networkId);
            ps.setInt(2, questionRef);
            ps.setInt(3, ref);
            ps.execute();
        }

        end(conn, ps, null);
    }

    public static Integer getMaxRefByQuestionRef(
            Connection conn,
            Integer networkId,
            Integer questionRef) throws SQLException {

        conn = start(conn);

        String sql =
         "select max(`ref`) as `max_ref` " +
         "from `question_options` " +
         "where `network_id` = ? " +
         "and `question_ref` = ? ;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, questionRef);
        ResultSet rs = ps.executeQuery();

        Integer max = null;
        while (rs.next())
            max = DatabaseUtils.getInt(rs, "max_ref");

        end(conn, ps, rs);
        return max;
    }

    public static Integer deleteByNetworkIdAndQuestionRef (
            Connection conn,
            Integer networkId,
            Integer questionRef) throws SQLException {

        conn = start(conn);

        String sql =
            "delete " +
            "from `question_options` " +
            "where `network_id` = ? " +
            "and `question_ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, questionRef);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    private static QuestionOption loadPrimitives (ResultSet rs) throws SQLException {
        QuestionOption out = new QuestionOption();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setCreatedBy(DatabaseUtils.getInt(rs, "created_by"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setQuestionRef(DatabaseUtils.getInt(rs, "question_ref"));
        out.setRef(DatabaseUtils.getInt(rs, "ref"));
        out.setText(DatabaseUtils.getString(rs, "text"));
        out.setTotalAnswers(DatabaseUtils.getInt(rs, "total_answers"));
        return out;
    }
}
