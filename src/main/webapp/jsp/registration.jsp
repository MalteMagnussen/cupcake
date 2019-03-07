<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

    <img src="https://i.imgur.com/p7ER9J4.jpg" 
         alt="Hello Kitty" 
         style="
         float: right;
         width: 639px;
         height: 426px;  ">


    <div id="CreateUser">
        <p> Create User: </p>
        <form method="post" action="/Cupcake/Controller?command=Login">
            <input type="hidden" name="origin" value="registration">
            Name:        <input type="text" name="username"/><br/>
            Password:    <input type="text" name="password"/><br/>
            Email:       <input type="text" name="email"/><br/>
            <input type="submit" value="Create User"/>
        </form>
    </div> 
<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>