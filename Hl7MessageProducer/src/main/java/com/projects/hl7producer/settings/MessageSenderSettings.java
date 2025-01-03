package com.projects.hl7producer.settings;

public record MessageSenderSettings(String host, int port, boolean tlsEnabled) {
}
