<%--
  Created by IntelliJ IDEA.
  User: zms
  Date: 2019/8/19
  Time: 下午6:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession httpSession=request.getSession(true);
    httpSession.removeAttribute("user");
    response.sendRedirect("login.jsp");
%>
