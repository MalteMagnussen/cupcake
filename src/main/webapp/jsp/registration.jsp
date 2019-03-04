<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

    <p> Create User: </p>
    <form method="post" action="/Cupcake/Controller?command=InputUser">
        <input type="hidden" name="origin" value="addProduct">
        Name:        <input type="text" name="username"/><br/>
        Password:    <input type="text" name="password"/><br/>
        Email:       <input type="text" name="email"/><br/>
        <input type="submit" value="Create User"/>
    </form>

<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>