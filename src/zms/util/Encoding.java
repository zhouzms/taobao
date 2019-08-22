package zms.util;

import java.io.UnsupportedEncodingException;

public class Encoding {
    public static void setEncoding(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
