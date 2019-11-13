package com.dongisarang.user.customer;

import com.dongisarang.user.place.PlaceDtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/join")
    public String goSearch(){
        return "pages/join";
    }

    @GetMapping("/login")
    public String goLogin(){
        return "pages/login";
    }

    @GetMapping("/")
    public String goLMain(){
        return "pages/main";
    }

    @GetMapping("/profile")
    public String goProfile(){
        return "pages/profile";
    }

    @GetMapping("/place")
    public String goPlace(Model model){

        //시설 안내 내용 DB에서 가져오기
        String [] facility = new String[5];
        facility[0] = "매장 내부에 화장실이 있습니다.";
        facility[1] = "전신거울이 준비되어 있습니다.";
        facility[2] = "모임/강연/마켓/클래스 등 모객 홍보를 지원해드리고 있습니다. (자체 SNS 홍보망 구축)";
        facility[3] = "스탠드 및 담요/미니안마기/공기청정기가 마련되어 있습니다.";
        facility[4] = "각 룸에 모니터를 구비하고 있습니다.";

        //예약 시 주의사항 내용 DB에서 가져오기
        String [] reservation = new String[7];
        reservation[0] = "예약 변경사항은 꼭 사전에 연락 주시기 바랍니다.";
        reservation[1] = "101~103호 최소인원은 3명입니다.";
        reservation[2] = "104호 최소인원은 5명입니다.";
        reservation[3] = "모니터 사용을 희망하시는 분은 사전에 메모 혹은 전화문의 부탁드립니다.";
        reservation[4] = "외부 음식 반입은 가능하나 음식물 쓰레기는 직접 치워주셔야 하니 유의해주세요.";
        reservation[5] = "주류는 반입 불가입니다.";
        reservation[6] = "월요일과 화요일 예약은 전화 및 카카오톡 플러스 친구(소셜팩토리 홍대2호점)로 문의해주세요.";

        //공간 소개 가져오기
        String information =
                "TALK. PLAY. BOOK. STUDY.가 가능한\n" +
                        "복합문화공간으로 누구나 편하게 이용할 수 있답니다:-)\n" +
                        "\n" +
                        "화려하고 거창하진 않지만 쾌적하고 불편함 없이 이용할 수 있는 장소입니다.\n" +
                        "부담없이 다양한 모임문화가 이루어질 수 있도록 돕고 있습니다.\n" +
                        "\n" +
                        "홍대 모임·스터디·세미나·강연·멘토링·독서모임·\n" +
                        "전시회·문화·원데이클래스·행사 등이 가능한 곳입니다.\n" +
                        "\n" +
                        "(*주류는 반입하실 수 없습니다)\n" +
                        "\n" +
                        "2호선 홍대입구역 2번출구 방면.\n" +
                        "바다파스타에서 50m만 오시면 만나실 수 있습니다.\n" +
                        "*네이버에서 소셜팩토리 2호점 검색\n" +
                        "\n" +
                        "■ 혹시 매장 전화가 안될때는 카카오톡 플러스친구 \"소셜팩토리 홍대2호점\" 를 추가해주시면 편하게 문의가능합니다^^ ■\n" +
                        "■ 월요일과 화요일 예약은 전화 혹은 카카오톡 플러스 친구 문의로만 가능하니 유의해주세요!■ ";

        //세부공간 정보 가져오기 >> 객체로 가져와야한다.
        String [] placeDtls = new String[4];
        placeDtls[0] = "소셜팩토리 101호(3-8인)";
        placeDtls[1] = "소셜팩토리 101호(3-8인)";
        placeDtls[2] = "소셜팩토리 101호(3-8인)";
        placeDtls[3] = "소셜팩토리 101호(3-8인)";

        model.addAttribute("facility", facility);
        model.addAttribute("reservation", reservation);
        model.addAttribute("information", information);
        model.addAttribute("placeDtls", placeDtls);
        return "pages/place";
    }

    @GetMapping("/reserve")
    public String goReserve(){
        return "pages/reserve";
    }


    /** 유저 프로필 페이지로 이동한다. */
    @RequestMapping("/my")
    public String profileManageForm(Model model, @RequestParam(value ="message", defaultValue = "default")String message){
        //검증
        Customer getCustomer = customerRepository.findByCustomerId("gunflake09");
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
