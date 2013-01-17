<%
{
    /* Inputs variables
     *
     *    User share_b_me = null;
     *
     *    // Item whose voting characteristics can change
     *    SharedVotable share_b_sharedVotable = null;
     *
     *    Map<NetworkAlphaSettingEnum, String> share_b_networkAlphaSettings = null;
     *    Map<NetworkIntegerSettingEnum, Integer> share_b_networkIntegerSettings = null;
     */

    // Retrieving related settings
    Integer share_b_settingSharedItemDisplayUpVotes = share_b_networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_ITEM_DISPLAY_UP_VOTES);
    Integer share_b_settingSharedItemDisplayDownVotes = share_b_networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_ITEM_DISPLAY_DOWN_VOTES);
    String share_b_settingSharedVoteUpVocab = share_b_networkAlphaSettings.get(NetworkAlphaSettingEnum.SHARED_VOTE_UP_VOCAB);
    String share_b_settingSharedVoteDownVocab = share_b_networkAlphaSettings.get(NetworkAlphaSettingEnum.SHARED_VOTE_DOWN_VOCAB);

    // Retrieving user's shared vote, if any...
    SharedVote share_b_sharedVote = SharedVoteDao.getByUserIdAndSharedVotable(share_b_me.getId(), share_b_sharedVotable);

    // Determining if the user has vote up
    Boolean share_b_isUpVoteActive = false;
    if (share_b_sharedVote != null && share_b_sharedVote.getVote() == SharedVoteEnum.UP)
        share_b_isUpVoteActive = true;

    // Determining if the user has vote down
    Boolean share_b_isDownVoteActive = false;
    if (share_b_sharedVote != null && share_b_sharedVote.getVote() == SharedVoteEnum.DOWN)
        share_b_isDownVoteActive = true;
%>

<div>
    <div class="shared_vote">

        <% if (share_b_settingSharedItemDisplayUpVotes != 0) { %>

            <% if (!share_b_isUpVoteActive) { %>
                <div class="do_vote sm_text vsm_button light_button highlight2">
            <% } else { %>
                <div class="do_vote sm_text vsm_button active_button white">
            <% } %>

                <%= share_b_settingSharedVoteUpVocab %>

                <% if (share_b_sharedVotable.getUpVotes() > 0) { %>
                    (<%= share_b_sharedVotable.getUpVotes()%>)
                <% } %>

            </div>

        <% } if (share_b_settingSharedItemDisplayDownVotes != 0) { %>

            <span class="sm_text dim2"> or </span>

            <% if (!share_b_isDownVoteActive) { %>
                <div class="do_vote sm_text vsm_button light_button highlight2">
            <% } else { %>
                <div class="do_vote sm_text vsm_button active_button white">
            <% } %>

                <%= share_b_settingSharedVoteDownVocab %>

                <% if (share_b_sharedVotable.getDownVotes() > 0) { %>
                    (<%= share_b_sharedVotable.getDownVotes()%>)
                <% } %>

            </div>
        <% } %>

    </div>
</div>
<% } %>
