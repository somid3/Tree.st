package com.questy.dao;

import com.questy.domain.SharedComment;
import com.questy.domain.SharedVote;
import com.questy.enums.SharedVoteEnum;
import com.questy.ifaces.SharedVotable;
import com.questy.utils.DatabaseUtils;

import java.sql.*;

public class SharedVoteDao extends ParentDao {

    public static SharedVote getByAllFields (
        Integer userId,
        Integer networkId,
        Integer smartGroupRef,
        Integer sharedItemRef,
        Integer sharedCommentRef) throws SQLException {

        Connection conn = start();

        String sql =
            "select * " +
            "from `shared_votes` " +
            "where `user_id` = ? " +
            "and `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `shared_item_ref` = ? " +
            "and `shared_comment_ref` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ps.setInt(3, smartGroupRef);
        ps.setInt(4, sharedItemRef);
        ps.setInt(5, sharedCommentRef);

        ResultSet rs = ps.executeQuery();

        SharedVote out = null;
        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static SharedVote getByUserIdAndSharedVotable(Integer userId, SharedVotable sharedVotable) throws SQLException {

        SharedVote out = null;
        if (sharedVotable instanceof SharedComment) {

            out = SharedVoteDao.getByAllFields(
                userId,
                sharedVotable.getNetworkId(),
                sharedVotable.getSmartGroupRef(),
                ((SharedComment) sharedVotable).getSharedItemRef(),
                sharedVotable.getRef());

        } else {

            out = SharedVoteDao.getByAllFields(
                userId,
                sharedVotable.getNetworkId(),
                sharedVotable.getSmartGroupRef(),
                sharedVotable.getRef(),
                SharedCommentDao.ANY_SHARED_COMMENT_REF);

        }


        return out;
    }

    public static Integer updateVoteByAllFields(
        Integer userId,
        Integer networkId,
        Integer smartGroupRef,
        Integer sharedItemRef,
        Integer sharedCommentRef,
        SharedVoteEnum vote) throws SQLException {

        Connection conn = start();

        String sql =
            "update `shared_votes` " +
            "set `vote` = ? " +
            "where `user_id` = ? " +
            "and `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `shared_item_ref` = ? " +
            "and `shared_comment_ref` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, vote.getId());
        ps.setInt(2, userId);
        ps.setInt(3, networkId);
        ps.setInt(4, smartGroupRef);
        ps.setInt(5, sharedItemRef);
        ps.setInt(6, sharedCommentRef);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    public static Integer deleteInactive () throws SQLException {

        Connection conn = start();

        String sql =
            "delete " +
            "from `shared_votes` " +
            "where `vote` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, SharedVoteEnum.NONE.getId());

        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    public static Integer deleteByAllFields(
        Integer userId,
        Integer networkId,
        Integer smartGroupRef,
        Integer sharedItemRef,
        Integer sharedCommentRef) throws SQLException {

        Connection conn = start();

        String sql =
            "delete " +
            "from `shared_votes` = ? " +
            "where `user_id` = ? " +
            "and `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `shared_item_ref` = ? " +
            "and `shared_comment_ref` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ps.setInt(3, smartGroupRef);
        ps.setInt(4, sharedItemRef);
        ps.setInt(5, sharedCommentRef);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }


    public static Integer insert (
        Integer userId,
        Integer networkId,
        Integer smartGroupRef,
        Integer sharedItemRef,
        Integer sharedCommentRef) throws SQLException {

        Connection conn = start();

        String sql =
            "insert into `shared_votes` (" +
            "`created_on`, " +
            "`user_id`, " +
            "`network_id`, " +
            "`smart_group_ref`, " +
            "`shared_item_ref`, " +
            "`shared_comment_ref` " +
            ") values (NOW(), ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ps.setInt(3, smartGroupRef);
        ps.setInt(4, sharedItemRef);
        ps.setInt(5, sharedCommentRef);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static SharedVote loadPrimitives (ResultSet rs) throws SQLException {

        SharedVote out = new SharedVote();

        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setSmartGroupRef(DatabaseUtils.getInt(rs, "smart_group_ref"));
        out.setSharedItemRef(DatabaseUtils.getInt(rs, "shared_item_ref"));
        out.setSharedCommentRef(DatabaseUtils.getInt(rs, "shared_comment_ref"));
        out.setVote(SharedVoteEnum.getById(DatabaseUtils.getInt(rs, "vote")));

        return out;
    }

}
