<%
{
    /* Inputs variables
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
    SharedVote share_b_sharedVote = SharedVoteDao.getByUserIdAndSharedVotable(me.getId(), share_b_sharedVotable);

    // Determining if the user has vote up
    Boolean share_b_isUpVoteActive = false;
    if (share_b_sharedVote != null && share_b_sharedVote.getVote() == SharedVoteEnum.UP)
        share_b_isUpVoteActive = true;

    // Determining if the user has vote down
    Boolean share_b_isDownVoteActive = false;
    if (share_b_sharedVote != null && share_b_sharedVote.getVote() == SharedVoteEnum.DOWN)
        share_b_isDownVoteActive = true;

    // Creating html ids
    String share_b_hVotesId = HtmlUtils.getRandomId();
%>

<div class="shared_vote" id="<%= share_b_hVotesId %>">

    <% if (share_b_settingSharedItemDisplayUpVotes != 0) { %>

        <a href="#" onclick="SharedVote.applyVote(event, '<%= share_b_hVotesId %>', <%= share_b_sharedVotable.getNetworkId() %>, <%= share_b_sharedVotable.getSmartGroupRef() %>, <%= share_b_sharedVotable.getSharedItemRef() %>, <%= share_b_sharedVotable.getSharedCommentRef() %>, <%= SharedVoteEnum.UP.getId() %>)">

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

        </a>

    <% } if (share_b_settingSharedItemDisplayDownVotes != 0) { %>

        <a href="#" onclick="SharedVote.applyVote(event, '<%= share_b_hVotesId %>', <%= share_b_sharedVotable.getNetworkId() %>, <%= share_b_sharedVotable.getSmartGroupRef() %>, <%= share_b_sharedVotable.getSharedItemRef() %>, <%= share_b_sharedVotable.getSharedCommentRef() %>, <%= SharedVoteEnum.DOWN.getId() %>)">

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

        </a>

    <% } %>

</div>
<% } %>
