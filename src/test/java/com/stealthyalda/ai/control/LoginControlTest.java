package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.NoSuchUserOrPassword;
import org.junit.Test;

public class LoginControlTest {

    @Test(expected = NoSuchUserOrPassword.class)
    public void checkAuthenticationNeg2() throws NoSuchUserOrPassword, DatabaseException {
        //unregistered user should fail
        LoginControl.checkAuthentification("nicht null", "nicht null");
    }


    @Test(expected = NullPointerException.class)
    public void logoutUserNeg() {
        LoginControl.logoutUser();
    }


    @Test
    public void checkAuthentification() {

    }

    @Test
    public void logoutUser() {
    }
}