package tech.alexchen.zeus.auth.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/token")
public class ZeusTokenEndpoint {

//    @GetMapping("/hello")
//    public String hello() {
//        return "hello";
//    }

//    @GetMapping("/login")
//    public ModelAndView login(ModelAndView modelAndView) {
//        modelAndView.setViewName("ftl/login");
//        return modelAndView;
//    }

//    @GetMapping("/confirm_access")
//    public ModelAndView confirm(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
//        Map<String, Object> scopeList = (Map<String, Object>) request.getAttribute("scopes");
//        modelAndView.addObject("scopeList", scopeList.keySet());
//
//        Object auth = session.getAttribute("authorizationRequest");
////        if (auth != null) {
////            AuthorizationRequest authorizationRequest = (AuthorizationRequest) auth;
////            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
////            modelAndView.addObject("app", clientDetails.getAdditionalInformation());
////            modelAndView.addObject("user", SecurityUtils.getUser());
////        }
//        modelAndView.setViewName("ftl/confirm");
//        return modelAndView;
//    }

}
