<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>

<div id="currently">
</div>

<div id="networks">
</div>

<script type="text/javascript">

    Transitions.load("#networks", "./modules/networks/networks.jsp", function() {
        LeftMenu.offsetCurrently();
    });

</script>