<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

    <h2>Cupcake - Home</h2>
    <section>
        <%= displayDate() %>
    </section>
    <%! 
    public String displayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        Date date = Calendar.getInstance().getTime();
        return dateFormat.format(date);
    }
    %>

    <p>Benjamin, Malte, Nikolaj, Mikkel</p>

<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>