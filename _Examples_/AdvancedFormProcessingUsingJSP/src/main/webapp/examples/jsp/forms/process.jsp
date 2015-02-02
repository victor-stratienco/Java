<%@ page import="java.util.*" %>
<%!
    ResourceBundle bundle =null;
    public void jspInit() {
        bundle = ResourceBundle.getBundle("forms");
    }
%>
<jsp:useBean id="formHandler" class="foo.FormBean" scope="request">
    <jsp:setProperty name="formHandler" property="*"/>
</jsp:useBean>
<%
    if (formHandler.validate()) {
%>
<jsp:forward page="<%=bundle.getString(\"process.success\")%>"/>
<%
}  else {
%>
<jsp:forward page="<%=bundle.getString(\"process.retry\")%>"/>
<%
    }
%>