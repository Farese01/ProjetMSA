package MSA.service;


import MSA.node.Site;
import MSA.repository.SiteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;

    public Flux<Site> getAllSites() {
        return siteRepository.findAll();
    }

    public Mono<Site> getSiteById(UUID id) {
        return siteRepository.findById(id);
    }

    public Mono<Site> getSiteByName(String name) {
        return siteRepository.findByName(name);
    }

    public Flux<Site> getSitesBySport(String sportName) {
        return siteRepository.findBySport(sportName);
    }

    public Flux<Site> getSitesByEventDate(String date) {
        return siteRepository.findByEventDate(date);
    }

    public Flux<Site> getSitesBySportAndEventDate(String sportName, String date) {
        return siteRepository.findBySportAndEventDate(sportName, date);
    }

    public Mono<Site> createSite(Site site) {
        return siteRepository.save(site);
    }

    public Mono<Void> deleteSite(UUID id) {
        return siteRepository.deleteById(id);
    }
}
