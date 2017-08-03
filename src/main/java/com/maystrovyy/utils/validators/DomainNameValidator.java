package com.maystrovyy.utils.validators;

import com.maystrovyy.configs.ApplicationConstants;

public interface DomainNameValidator {

    static boolean isValidDomainName(String domain) {
        return domain.matches("[()\\d\\w-]+");
    }

    static boolean isDomainNameBeforeNSFilter(String string) {
        int i = string.indexOf(ApplicationConstants.NS_FILTER);
        if (i != 0) {
            String firstString = string.substring(0, i);
            return isValidDomainName(firstString);
        }
        return false;
    }

}