package com.questy.dao;

import com.questy.domain.AnswerOption;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerOptionDao extends ParentDao {

    public static final Integer SKIP_OPTION_REF = 0;

    public static List<AnswerOption> getByUserIdAndNetworkIdAndAnswerRef(
            Connection conn,
            Integer userId,
            Integer networkId,
            Integer answerRef) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `answer_options` " +
            "where `user_id` = ? " +
            "and `network_id` = ? " +
            "and `answer_ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ps.setInt(3, answerRef);
        ResultSet rs = ps.executeQuery();

        List<AnswerOption> out = new ArrayList<AnswerOption>();
        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static Integer deleteByNetworkIdAndUserIdAndId (
            Connection conn,
            Integer networkId,
            Integer userId,
            Integer answerRef) throws SQLException {

        conn = start(conn);

        String sql =
            "delete " +
            "from `answer_options` " +
            "where `network_id` = ? " +
            "and `user_id` = ? " +
            "and `answer_ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setInt(3, answerRef);
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
            "from `answer_options` " +
            "where `network_id` = ? " +
            "and `question_ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, questionRef);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }


    public static List<Integer> insert (
            Connection conn,
            Integer networkId,
            Integer questionRef,
            Integer answerRef,
            Integer userId,
            List<Integer> optionRefs) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `answer_options` (" +
            "`answer_ref`, " +
            "`question_ref`, " +
            "`network_id`, " +
            "`user_id`, " +
            "`option_ref`" +
            ") values (?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        List<Integer> generatedIds = new ArrayList<Integer>();

        for(Integer optionRef : optionRefs) {

            ps.setInt(1, answerRef);
            ps.setInt(2, questionRef);
            ps.setInt(3, networkId);
            ps.setInt(4, userId);
            ps.setInt(5, optionRef);
            ps.execute();

            generatedIds.add(DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys()));
        }

        end(conn, ps, null);
        return generatedIds;
    }

    private static AnswerOption loadPrimitives (ResultSet rs) throws SQLException {
        AnswerOption out = new AnswerOption();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setOptionRef(DatabaseUtils.getInt(rs, "option_ref"));
        out.setAnswerRef(DatabaseUtils.getInt(rs, "answer_ref"));
        out.setQuestionRef(DatabaseUtils.getInt(rs, "question_ref"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        return out;
    }

}
