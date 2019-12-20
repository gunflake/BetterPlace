package com.dongisarang.user.place;

import com.dongisarang.user.partner.Partner;
import com.dongisarang.user.partner.PartnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class PlaceRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(PlaceRepositoryTest.class);

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @BeforeEach
    void beforeProcess() throws Exception{

        //Partner1
        Partner partner1 = new Partner();
        partner1.setPartnerId("gunflake09");
        partner1.setEmail("gunflake09@naver.com");
        partner1.setPartnerPassword("1234");
        partnerRepository.save(partner1);

        //Partner2
        Partner partner2 = new Partner();
        partner2.setPartnerId("hskim");
        partner2.setEmail("hskim@naver.com");
        partner2.setPartnerPassword("1234");
        partnerRepository.save(partner2);
    }

    @Test
    void savePartner() throws Exception{
        Partner partner1 = new Partner();
        partner1.setPartnerId("gunflake09");
        partner1.setEmail("gunflake09@naver.com");
        partner1.setPartnerPassword("1234");
        partnerRepository.save(partner1);
        Optional<Partner> gunflake09 = partnerRepository.findByPartnerId("gunflake09");
        System.out.println("날짜 :"+ gunflake09.get().getRegisterDate());

        Thread.sleep(2000);

        partner1.setEmail("1234");
        partnerRepository.save(partner1);
        Optional<Partner> gunflake10 = partnerRepository.findByPartnerId("gunflake09");
        System.out.println("날짜 :"+ gunflake10.get().getRegisterDate());

    }

    @Test
    void savePlace() throws Exception{
        Place place1 = new Place();
        place1.setPlaceName("빈 브라더스 1호점");
        place1.setAddress("강남구 강남대로 149");
        place1.setPhone("0242239142");

        place1.setPartner(partnerRepository.findByPartnerId("gunflake09").orElseThrow());

        placeRepository.save(place1);

        placeRepository.findByPlaceNameLike("%브라더스%");

        /*
        Place place2 = new Place();
        place2.setPlaceName("빈 브라더스 2호점");
        place2.setAddress("강남구 강남대로 151");
        place2.setPhone("0242239143");

        place2.setPartner(partnerRepository.findByPartnerId("gunflake09").orElseThrow());

        placeRepository.save(place2);
        */
    }

    @Test
    public void search() throws Exception{
        Place getPlace = placeRepository.findByPlaceNameLike("%1호점%").orElseThrow();
        assertThat(getPlace.getPlaceName()).isEqualTo("빈 브라더스 1호점");
        assertThat(getPlace.getPartner().getPartnerId()).isEqualTo("gunflake09");
    }

    @Test
    public void searchPlace() throws Exception {
        Optional<Partner> gunflake09 = partnerRepository.findByPartnerId("gunflake09");
        List<Place> places = gunflake09.orElseThrow().getPlaces();

        assertThat(places.size()).isEqualTo(0);
    }

}
