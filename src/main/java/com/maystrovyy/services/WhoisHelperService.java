package com.maystrovyy.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.whois.WhoisClient;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WhoisHelperService implements Serializable {

    private final WhoisClient whoisClient;

    public WhoisHelperService() {
        this.whoisClient = new WhoisClient();
    }

    private void connectClient(String whoisHost) {
        try {
            whoisClient.connect(whoisHost);
            log.info("Connected!");
        } catch (IOException e) {
            log.error(e.getMessage() + "By " + whoisHost + ".");
            e.printStackTrace();
        }
    }

    private void disconnect() {
        try {
            whoisClient.disconnect();
            log.info("Disconnected!");
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String getWhois(String domainName) {
        connectClient(WhoisClient.DEFAULT_HOST);
        String result = queryDomain(domainName);
        disconnect();
        return result;
    }

    public String getWhois(String domainName, @NotNull String whoisHost) {
        connectClient(whoisHost);
        String result = queryDomain(domainName);
        disconnect();
        return result;
    }

    private String queryDomain(String domainName) {
        try {
            return whoisClient.query("=" + domainName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "unexpected";
    }

    public Map<String, String> getWhoisServersMap() {
        Map<String, String> map = new HashMap<>();
        map.put("ac", "whois.nic.ac");
        map.put("ae", "whois.aeda.net.ae");
        map.put("aero", "whois.aero");
        map.put("af", "whois.nic.af");
        map.put("ag", "whois.nic.ag");
        map.put("al", "whois.ripe.net");
        map.put("am", "whois.amnic.net");
        map.put("as", "whois.nic.as");
        map.put("asia", "whois.nic.asia");
        map.put("at", "whois.nic.at");
        map.put("au", "whois.aunic.net");
        map.put("ax", "whois.ax ");
        map.put("az", "whois.ripe.net");
        map.put("ba", "whois.ripe.net");
        map.put("be", "whois.dns.be");
        map.put("bg", "whois.register.bg");
        map.put("bi", "whois.nic.bi");
        map.put("biz", "whois.neulevel.biz");
        map.put("bj", "www.nic.bj");
        map.put("br", "whois.nic.br");
        map.put("br.com", "whois.centralnic.com");
        map.put("bt", "whois.netnames.net");
        map.put("by", "whois.cctld.by");
        map.put("bz", "whois.belizenic.bz");
        map.put("ca", "whois.cira.ca");
        map.put("cat", "whois.cat");
        map.put("cc", "whois.nic.cc");
        map.put("cd", "whois.nic.cd");
        map.put("ch", "whois.nic.ch ");
        map.put("ck", "whois.nic.ck");
        map.put("cl", "nic.cl");
        map.put("cn", "whois.cnnic.net.cn");
        map.put("cn.com", "whois.centralnic.com");
        map.put("co", "whois.nic.co");
        map.put("co.nl", "whois.co.nl");
        map.put("com", "whois.verisign-grs.com");
        map.put("coop", "whois.nic.coop");
        map.put("cx", "whois.nic.cx");
        map.put("cy", "whois.ripe.net");
        map.put("cz", "whois.nic.cz");
        map.put("de", "whois.denic.de");
        map.put("dk", "whois.dk-hostmaster.dk");
        map.put("dm", "whois.nic.cx");
        map.put("dz", "whois.nic.dz");
        map.put("edu", "whois.educause.net");
        map.put("ee", "whois.tld.ee");
        map.put("eg", "whois.ripe.net");
        map.put("es", "whois.nic.es");
        map.put("eu", "whois.eu");
        map.put("eu.com", "whois.centralnic.com");
        map.put("fi", "whois.ficora.fi");
        map.put("fo", "whois.nic.fo");
        map.put("fr", "whois.nic.fr");
        map.put("gb", "whois.ripe.net");
        map.put("gb.com", "whois.centralnic.com");
        map.put("gb.net", "whois.centralnic.com");
        map.put("qc.com", "whois.centralnic.com");
        map.put("ge", "whois.ripe.net");
        map.put("gl", "whois.nic.gl");
        map.put("gm", "whois.ripe.net");
        map.put("gov", "whois.nic.gov");
        map.put("gr", "whois.ripe.net");
        map.put("gs", "whois.nic.gs");
        map.put("hk", "whois.hknic.net.hk");
        map.put("hm", "whois.registry.hm");
        map.put("hn", "whois2.afilias-grs.net");
        map.put("hr", "whois.dns.hr");
        map.put("hu", "whois.nic.hu");
        map.put("hu.com", "whois.centralnic.com");
        map.put("ie", "whois.domainregistry.ie");
        map.put("il", "whois.isoc.org.il");
        map.put("in", "whois.inregistry.net");
        map.put("info", "whois.afilias.info");
        map.put("int", "whois.isi.edu");
        map.put("io", "whois.nic.io");
        map.put("iq", "vrx.net");
        map.put("ir", "whois.nic.ir");
        map.put("is", "whois.isnic.is");
        map.put("it", "whois.nic.it");
        map.put("je", "whois.je");
        map.put("jobs", "jobswhois.verisign-grs.com");
        map.put("jp", "whois.jprs.jp");
        map.put("ke", "whois.kenic.or.ke");
        map.put("kg", "whois.domain.kg");
        map.put("kr", "whois.nic.or.kr");
        map.put("la", "whois2.afilias-grs.net");
        map.put("li", "whois.nic.li");
        map.put("lt", "whois.domreg.lt");
        map.put("lu", "whois.restena.lu");
        map.put("lv", "whois.nic.lv");
        map.put("ly", "whois.lydomains.com");
        map.put("ma", "whois.iam.net.ma");
        map.put("mc", "whois.ripe.net");
        map.put("md", "whois.nic.md");
        map.put("me", "whois.nic.me");
        map.put("mil", "whois.nic.mil");
        map.put("mk", "whois.ripe.net");
        map.put("mobi", "whois.dotmobiregistry.net");
        map.put("ms", "whois.nic.ms");
        map.put("mt", "whois.ripe.net");
        map.put("mu", "whois.nic.mu");
        map.put("mx", "whois.nic.mx");
        map.put("my", "whois.mynic.net.my");
        map.put("name", "whois.nic.name");
        map.put("net", "whois.verisign-grs.com");
        map.put("nf", "whois.nic.cx");
        map.put("ng", "whois.nic.net.ng");
        map.put("nl", "whois.domain-registry.nl");
        map.put("no", "whois.norid.no");
        map.put("no.com", "whois.centralnic.com");
        map.put("nu", "whois.nic.nu");
        map.put("nz", "whois.srs.net.nz");
        map.put("org", "whois.pir.org");
        map.put("pl", "whois.dns.pl");
        map.put("pr", "whois.nic.pr");
        map.put("pro", "whois.registrypro.pro");
        map.put("pt", "whois.dns.pt");
        map.put("pw", "whois.nic.pw");
        map.put("ro", "whois.rotld.ro");
        map.put("ru", "whois.tcinet.ru");
        map.put("sa", "saudinic.net.sa");
        map.put("sa.com", "whois.centralnic.com");
        map.put("sb", "whois.nic.net.sb");
        map.put("sc", "whois2.afilias-grs.net");
        map.put("se", "whois.nic-se.se");
        map.put("se.com", "whois.centralnic.com");
        map.put("se.net", "whois.centralnic.com");
        map.put("sg", "whois.nic.net.sg");
        map.put("sh", "whois.nic.sh");
        map.put("si", "whois.arnes.si");
        map.put("sk", "whois.sk-nic.sk");
        map.put("sm", "whois.nic.sm");
        map.put("st", "whois.nic.st");
        map.put("so", "whois.nic.so");
        map.put("su", "whois.tcinet.ru");
        map.put("tc", "whois.adamsnames.tc");
        map.put("tel", "whois.nic.tel");
        map.put("tf", "whois.nic.tf");
        map.put("th", "whois.thnic.net");
        map.put("tj", "whois.nic.tj");
        map.put("tk", "whois.nic.tk");
        map.put("tl", "whois.domains.tl");
        map.put("tm", "whois.nic.tm");
        map.put("tn", "whois.ati.tn");
        map.put("to", "whois.tonic.to");
        map.put("tp", "whois.domains.tl");
        map.put("tr", "whois.nic.tr");
        map.put("travel", "whois.nic.travel");
        map.put("tw", "whois.twnic.net.tw");
        map.put("tv", "whois.nic.tv");
        map.put("tz", "whois.tznic.or.tz");
        map.put("ua", "whois.ua");
        map.put("uk", "whois.nic.uk");
        map.put("uk.com", "whois.centralnic.com");
        map.put("uk.net", "whois.centralnic.com");
        map.put("gov.uk", "whois.ja.net");
        map.put("us", "whois.nic.us");
        map.put("us.com", "whois.centralnic.com");
        map.put("uy", "nic.uy");
        map.put("uy.com", "whois.centralnic.com");
        map.put("uz", "whois.cctld.uz");
        map.put("va", "whois.ripe.net");
        map.put("vc", "whois2.afilias-grs.net");
        map.put("ve", "whois.nic.ve");
        map.put("vg", "whois.adamsnames.tc");
        map.put("ws", "whois.website.ws");
        map.put("xxx", "whois.nic.xxx");
        map.put("yu", "whois.ripe.net");
        map.put("za.com", "whois.centralnic.com");
        return Collections.unmodifiableMap(map);
    }

}