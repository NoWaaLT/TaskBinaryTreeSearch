package com.orioninc.abstractfactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkIdentifyTest {

    LinkIdentify linkIdentify = new LinkIdentify();

    @Test
    @DisplayName("Test Link Identify for HTTPS")
    void testLinkIdentifyForHttpsProtocol() {
        assertTrue(linkIdentify.isLink("https://google.lt"));
    }

    @Test
    @DisplayName("Test Link Identify for HTTP")
    void testLinkIdentifyForHttpProtocol() {
        assertTrue(linkIdentify.isLink("http://google.lt"));
    }

    @Test
    @DisplayName("Test Link Identify for Any Letter In Protocol")
    void testLinkIdentifyForInvalidProtocol() {
        assertFalse(linkIdentify.isLink("ht://google.lt"));
    }

    @Test
    @DisplayName("Test Link Identify For No Protocol")
    void testLinkIdentifyForNoProtocol() {
        assertTrue(linkIdentify.isLink("www.google.lt"));
    }


    @Test
    @DisplayName("Test 2 Link Identify For No Protocol")
    void testLinkIdentifyForNoProtocol2() {
        assertFalse(linkIdentify.isLink("ww.google.lt"));
    }
}
