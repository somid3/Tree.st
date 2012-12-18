package com.questy.dao;

import com.questy.domain.Answer;
import com.questy.domain.NetworkEmailEnding;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NetworkEmailEndingsDao extends ParentDao {

    public static List<NetworkEmailEnding> getByNetworkId (
            Connection conn,
            Integer networkId) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `network_email_endings` " +
            "where `network_id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ResultSet rs = ps.executeQuery();

        List<NetworkEmailEnding> out = new ArrayList<NetworkEmailEnding>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }


    private static NetworkEmailEnding loadPrimitives (ResultSet rs) throws SQLException {
        NetworkEmailEnding out = new NetworkEmailEnding();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setRef(DatabaseUtils.getInt(rs, "ref"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setEmailEndsWith(DatabaseUtils.getString(rs, "email_ends_with"));
        return out;
    }

}
