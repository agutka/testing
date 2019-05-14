package com.agutka.testing.account;

import com.agutka.testing.account.Account;
import com.agutka.testing.account.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@Tag("fries")
class AccountTest {

     private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Test
    void newAccountShouldNotBeActiveAfterCreation() {
        //then
        //assertFalse(account.isActive());
        //assertThat(account.isActive(), equalTo(false));
        assertThat(account.isActive(), is(false));
    }

    @Test
    void accountShouldBeActiveAfterActivation() {
        //when
        account.activate();

        //then
        //assertTrue(account.isActive());
        assertThat(account.isActive(), equalTo(true));

    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet() {
        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        //assertNull(address);
        assertThat(address, nullValue());
    }

    @Test
    void defaultDeliveryShouldNotBeNullAfterBeingSet() {
        //given
        Address address = new Address("Sportowa", "13");
        account.setDefaultDeliveryAddress(address);

        //when
        Address defaultAddress = account.getDefaultDeliveryAddress();

        //then
        assertNotNull(defaultAddress);
        assertThat(defaultAddress, notNullValue());
    }

    @RepeatedTest(5)
    void newAccountWithNotNullAddressShouldBeActive() {
        //given
        Address address = new Address("Sportowa", "13");

        //when
        Account account = new Account(address);

        //then
        assumingThat(address != null, () ->
                assertTrue(account.isActive()));
    }
}