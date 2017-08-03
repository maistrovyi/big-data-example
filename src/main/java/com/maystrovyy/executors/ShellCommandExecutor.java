package com.maystrovyy.executors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

@Slf4j
@Component
public class ShellCommandExecutor implements Serializable {

    private final String WHOIS_COMMAND = "whois";
    private final String NSLOOKUP_COMMAND = "nslookup";

    public String whois(String domainName) {
        return execute(WHOIS_COMMAND, domainName);
    }

    public String nsLookup(String domainName) {
        log.info("nsLookup " + domainName);
        return execute(NSLOOKUP_COMMAND, domainName);
    }

    private String execute(String command, String param) {
        Process process;
        StringBuilder builder = new StringBuilder();
        try {
            process = Runtime.getRuntime().exec(command + " " + param);
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return "Bad domain: " + param;
        }
        return builder.toString();
    }

}