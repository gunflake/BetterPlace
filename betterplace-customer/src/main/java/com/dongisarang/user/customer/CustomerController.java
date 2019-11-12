package com.dongisarang.user.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    /** 메인 페이지로 이동한다. */
    @GetMapping(value = "/main")
    public String goMain() {
        return "pages/main";
    }

    /** 로그인 페이지로 이동한다. */
    @GetMapping(value = "/login")
    public String goLogin() { return "pages/login"; }

    /** 회원가입 페이지로 이동한다. */
    @GetMapping(value = "/join")
    public String goJoin() { return "pages/join"; }

    /** 회원가입 후 로그인 페이지로 이동한다. */
    @PostMapping(value="/join")
    public String setJoin(Customer customer) {

        customerService.signUpCustomer(customer);

        return "redirect:/login";
    }

    /** 유저 프로필 페이지로 이동한다. */
    @RequestMapping("/my")
    public String profileManageForm(Model model, @RequestParam(value ="message", defaultValue = "default")String message){
        //검증
        Customer getCustomer = customerRepository.findByCustomerId("hskim");
        System.out.println(getCustomer.getNickname());

        //로그인 정보 가지고 오기 > Customer 객체로 모델 담기
        model.addAttribute("customer", getCustomer);

        //Redirect 된 경우엔 model에 리턴 메세지 담기
        if(!message.equals("default")){
            model.addAttribute("resultMessage", message);
        }

        return "customer/Profile";
    }

    /** 유저 비밀번호 변경 페이지로 이동한다. */
    @RequestMapping("/auth/change/password")
    public String changePasswordForm(){
        //TODO: 로그인 정보 세션 값 읽어서 유저 상태확인

        return "customer/ChangePassword";
    }

    /** 새로운 비밀번호로 변경 후 마이페이지로 이동한다. */
    @PostMapping("/auth/change/password/setting")
    public String setPasswordForm(Customer customer, RedirectAttributes redirectAttributes){
        //TODO: 로그인 정보 세션 값 읽어서 유저 상태확인
        System.out.println(customer.getCustomerPassword());
        System.out.println(customer.getChangePassword());

        //변경
        if(customerService.changePassword(customer.getCustomerPassword(), customer.getChangePassword())){
            redirectAttributes.addAttribute("message", "비밀번호가 변경 되었습니다.");
        }else{
            redirectAttributes.addAttribute("message", "비밀번호가 변경에 실패했습니다. 다시 시도해주세요.");
        }

        //이동
        return "redirect:/my";
    }

    /** 패스워드 찾기 페이지로 이동한다*/
    @GetMapping("/customer/password/reset")
    public String resetPasswordForm(){
        return "customer/ResetPassword";
    }
}
