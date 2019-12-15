//package com.mybike.web.filters;
//
//import com.mybike.data.entities.Enduro;
//import com.mybike.service.models.EnduroCreateServiceModel;
//import com.mybike.service.services.AuthenticatedUserService;
//import com.mybike.service.services.EnduroService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Set;
//
//@Component
//@AllArgsConstructor
//public class UserEnduroInterceptor implements HandlerInterceptor {
//    private final AuthenticatedUserService authenticatedUserService;
//    private final EnduroService enduroService;
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        String username = authenticatedUserService.getUsername();
//        try {
//            Enduro enduro = enduroService.create(username, new EnduroCreateServiceModel());
//            request.getSession()
//                    .setAttribute("enduroName", enduro.getName());
//        } catch (Exception ex) {
//            // do nothing
//        }
//    }
//
//}