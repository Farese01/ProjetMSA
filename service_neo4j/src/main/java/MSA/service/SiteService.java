package MSA.service;


import MSA.DTO.DistanceDTO;
import MSA.DTO.SiteDTO;
import MSA.node.Site;
import MSA.relationship.Distance;
import MSA.repository.SiteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class SiteService {
    private final SiteRepository siteRepository;

    public Flux<Site> getAllSites(){
        return siteRepository.findAll();
    }


    public Mono<Site> createSite(SiteDTO siteDTO) {
        Site site = new Site(siteDTO.getName(), siteDTO.getLatitude(), siteDTO.getLongitude(), siteDTO.getIsParalympic());
        return siteRepository.save(site);
    }

    public Mono<Site> createDistance(DistanceDTO distanceDTO) {
        Mono<Site> fromSiteMono = siteRepository.findById(distanceDTO.getFromSiteId());
        Mono<Site> toSiteMono = siteRepository.findById(distanceDTO.getToSiteId());

        return fromSiteMono.zipWith(toSiteMono)
                .flatMap(tuple -> {
                    Site fromSite = tuple.getT1();
                    Site toSite = tuple.getT2();

                    Distance distance = new Distance(null, distanceDTO.getDistance(), distanceDTO.getTravelTime(), toSite);
                    fromSite.addDistance(distance);

                    return siteRepository.save(fromSite);
                });
    }

    public void deleteSite(Long id) {
        siteRepository.deleteById(id);
    }
}
