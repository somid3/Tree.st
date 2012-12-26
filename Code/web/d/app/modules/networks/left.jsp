<%@ include file="../../all.jsp" %>

<div id="currently">
</div>

<div id="networks">
</div>

<script type="text/javascript">

    Transitions.load("#networks", "./modules/networks/networks.jsp", function() {
        LeftMenu.offsetItems();
    });

</script>