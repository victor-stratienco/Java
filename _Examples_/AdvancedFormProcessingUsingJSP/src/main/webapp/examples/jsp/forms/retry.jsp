<jsp:useBean id="formHandler" class="foo.FormBean" scope="request"/>
<html>
<body>
<form action="process.jsp" method=post>
    <center>
        <table cellpadding=4 cellspacing=2 border=0>
            <th bgcolor="#CCCCFF" colspan=2>
                <font size=5>USER REGISTRATION</font>
                <br>
                <font size=1><sup>*</sup> Required Fields </font>
            </th>
            <tr bgcolor="#c8d8f8">
                <td valign=top>
                    <B>First Name<sup>*</sup></B>
                    <br>
                    <input type="text" name="firstName"
                           value='<%=formHandler.getFirstName()%>' size=15 maxlength=20>
                    <br><font size=2
                              color=red><%=formHandler.getErrorMsg("firstName")%></font>
                </td>
                <td  valign=top>
                    <B>Last Name<sup>*</sup></B>
                    <br>
                    <input type="text" name="lastName"
                           value='<%=formHandler.getLastName()%>' size=15 maxlength=20>
                    <br><font size=2
                              color=red><%=formHandler.getErrorMsg("lastName")%></font>
                </td>
            </tr>
            <tr bgcolor="#c8d8f8">
                <td valign=top>
                    <B>E-Mail<sup>*</sup></B>
                    <br>
                    <input type="text" name="email" value='<%=formHandler.getEmail()%>'
                           size=25  maxlength=125>
                    <br><font size=2 color=red><%=formHandler.getErrorMsg("email")%></font>
                </td>
                <td  valign=top>
                    <B>Zip Code<sup>*</sup></B>
                    <br>
                    <input type="text" name="zip" value='<%=formHandler.getZip()%>' size=5
                           maxlength=5>
                    <br><font size=2 color=red><%=formHandler.getErrorMsg("zip")%></font>
                </td>
            </tr>
            <tr bgcolor="#c8d8f8">
                <td valign=top colspan=2>
                    <B>User Name<sup>*</sup></B>
                    <br>
                    <input type="text" name="userName" size=10
                           value='<%=formHandler.getUserName()%>'  maxlength=10>
                    <br><font size=2
                              color=red><%=formHandler.getErrorMsg("userName")%></font>
                </td>
            </tr>
            <tr bgcolor="#c8d8f8">
                <td valign=top>
                    <B>Password<sup>*</sup></B>
                    <br>
                    <input type="password" name="password1" size=10
                           value='<%=formHandler.getPassword1()%>'  maxlength=10>
                    <br><font size=2
                              color=red><%=formHandler.getErrorMsg("password1")%></font>
                </td>
                <td  valign=top>
                    <B>Confirm Password<sup>*</sup></B>
                    <br>
                    <input type="password" name="password2" size=10
                           value='<%=formHandler.getPassword2()%>'  maxlength=10>
                    <br><font size=2
                              color=red><%=formHandler.getErrorMsg("password2")%></font>
                </td>
                <br>
            </tr>
            <tr bgcolor="#c8d8f8">
                <td colspan=2 valign=top>
                    <B>What music are you interested in?</B>
                    <br>
                    <input type="checkbox" name="faveMusic" value="Rock"
                        <%=formHandler.isCbSelected("Rock")%>>Rock
                    <input type="checkbox" name="faveMusic" value="Pop"
                        <%=formHandler.isCbSelected("Pop")%>>Pop
                    <input type="checkbox" name="faveMusic" value="Bluegrass"
                        <%=formHandler.isCbSelected("Bluegrass")%>>Bluegrass<br>
                    <input type="checkbox" name="faveMusic" value="Blues"
                        <%=formHandler.isCbSelected("Blues")%>>Blues
                    <input type="checkbox" name="faveMusic" value="Jazz"
                        <%=formHandler.isCbSelected("Jazz")%>>Jazz
                    <input type="checkbox" name="faveMusic" value="Country"
                        <%=formHandler.isCbSelected("Country")%>>Country<br>
                </td>
            </tr>
            <tr bgcolor="#c8d8f8">
                <td colspan=2 valign=top>
                    <B>Would you like to receive e-mail notifications on our special
                        sales?</B>
                    <br>
                    <input type="radio" name="notify" value="Yes"
                        <%=formHandler.isRbSelected("Yes")%>>Yes
                    <input type="radio" name="notify" value="No"
                        <%=formHandler.isRbSelected("No")%>> No
                    <br><br></td>
            </tr>
            <tr bgcolor="#c8d8f8">
                <td colspan=2 align=center>
                    <input type="submit" value="Submit"> <input type="reset"
                                                                value="Reset">
                </td>
            </tr>
        </table>
    </center>
</form>
</body>
</html>