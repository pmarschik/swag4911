<%
response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
String newLocn = "../../swag/user/";
response.setHeader("Location",newLocn);
%>