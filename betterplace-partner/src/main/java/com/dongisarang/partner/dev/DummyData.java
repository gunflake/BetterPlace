package com.dongisarang.partner.dev;

import com.dongisarang.partner.customer.Customer;
import com.dongisarang.partner.customer.CustomerRepository;
import com.dongisarang.partner.partner.Partner;
import com.dongisarang.partner.partner.PartnerRepository;
import com.dongisarang.partner.place.Place;
import com.dongisarang.partner.place.PlaceDetail;
import com.dongisarang.partner.place.PlaceDetailRepository;
import com.dongisarang.partner.place.PlaceRepository;
import com.dongisarang.partner.reservation.Reservation;
import com.dongisarang.partner.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
@Component
@Transactional
public class DummyData implements ApplicationRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceDetailRepository placeDetailRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        savePartner();
        savePlace();
        savePlaceDetail();
        saveCustomer();
        saveReservation();
        saveReservation1();
    }

    private void saveReservation() {
        Reservation reservation = new Reservation();
        reservation.setCustomerCount((byte) 3);
        reservation.setReservationDate("20200110");
        reservation.setStartTime((byte) 6);
        reservation.setEndTime((byte) 10);

        Place place = placeRepository.findByPlaceNameLike("%썸띵%").orElseThrow();
        reservation.setPlace(place);
        //기본 가격 * (사용 시간) * 인원수
        int reservationPrice = place.getDefaultPrice() * (10 - 6) * 3;
        reservation.setPrice(reservationPrice);

        Customer gunflake09 = customerRepository.findByCustomerId("gunflake09");
        reservation.setCustomer(gunflake09);

        PlaceDetail placeDetail = placeDetailRepository.findByPlaceAndPlaceDetailName(place, "스터디룸 A [4인실]").orElseThrow();
        reservation.setPlaceDetail(placeDetail);

        reservationRepository.save(reservation);



    }

    private void saveCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId("gunflake09");
        customer.setCustomerPassword("1234");
        customer.setNickname("gunflake");
        customer.setEmail("gunflake09@gmail.com");
        customerRepository.save(customer);

    }

    private void saveReservation1() {
        Reservation reservation = new Reservation();
        reservation.setCustomerCount((byte) 3);
        reservation.setReservationDate("20200110");
        reservation.setStartTime((byte) 12);
        reservation.setEndTime((byte) 22);

        Place place = placeRepository.findByPlaceNameLike("%썸띵%").orElseThrow();
        reservation.setPlace(place);
        //기본 가격 * (사용 시간) * 인원수
        int reservationPrice = place.getDefaultPrice() * (10 - 6) * 3;
        reservation.setPrice(reservationPrice);

        Customer gunflake09 = customerRepository.findByCustomerId("gunflake09");
        reservation.setCustomer(gunflake09);

        PlaceDetail placeDetail = placeDetailRepository.findByPlaceAndPlaceDetailName(place, "스터디룸 A [4인실]").orElseThrow();
        reservation.setPlaceDetail(placeDetail);

        reservationRepository.save(reservation);
    }

    private void savePlaceDetail() {

        List<Place> all = placeRepository.findAll();

        for (Place place: all) {
            PlaceDetail placeDetail1 = new PlaceDetail();
            placeDetail1.setMinCount((short) 1);
            placeDetail1.setMaxCount((short) 4);
            placeDetail1.setPlaceDetailName("스터디룸 A [4인실]");
            placeDetail1.setPlaceDetailIntro("슈퍼스타트의 4인실 스터디룸입니다.\n" +
                    "소규모 회의 및 토론 면접연습이 가능합니다.");
            placeDetail1.setPlace(place);

            PlaceDetail placeDetail2 = new PlaceDetail();
            placeDetail2.setMinCount((short) 2);
            placeDetail2.setMaxCount((short) 6);
            placeDetail2.setPlaceDetailName("스터디룸 B [6인실]");
            placeDetail2.setPlaceDetailIntro("슈퍼스타트의 6인실 스터디룸입니다.\n" +
                    "소규모 회의 및 토론 면접연습이 가능합니다.");
            placeDetail2.setPlace(place);

            PlaceDetail placeDetail3 = new PlaceDetail();
            placeDetail3.setMinCount((short) 4);
            placeDetail3.setMaxCount((short) 8);
            placeDetail3.setPlaceDetailName("스터디룸 C [8인실]");
            placeDetail3.setPlaceDetailIntro("슈퍼스타트의 8인실 스터디룸입니다.\n" +
                    "대규모 회의 및 토론 면접연습이 가능합니다.");
            placeDetail3.setPlace(place);

            PlaceDetail placeDetail4 = new PlaceDetail();
            placeDetail4.setMinCount((short) 6);
            placeDetail4.setMaxCount((short) 10);
            placeDetail4.setPlaceDetailName("스터디룸 D [10인실]");
            placeDetail4.setPlaceDetailIntro("슈퍼스타트의 10인실 스터디룸입니다.\n" +
                    "대규모 회의 및 강의가 가능합니다.");
            placeDetail4.setPlace(place);

            placeDetailRepository.save(placeDetail1);
            placeDetailRepository.save(placeDetail2);
            placeDetailRepository.save(placeDetail3);
            placeDetailRepository.save(placeDetail4);

        }



    }

    private void savePlace() {
        Place place1 = new Place();
        place1.setPlaceName("조용하고 아늑한 상수동 - 썸띵");
        place1.setIntro("11-12월 한달간!!! 50% 할인(1인 1시간 기준 2,000원 → 1,000원)\n" +
                "\n" +
                "아늑한 분위기에서 프라이빗하게 이용가능한 공간\n" +
                "오래된 단독주택을 개조하여 운치있는 분위기 연출가능합니다. 또한 원데이클레스 등 작업에 용이한 공간입니다.\n" +
                "\n" +
                "A Room(최대 16인): 빔프로젝터(옵션), 화이트보드, 무료 와이파이 등\n" +
                "B Room(최대 10인): TV모니터, 독립 세면대, 무료 와이파이 등\n" +
                "으로 준비되어 있습니다.\n" +
                "\n" +
                "*썸띵은 다양한 분야에서 활동하는 창작예술과의 소통과 협업을 통해 예술의 즐거움과 영향을 주고받는 디자인 아트 콜라보레이션 브랜드입니다.");
        place1.setTag("홍대,상수역,모임공간,원데이클래스,작업실");
        place1.setInfo("B Room의 경우, 단독 세면시설 사용가능(미술, 캘리그라피 등 용이함);무료 와이파이 사용가능;대관시간 협의가능합니다.;룸 입구는 커튼식으로 설치되어 있습니다.; 1인 1음료 별도 주문 필수입니다.;베이커리류를 제외한 냄새나는 음식물 섭취 불가합니다;A Room의 경우, 사전 빔프로젝터 신청시 유료(5,000원)로 사용 가능합니다.");
        place1.setAddress("서울특별시 마포구 와우산로 29-18 단독주택건물");
        place1.setWebsite("https://www.stsomething.com");
        place1.setPhone("050409059329");
        place1.setImage("https://moplqfgeemqv2103108.cdn.ntruss.com/pstatic-scloud/20171123_122/15114354285970aKDL_JPEG/2%2525C3%2525FE_%2525C0%2525FC%2525BD%2525C3%2525C0%2525E5_1.jpg?type=m&w=900&h=900&autorotate=true&quality=90");
        place1.setNotice("예약 변경사항은 꼭 사전에 연락 주시기 바랍니다.;101~103호 최소인원은 3명입니다.;104호 최소인원은 5명입니다.;모니터 사용을 희망하시는 분은 사전에 메모 혹은 전화문의 부탁드립니다.;외부 음식 반입은 가능하나 음식물 쓰레기는 직접 치워주셔야 하니 유의해주세요.;주류는 반입 불가입니다.;월요일과 화요일 예약은 전화 및 카카오톡 플러스 친구(소셜팩토리 홍대2호점)로 문의해주세요.");
        place1.setDefaultPrice(1500);

        place1.setPartner(partnerRepository.findByPartnerId("gunflake09").orElseThrow());

        Place place2 = new Place();
        place2.setPlaceName("슈퍼스타트 홍대점");
        place2.setIntro("1. 스터디룸은 4인실 6인실 8인실 10인실 15인실로 되어 있습니다.\n" +
                "소규모 스터디 및 과외 면접준비가 가능한 4~8인실부터\n" +
                "모임 발표 토론 및 강의가 가능한 10~15인실로 구성되어 있습니다.\n" +
                "\n" +
                "2. 여러 선생님들과 제휴를 맺어 원하시는 강의를 신청하실 수 있습니다.\n" +
                "승무원 스터디(국내 외황 승무원 영어 보이스 피칭) 금융권 스터디 영어 회화(캠블리) 스터디가\n" +
                "진행 중에 있습니다.\n" +
                "\n" +
                "3. 홍대에서 거의 유일하게 주차가 가능한 스터디룸 입니다.\n" +
                "\n" +
                "4. 기본 PC Wi-Fi 가 제공되며 10인실 15인실 룸에는 빔 프로젝터가 구비되어 있습니다.\n" +
                "소규모 방의 경우 이동형 빔프로젝터를 무료로 대여해 드립니다.(사전 문의 필요)\n" +
                "\n" +
                "5. 고급 머신에서 추출하는 드립 커피를 무료로 드리고 있습니다. 원하시는 만큼 드실 수 있습니다.\n" +
                "(※드립 커피 등 추가 음료는 판매합니다. )");
        place2.setTag("홍대,승무원,스터디룸,영어회화,강의실");
        place2.setInfo("평일/주말 모두 09~23시까지 운영합니다;일찍 오신 경우 야외에 테라스에서 대기하시면 됩니다.;결제는 사전 결제 및 후불 결제가 가능합니다;예약 당일 오셔서 카운터에 예약자분 성함을 말씀해 주시면 배정된 방 번호를 알려 드립니다.");
        place2.setAddress("서울특별시 마포구 서교동 364-31 4층 슈퍼스타트");
        place2.setWebsite("http://www.superstart.co.kr");
        place2.setPhone("050409053184");
        place2.setImage("https://moplqfgeemqv2103108.cdn.ntruss.com/service/157607491_b5a886ed9bdbcef40a77870fcf4c865c.jpg?type=m&w=900&h=900&autorotate=true&quality=90");
        place2.setDefaultPrice(2000);
        place2.setNotice("예약 변경사항은 꼭 사전에 연락 주시기 바랍니다.;101~103호 최소인원은 3명입니다.;104호 최소인원은 5명입니다.;모니터 사용을 희망하시는 분은 사전에 메모 혹은 전화문의 부탁드립니다.;외부 음식 반입은 가능하나 음식물 쓰레기는 직접 치워주셔야 하니 유의해주세요.;주류는 반입 불가입니다.;월요일과 화요일 예약은 전화 및 카카오톡 플러스 친구(소셜팩토리 홍대2호점)로 문의해주세요.");

        place2.setPartner(partnerRepository.findByPartnerId("gunflake09").orElseThrow());

        Place place3 = new Place();
        place3.setPlaceName("[1958년] 다락방구구(독채)");
        place3.setIntro("다락방구구는 1958년에 지어진 국내에서 가장 오래된 건물 중 하나로\n" +
                "낡은 것과 새로운 것이 공존하는 따뜻한 공간입니다.\n" +
                "\n" +
                "주인장이 스페인 산티아고 순례길을 1,000km 걸으면서\n" +
                "묵었던 여행자 숙소인 알베르게(Albergue)에서 영감을 받아\n" +
                "만들었다는 사실!\n" +
                "\n" +
                "높은 나무 천장 아래에 앉으면 나무 향기가 은은해요.\n" +
                "10평의 공간을 독채로 사용하기 때문에 조용하고 독립적이어서\n" +
                "기업체 팀 워크숍, 세미나를 하기에 제격입니다.\n" +
                "\n" +
                "시공간이 멈춘 조용한 마을 북촌의 한가운데 있어\n" +
                "커피 한잔을 들고 북촌8경을 산책하면 교외로 '소풍' 온 기분을 느낄 수 있죠.\n" +
                "(3호선 안국역 5분 거리)\n" +
                "\n" +
                "이번 겨울에는 나무 천장의 다락방에서 파티 해보는 건 어떠세요?\n" +
                "낮에는 팀워크숍을 하고, 밤에는 파티를 이어서 진행하면\n" +
                "회의도 하고 친목도 다질 수 있어 만족도가 더 높아질 겁니다.\n" +
                "\n" +
                "(삼성SDS연수에서 4.9점 기록/\n" +
                "기업체 워크숍 후 만족도 조사에서 항상 1위)");
        place3.setTag("워크샵,북촌,워크숍,종로,세미나");
        place3.setInfo("서울에서 가장 오래된 건물 중 하나로 나무천장이 멋스러운 공간;북촌 한옥 마을 안에 있어 조용하고, 커피를 들고 북촌8경을 산책하기 좋음;10평의 공간을 독채로 사용해서 방해 받지 않고 몰입감이 높음;워크숍 전용공간으로 빔프로젝터, 노트북, 음향 등이 완비;저녁에는 와인파티, 독서모임 등의 소셜모임을 추천;안국에서 4~5분 거리로 차 없이 편리하게 이동 가능;직접 내려주는 원두커피 향이 좋음");
        place3.setAddress("서울특별시 종로구 가회동 206 2층 다락방구구");
        place3.setWebsite("http://attic99.com/");
        place3.setPhone("050409055431");
        place3.setImage("https://iepdybgueong2014375.cdn.ntruss.com/pstatic-scloud/20160927_200/1474972997013BLaT8_PNG/%25B1%25D7%25B8%25B24.png");
        place3.setDefaultPrice(1000);
        place3.setNotice("예약 변경사항은 꼭 사전에 연락 주시기 바랍니다.;101~103호 최소인원은 3명입니다.;104호 최소인원은 5명입니다.;모니터 사용을 희망하시는 분은 사전에 메모 혹은 전화문의 부탁드립니다.;외부 음식 반입은 가능하나 음식물 쓰레기는 직접 치워주셔야 하니 유의해주세요.;주류는 반입 불가입니다.;월요일과 화요일 예약은 전화 및 카카오톡 플러스 친구(소셜팩토리 홍대2호점)로 문의해주세요.");
        place3.setPartner(partnerRepository.findByPartnerId("gunflake09").orElseThrow());

        Place place4 = new Place();
        place4.setPlaceName("TRYGROUND-스터디룸,클래스");
        place4.setIntro("- TRYGROUND_클래스점은 개인 창업자, 프리랜서, 창작가 등 다양한 크리에이터들이 함께 이용하는 공간입니다.\n" +
                "- 당신도 창업! 당신만의 Privite한 아지트에서 새로운 TRY를 당장 시작하세요!\n" +
                "- 해당 공간은 공유공간 내에 독립된 공간으로 많은 분들이 클래스, 스터디, 소모임, 컨설팅 등을 목적으로 사용하고 계십니다.\n" +
                "- 빔프로젝터, 화이트보드 등 클래스에 필요한 장비 완비!\n" +
                "\n" +
                "※ 최소 주문 가능 금액은 3명부터 이며, 2명 이하일 경우 3명으로 결제해야 예약기 가능합니다.");
        place4.setTag("회의실,수업,다목적,스터디룸,클래스");
        place4.setInfo("와이파이;빔프로젝터;정수기;노트북 무료대여;공기청정기;냉난방기;음료주문가능;화이트보드");
        place4.setAddress("서울 관악구 남부순환로 1921-1 4층 (낙성대역 5번출구 바로앞 건물)");
        place4.setWebsite("https://tryground.modoo.at/?link=5s02japt");
        place4.setPhone("050409055431");
        place4.setImage("https://moplqfgeemqv2103108.cdn.ntruss.com/service/157495291_18e2999891374a475d0687ca9f989d83.jpg?type=m&w=900&h=900&autorotate=true&quality=90");
        place4.setDefaultPrice(2000);
        place4.setNotice("예약 변경사항은 꼭 사전에 연락 주시기 바랍니다.;101~103호 최소인원은 3명입니다.;104호 최소인원은 5명입니다.;모니터 사용을 희망하시는 분은 사전에 메모 혹은 전화문의 부탁드립니다.;외부 음식 반입은 가능하나 음식물 쓰레기는 직접 치워주셔야 하니 유의해주세요.;주류는 반입 불가입니다.;월요일과 화요일 예약은 전화 및 카카오톡 플러스 친구(소셜팩토리 홍대2호점)로 문의해주세요.");
        place4.setPartner(partnerRepository.findByPartnerId("gunflake09").orElseThrow());

        Place place5 = new Place();
        place5.setPlaceName("스터디카페두드림 Do dream");
        place5.setIntro("분당 서현역 24시간 스터디카페 Do:Dream (스터디룸 1-4인실 / 2-5인실 보유)\n" +
                "당일 예약은 호스트에게 직접 해주세요 (당일은 예약 확인을 못 할 수도 있음)\n" +
                "\n" +
                "※ 스페이스클라우드 시스템 문제가 해결되어 6월 21일부터 예약이 정상적으로 되고 있습니다.\n" +
                "혹시 예약 진행시 문제가 생기시면 ☎ 070-4408-0909 로 직접 예약해 주시면 감사하겠습니다.");
        place5.setTag("서현,분당,스터디룸,서현역,서현역과외공간");
        place5.setInfo("4인실 스터디룸;5인실 스터디룸;저렴한 이용요금(룸당 요금 계산);핸드폰 충전기 및 콘센트;공기청정기;냉난방기;음료주문가능;화이트보드");
        place5.setAddress("경기도 성남시 분당구 황새울로312번길 20 태성빌딩 4층 403호");
        place5.setWebsite("https://tryground.modoo.at/?link=5s02japt");
        place5.setPhone("050409055431");
        place5.setImage("https://moplqfgeemqv2103108.cdn.ntruss.com/pstatic-scloud/20170809_92/1502247571583oHUiX_JPEG/KakaoTalk_20170803_113048101.jpg?type=m&w=900&h=900&autorotate=true&quality=90");
        place5.setDefaultPrice(3000);
        place5.setNotice("예약 변경사항은 꼭 사전에 연락 주시기 바랍니다.;101~103호 최소인원은 3명입니다.;104호 최소인원은 5명입니다.;모니터 사용을 희망하시는 분은 사전에 메모 혹은 전화문의 부탁드립니다.;외부 음식 반입은 가능하나 음식물 쓰레기는 직접 치워주셔야 하니 유의해주세요.;주류는 반입 불가입니다.;월요일과 화요일 예약은 전화 및 카카오톡 플러스 친구(소셜팩토리 홍대2호점)로 문의해주세요.");
        place5.setPartner(partnerRepository.findByPartnerId("gunflake09").orElseThrow());

        placeRepository.save(place1);
        placeRepository.save(place2);
        placeRepository.save(place3);
        placeRepository.save(place4);
        placeRepository.save(place5);
    }

    private void savePartner(){
        Partner partner1 = new Partner();
        partner1.setPartnerId("gunflake09");
        partner1.setEmail("gunflake09@naver.com");
        partner1.setPartnerPassword("{bcrypt}$2a$10$ojPDvv8tIBJyeH6kp9lTgOm8URDorcS/7jHAhE/jH05FcaWzQqAOy");

        partnerRepository.save(partner1);
    }
}