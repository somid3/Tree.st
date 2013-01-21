package com.questy.dao;

import com.questy.domain.FlowRule;
import com.questy.domain.Question;
import com.questy.domain.QuestionOption;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlowRuleDao extends ParentDao {

    public static List<FlowRule> getByFromQuestionRefAndNetworkId (
            Connection conn,
            Integer fromQuestionRef,
            Integer networkId) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `flow_rules` " +
            "where `from_question_ref` = ? " +
            "and `network_id` = ? " +
            "order by `order` asc;";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, fromQuestionRef);
        ps.setInt(2, networkId);
        ResultSet rs = ps.executeQuery();

        List<FlowRule> frs = new ArrayList<FlowRule>();
        while (rs.next())
            frs.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return frs;
    }

    public static List<FlowRule> getByToQuestionRefAndNetworkId (
            Connection conn,
            Integer toQuestionRef,
            Integer networkId) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `flow_rules` " +
            "where `to_question_ref` = ? "+
            "and `network_id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, toQuestionRef);
        ps.setInt(2, networkId);
        ResultSet rs = ps.executeQuery();

        List<FlowRule> frs = new ArrayList<FlowRule>();
        while (rs.next())
            frs.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return frs;
    }

    public static Integer getMaxOrderByNetworkIdAndFromQuestionRefAndOptionRef (
        Connection conn,
        Integer networkId,
        Integer fromQuestionRef,
        Integer optionRef) throws SQLException {

        conn = start(conn);

        String sql =
            "select max(`order`) as `max_order` " +
            "from `flow_rules` " +
            "where `network_id` = ? " +
            "and `from_question_ref` = ? " +
            "and `from_option_ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, fromQuestionRef);
        ps.setInt(3, optionRef);
        ResultSet rs = ps.executeQuery();

        Integer max = null;
        while (rs.next())
            max = DatabaseUtils.getInt(rs, "max_order");

        end(conn, ps, rs);
        return max;

    }

    public static Integer insert (
            Connection conn,
            Integer networkId,
            Integer fromQuestionRef,
            Integer fromOptionRef,
            Integer toQuestionRef,
            Integer order) throws SQLException {

        conn = start(conn);

        /**
         * Validating: if this is a root question, it can not also depend on a particular option
         * from a previous answer because its impossible for there to be a previous answer
         */
        if (fromQuestionRef.equals(Question.ROOT_QUESTION_REF) && !fromOptionRef.equals(QuestionOption.ANY_OPTION_REF))
            throw new RuntimeException ("If from question is root, then option ref must be any");

        String sql =
            "insert into `flow_rules` (" +
            "`created_on`, " +
            "`network_id`, " +
            "`from_question_ref`, " +
            "`from_option_ref`, " +
            "`to_question_ref`, " +
            "`order`" +
            ") values (NOW(), ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, networkId);
        ps.setInt(2, fromQuestionRef);
        ps.setInt(3, fromOptionRef);
        ps.setInt(4, toQuestionRef);
        ps.setInt(5, order);

        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    public static Integer deleteByNetworkIdAndFromQuestionRef (
            Connection conn,
            Integer networkId,
            Integer questionRef) throws SQLException {

        conn = start(conn);

        String sql =
            "delete " +
            "from `flow_rules` " +
            "where `network_id` = ? " +
            "and `from_question_ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, questionRef);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    public static Integer deleteByNetworkIdAndToQuestionRef (
            Connection conn,
            Integer networkId,
            Integer questionRef) throws SQLException {

        conn = start(conn);

        String sql =
            "delete " +
            "from `flow_rules` " +
            "where `network_id` = ? " +
            "and `to_question_ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, questionRef);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }



    private static FlowRule loadPrimitives (ResultSet rs) throws SQLException {
        FlowRule out = new FlowRule();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setOrder(DatabaseUtils.getInt(rs, "order"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setFromQuestionRef(DatabaseUtils.getInt(rs, "from_question_ref"));
        out.setToQuestionRef(DatabaseUtils.getInt(rs, "to_question_ref"));
        out.setFromOptionRef(DatabaseUtils.getInt(rs, "from_option_ref"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        return out;
    }


}
