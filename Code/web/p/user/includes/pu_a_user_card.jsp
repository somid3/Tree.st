<% {
    /* Inputs variables
     *
     * User pu_a_user = null;
     * Network pu_a_network = null;
     */

    UrlQuery pu_a_UrlQuery = new UrlQuery();
    pu_a_UrlQuery.add("uid", pu_a_user.getId());

    UserToNetwork pu_a_userToNetwork = UserToNetworkDao.getByUserIdAndNetworkId(null, pu_a_user.getId(), pu_a_network.getId());
%>
    <style>
        .user_card {
            display: inline-block;
            width: 100px;
            border: solid 1px <%= HtmlDesign.dim4 %>;
            padding: 5px;
        }

        .card_left {
            float: left;
            margin-right: 5px;
        }

        .card_right {
            float: left;
        }

        .card_face > img {
            width: 30px;
            height: 30px;
        }
    </style>


    <div class="user_card">

        <div class="card_left">

            <div class="card_face">
                <img src="<%= pu_a_user.getFaceUrl() %>"/>
            </div>

        </div>

        <div class="card_right">

            <div class="card_top">

                <div class="card_details">
                    <div class="sm_text"><%= StringUtils.concat(pu_a_user.getFirstName(), 8, "&hellip;") %></div>
                    <div>
                        <span class="sm_text dim2"><%= pu_a_userToNetwork.getCurrentPoints()%></span>
                    </div>
                </div>
            </div>
        </div>
    </div>

<% } %>