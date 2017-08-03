package com.maystrovyy.utils.formatters;

import com.maystrovyy.models.DomainType;
import org.springframework.util.StringUtils;

import java.io.Serializable;

public interface NameServerFormatter extends Serializable {

    default String defaultFormatByType(String domainName, DomainType type) {
        int dotsCount = StringUtils.countOccurrencesOf(domainName, ".");
        char lastChar = domainName.charAt(domainName.length() - 1);
        if ((dotsCount >= 0 || dotsCount <= 2) && lastChar != '.') {
            domainName = domainName + type.getDomain();
        } else if (lastChar == '.') {
            domainName = domainName.substring(0, domainName.length() - 1);
        }
        return domainName;
    }

}