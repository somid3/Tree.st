package com.questy.admin.networks;

import com.questy.dao.ActiveAnswerDao;
import com.questy.dao.QuestionOptionDao;
import com.questy.dao.UserDao;
import com.questy.dao.UserToNetworkDao;
import com.questy.domain.ActiveAnswer;
import com.questy.domain.QuestionOption;
import com.questy.domain.User;
import com.questy.domain.UserToNetwork;
import com.questy.enums.AllMembersViewEnum;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.enums.RoleEnum;
import com.questy.helpers.SqlLimit;
import com.questy.utils.ValueComparator;

import java.sql.SQLException;
import java.util.*;

public class MITEISeminarAnswerSummary {

    public static void main (String[] args) throws SQLException {


        Integer usersNetworkId = 2003;
        Integer questionNetworkId = 2003;
        Integer questionRef = 1;


        // Retrieve all E&I Track students
        List<UserToNetwork> utns = UserToNetworkDao.getByNetworkIdAndLowestRoleOrderedBy(null, usersNetworkId, RoleEnum.VISITOR, AllMembersViewEnum.BY_POINTS, SqlLimit.ALL);

        Map<User, String> unsortedMap = new HashMap<User, String>();
        ValueComparator vc = new ValueComparator(unsortedMap);
        Map<User, String> sortedMap = new TreeMap<User, String>(vc);

        for (UserToNetwork utn : utns) {
            User user = UserDao.getById(null, utn.getUserId());
            unsortedMap.put(user, user.getLastName());
        }


        sortedMap.putAll(unsortedMap);
        List<User> sortedUsers = new ArrayList<User>();
        sortedUsers.addAll(sortedMap.keySet());


        System.out.println("Total members: " + sortedUsers.size());

        List<ActiveAnswer> aas = null;
        QuestionOption option = null;
        String optionText = null;
        for (User user : sortedUsers) {

            aas = ActiveAnswerDao.getByNetworkIdAndQuestionRefAndUserId(null, questionNetworkId, questionRef, user.getId(), AnswerVisibilityEnum.PRIVATE, SqlLimit.ALL);

            System.out.println(user.getName());

            if (aas.isEmpty())
                System.out.println("\t *No answer*");

            for (ActiveAnswer aa : aas) {

                if (aa.getOptionRef().equals(QuestionOption.ANY_OPTION_REF))
                    optionText = "*Skipped*";
                else {
                    option = QuestionOptionDao.getByNetworkIdAndQuestionRefAndOptionRef(null, aa.getNetworkId(), aa.getQuestionRef(), aa.getOptionRef());
                    optionText = option.getText();
                }

                System.out.println("\t" + optionText);

            }

            System.out.println();
        }


    }

}
