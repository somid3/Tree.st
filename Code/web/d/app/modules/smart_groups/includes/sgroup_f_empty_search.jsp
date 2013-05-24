<% {
    /**
     * Inputs variables
     *
     *   Map<NetworkAlphaSettingEnum, String> sgroup_f_networkAlphaSettings = null;
     */

    String sgroup_f_settingUserPlural = sgroup_f_networkAlphaSettings.get(NetworkAlphaSettingEnum.VOCAB_USER_PLURAL);
    String sgroup_f_settingUserSingular = sgroup_f_networkAlphaSettings.get(NetworkAlphaSettingEnum.VOCAB_USER_SINGULAR);
%>

<div class="question_note">
    <div class="canvas_container">
        <div class="container">

            <div><img src="./modules/smart_groups/img/empty_search.png"></div>

            <div class="vl_text dim">
                Your <%= sgroup_f_settingUserSingular %> searcher is currently empty.<br/>
                Use the top search bar to find and add multiple filters to discover truly unique <%= sgroup_f_settingUserPlural %>.
            </div>
        </div>
    </div>
</div>

<% } %>