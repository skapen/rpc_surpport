package filters;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.FilterChain;

/*
 セッションが継続しているか検証するフィルタ
 */


public class SessionChk implements Filter{
	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
	    try{
	    	
	      final String login_menu = "http://localhost:8080/mystruts2/view/login.jsp";

	     //セッションを取得
	      HttpSession session = ((HttpServletRequest)request).getSession();

	      //セッションが確立されているかを確認
	      	if (session == null){
	      		((HttpServletResponse)response).sendRedirect(login_menu);
	      	}else{
	      		Object loginChk = session.getAttribute("login");
	      		if (loginChk == null){
	      	//セッション(login)にユーザー名が入っているかどうかを確認
	      			((HttpServletResponse)response).sendRedirect(login_menu);
	      		}
	      		chain.doFilter(request, response);
	      	}
	      }catch (ServletException se){
	      }catch (IOException e){
	      }
	    }

	    public void init(FilterConfig filterConfig) throws ServletException{
	    }

	    public void destroy(){
	    }
	  }
