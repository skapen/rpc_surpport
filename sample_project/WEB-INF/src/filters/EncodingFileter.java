/*
 * Created on 2004/06/15
 * EncodingFileter.java
 */
package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFileter implements Filter{

	public void destroy() {

	}

	public void doFilter(ServletRequest request,ServletResponse response,
			             FilterChain chain)throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request,response);
	}

	public void init(FilterConfig config) throws ServletException {
	}

}
