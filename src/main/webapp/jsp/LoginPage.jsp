<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

    <p> Login: </p> 
    <form action="Controller?command=Login" method="post">
        <input type="hidden" name="origin" value="addProduct">
        Name:        <input type="text" name="username"/><br/>
        Password:    <input type="text" name="password"/><br/>
        <input type="submit" value="Login"/>
    </form>

<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>