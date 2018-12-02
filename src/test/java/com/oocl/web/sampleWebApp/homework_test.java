package com.oocl.web.sampleWebApp;

import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingBoyRepository;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.oocl.web.sampleWebApp.WebTestUtil.getContentAsObject;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class homework_test {
    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void should_create_parkingBoy_with_employeeID() throws Exception {
        // Given employeeID
        final ParkingBoy boy = parkingBoyRepository.save(new ParkingBoy("boy"));

        // When post to parkingboys
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkingboys"))
                .andReturn();

        // Then expect to have 201 http status
        assertEquals(201, result.getResponse().getStatus());

        final ParkingBoyResponse[] parkingBoys = getContentAsObject(result, ParkingBoyResponse[].class);

        assertEquals(1, parkingBoys.length);
        assertEquals("boy", parkingBoys[0].getEmployeeId());
    }
}
