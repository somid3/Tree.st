<%@ include file="../all.jsp" %>
<%
    String path = StringUtils.parseString(request.getParameter("path"));
    String name = StringUtils.parseString(request.getParameter("name"));
    String desc = StringUtils.parseString(request.getParameter("desc"));
    String quality1 = StringUtils.parseString(request.getParameter("q1"));
    String quality2 = StringUtils.parseString(request.getParameter("q2"));
    String quality3 = StringUtils.parseString(request.getParameter("q3"));
    String quality4 = StringUtils.parseString(request.getParameter("q4"));
    String quality5 = StringUtils.parseString(request.getParameter("q5"));

    StringBuilder buf = new StringBuilder();

    try {

        List<String> qualities = new ArrayList<String>();
        qualities.add(quality1);
        qualities.add(quality2);
        qualities.add(quality3);
        qualities.add(quality4);
        qualities.add(quality5);

        NetworkServices.createNSimpleetwork(path, name, desc, qualities);

    } catch (UIException e) {

        // Documenting error
        buf = new StringBuilder();
        buf.append("<error>");
        buf.append(e.getMessage());
        buf.append("</error>");

    }
%>
<?xml version="1.0"?>
<login>
    <%= buf.toString() %>
</login>