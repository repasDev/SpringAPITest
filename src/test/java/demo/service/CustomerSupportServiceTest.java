package demo.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CustomerSupportServiceTest {

    CustomerSupportService customerSupportService = new CustomerSupportService();

    @Test
    void customerServiceShouldBeAvailable() {
        int hour1 = 9;
        int hour2 = 13;

        boolean expected1 = customerSupportService.checkIfAvailable(hour1);
        boolean expected2 = customerSupportService.checkIfAvailable(hour2);

        assertThat(expected1).isTrue();
        assertThat(expected2).isTrue();
    }

    @Test
    void customerServiceShouldBeUnavailable() {
        int hour1 = 8;
        int hour2 = 15;

        boolean expected1 = customerSupportService.checkIfAvailable(hour1);
        boolean expected2 = customerSupportService.checkIfAvailable(hour2);

        assertThat(expected1).isFalse();
        assertThat(expected2).isFalse();
    }
}