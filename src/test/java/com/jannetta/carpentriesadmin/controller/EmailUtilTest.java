package com.jannetta.carpentriesadmin.controller;

import org.junit.jupiter.api.Test;

import javax.mail.Session;

import static org.junit.jupiter.api.Assertions.*;


class EmailUtilTest {

    @Test
    void sendEmailTest() {
        EmailUtil.sendEmail("Session", "jannetta@henning.org", "Subject Test",
                "The quick brown fox jumped over the lazy dog", "jannetta.steyn@newcastle.ac.uk",
                "jannetta.steyn@newcastle.ac.uk");
    }

}