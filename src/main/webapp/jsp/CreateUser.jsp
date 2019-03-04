<%-- 
    Document   : CreateUser
    Created on : Mar 4, 2019, 2:38:12 PM
    Author     : Malte
--%>

<jsp:include page='/jsp/siteheader.jsp'></jsp:include>
    <p> Create User: \n</p>
    <form method="post" action="/Cupcake/Controller?command=InputUser">
        Name:        <input type="text" name="username"/><br/>
        Password:    <input type="text" name="password"/><br/>
        Email:       <input type="text" name="email"/><br/>
        <input type="submit" value="Create User"/>
    </form>

<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>


