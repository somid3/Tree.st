<%
    /* Inputs variables
     *
     *    Network f_network = null;
     *    SmartGroup f_smartGroup = null;
     *    SharedItem f_sharedItem = null;
     */

{
    // Retrieving shared item author
    User f_author = UserDao.getById(null, f_sharedItem.getUserId());

    // Create link to take user to the author's profile
    String f_hSharedCommentAuthorLink = null;

     // Creating shared item link
    String f_hSharedItemLink = null;
    {
        UrlQuery query = new UrlQuery();
        query.add("uid", EmailServices.TO_USER_ID);
        query.add("scs", EmailServices.TO_USER_SALT_CHECKSUM);
        query.add("gh", HashRouting.sharedItem(f_sharedItem.getId(), f_sharedItem.getSmartGroupRef(), f_sharedItem.getSharedItemRef()));
        f_hSharedItemLink = "http://" + Vars.domain + "/r/go/?" + query;
    }

    String f_hSharedItemAuthorLink = null;
    {
        UrlQuery query = new UrlQuery();
        query.add("uid", EmailServices.TO_USER_ID);
        query.add("scs", EmailServices.TO_USER_SALT_CHECKSUM);
        query.add("gh", HashRouting.member(f_sharedItem.getNetworkId(), f_sharedItem.getUserId()));
        f_hSharedItemAuthorLink = "http://" + Vars.domain + "/r/go/?" + query;
    }

    // Retrieving shared item comments
    List<SharedComment> f_sharedComments = null;
    f_sharedComments = SharedCommentDao.getByNetworkIdAndSmartGroupRefAndSharedItemRef(null, f_network.getId(), f_smartGroup.getSmartGroupRef(), f_sharedItem.getSharedItemRef(), SqlLimit.FIRST_TEN);
%>
<tr>
    <td>

        <table width="100%" cellspacing="10">
        <tr>
            <td valign="top" align="left" width="90">
                <%= EmailDesign.aBegin(f_hSharedItemAuthorLink)%>
                    <img width="80" height="80" src="http://<%= Vars.domain %><%= f_author.getFaceUrl() %>">
                <%= EmailDesign.aEnd %>
            </td>
            <td valign="top" align="left">
                <span
                    style="
                    font-family: <%= HtmlDesign.fontFamily%>;
                    font-size: 14px;
                    line-height: 1.5em;">

                &ldquo;<%= HtmlUtils.linkify(StringUtils.concat(f_sharedItem.getText(), 300, "&hellip;")) %>&rdquo;<br/>

                &mdash; <i><%= EmailDesign.aBegin(f_hSharedItemAuthorLink)%><%= f_author.getName() %><%= EmailDesign.aEnd %></i>

                <span style="color: <%= HtmlDesign.dim2%>"> / <%= PrettyDate.toString(f_sharedItem.getCreatedOn()) %></span>

                </span>

                <br/>
            </td>
        </tr>

        <tr>
            <td></td>
            <td valign="top" align="center">
                <br/>
                <%= EmailDesign.aBegin(f_hSharedItemLink, EmailDesign.aStyleNoDeco)%>
                    <%= EmailDesign.spanButtonBegin() %>

                        Comment

                        <% if (f_sharedItem.getTotalComments() > 0) { %>
                            (<%= f_sharedItem.getTotalComments() %>)
                        <% } %>

                        or view full message

                    <%= EmailDesign.spanEnd %>
                <%= EmailDesign.aEnd%>
                </span>
                <br/>
                <br/>
            </td>
        </tr>

        <%
            User f_commentator = null;
            for(SharedComment f_sharedComment : f_sharedComments) {

                // Retrieving commentator
                f_commentator = UserDao.getById(null, f_sharedComment.getUserId());

                f_hSharedCommentAuthorLink = null;
                {
                    UrlQuery query = new UrlQuery();
                    query.add("uid", EmailServices.TO_USER_ID);
                    query.add("scs", EmailServices.TO_USER_SALT_CHECKSUM);
                    query.add("gh", HashRouting.member(f_sharedComment.getNetworkId(), f_sharedComment.getUserId()));
                    f_hSharedCommentAuthorLink = "http://" + Vars.domain + "/r/go/?" + query;
                }
        %>
            <tr>
                <td valign="top" align="right" width="90">
                    <%= EmailDesign.aBegin(f_hSharedCommentAuthorLink) %><img width="50" height="50" src="http://<%= Vars.domain %><%= f_commentator.getFaceUrl() %>"><%= EmailDesign.aEnd %>
                    <br/>
                    <br/>
                </td>
                <td valign="top" align="left">
                    <span
                        style="
                        font-family: <%= HtmlDesign.fontFamily%>;
                        font-size: 12px;
                        line-height: 1.5em;">

                    &ldquo;<%= HtmlUtils.linkify(StringUtils.concat(f_sharedComment.getText(), 300, "&hellip;")) %>&rdquo;<br/>

                    &mdash; <i><%= EmailDesign.aBegin(f_hSharedCommentAuthorLink)%><%= f_commentator.getName() %><%= EmailDesign.aEnd %></i>

                    <span style="color: <%= HtmlDesign.dim2%>"> / <%= PrettyDate.toString(f_sharedComment.getCreatedOn()) %></span>

                    </span>
                    <br/>
                    <br/>
                </td>
            </tr>
        <% } %>
        </table>

    </td>
</tr>
<% } %>
