package exE;

public class HtmlBean {

    public static String toHtml(String body) {
        body = body.replaceAll("\n", "<br/>");
        body = "<body>" + body + "</body>";
        
        System.out.println("inside HtmlBean :"+body);
        return body;
    }
    
}
