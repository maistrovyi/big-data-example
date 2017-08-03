package com.maystrovyy.facades;

import com.maystrovyy.executors.ShellCommandExecutor;
import com.maystrovyy.services.WhoisHelperService;
import org.apache.spark.api.java.function.Function;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DefaultDomainValidationFacade implements DomainValidationFacade, Serializable {

    private final String NX_DOMAIN = "NXDOMAIN";
    private final String NOT_FOUND = "Not found: ";

    private final String SHELL_WHOIS_COM = "com";
    private final String SHELL_WHOIS_NET = "net";
    private final String SHELL_WHOIS_EDU = "edu";

    @Autowired
    private ShellCommandExecutor shellExecutor;

    private boolean isWhoisShellDomainFormat(String superDomain) {
        switch (superDomain) {
            case SHELL_WHOIS_COM:
            case SHELL_WHOIS_NET:
            case SHELL_WHOIS_EDU:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isFreeDomain(String domain, WhoisHelperService whoisService) {
        boolean isNxDomain = shellExecutor.nsLookup(domain).contains(NX_DOMAIN);
        if (isNxDomain) {
            String superDomain = domain.substring(domain.lastIndexOf(".") + 1, domain.length()).toLowerCase();
            if (isWhoisShellDomainFormat(superDomain)) {
                return shellExecutor.whois(domain).contains(NOT_FOUND + domain);
            } else {
                String host = whoisService.getWhoisServersMap().get(superDomain);
                return whoisService.getWhois(domain, host).contains(NOT_FOUND + domain);
            }
        }
        return false;
    }

    public Function<Document, Document> validateFunction() {
        Function<Document, Document> function = document -> {
            String domain = document.getString("domain");
            document.put("free", isFreeDomain(domain, null));
            return document;
        };
        return function;
    }

}