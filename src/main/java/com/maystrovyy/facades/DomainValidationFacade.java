package com.maystrovyy.facades;

import com.maystrovyy.services.WhoisHelperService;

public interface DomainValidationFacade {

    boolean isFreeDomain(String domain, WhoisHelperService whoisService);

}