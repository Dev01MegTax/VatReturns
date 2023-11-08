package filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
public final class filter1 implements Filter {//XssFilter
    static class FilteredRequest extends HttpServletRequestWrapper {
        /* These are the characters allowed by the Javascript validation */
        static String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789#*+@!%/,\\_}{";

        public FilteredRequest(ServletRequest request) {
                super((HttpServletRequest)request);
        }
        public String sanitize(String input) {
                String result = "";
                for (int i = 0; i < input.length(); i++) {
                        if (allowedChars.indexOf(input.charAt(i)) >= 0) {
                                result += input.charAt(i);
                        }
                }
          //  encoder enc = new encoder();
         //   enc.encode(input);
             //   return enc.encode(input);//result;
                return result;
        }
        @Override
        public String getParameter(String paramName) {
                String value = super.getParameter(paramName);
            //   System.out.println("timestamp val is  "+paramName);
                 if (!"TOKEN".equals(paramName)) {
                if ("username".equals(paramName)) {
                        value = sanitize(value);
               }
                if ("password".equals(paramName)) {
                        value = sanitize(value);
               }
                if ("tin".equals(paramName)) {
                        value = sanitize(value);
               }
               
                if("".equals(value))
                value = sanitize(value);
                 return value;
            }
 else return value;
        }
        @Override
        public String[] getParameterValues(String paramName) {
                String values[] = super.getParameterValues(paramName);
              //  if ("dangerousParamName".equals(paramName)) {
                        for (int index = 0; index < values.length; index++) {
                                values[index] = sanitize(values[index]);
                        }
               // }
                return values;
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new FilteredRequest(request), response);
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
    }
}
