package org.tyaa.spring.rest.hibernate.payment.servletfilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.tyaa.spring.rest.hibernate.payment.model.AbstractResponse;
import org.tyaa.spring.rest.hibernate.payment.model.AccountInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SecurityFilter implements HandlerInterceptor {
    
        public static final Integer ADMIN_ROLE_ID = 1;
        public static final Integer CUSTOMER_ROLE_ID = 2;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

            // Avoid a redirect loop for some urls
            //if (request.getRequestURI().contains("paymentResource")) {
                    AccountInfo accountInfo =
                            (AccountInfo) request.getSession()
                                    .getAttribute("ACCOUNT_INFO");
                    if (accountInfo == null
                            || accountInfo.roleId == null
                            || !accountInfo.roleId.equals(ADMIN_ROLE_ID)) {
                        ObjectMapper jsonMapper = new ObjectMapper();
                        // response.sendRedirect("/sample-interc/");
                        response.setContentType("application/json");
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write(
                            jsonMapper.writeValueAsString(
                                new AbstractResponse<Object>() {
                                    @Override
                                    public String getStatus() {
                                        return "error";
                                    }

                                    @Override
                                    public String getMessage() {
                                        return "sign in as admin first";
                                    }
                                }
                            )
                        );
                        return false;
                    }
            //}
            return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
