package MSA.controller;

import MSA.DTO.DistanceDTO;
import MSA.DTO.SiteDTO;
import MSA.node.Site;
import MSA.relationship.Distance;
import MSA.service.SiteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("site")
@AllArgsConstructor
public class SiteController {

    private final SiteService siteService;


    @GetMapping
    public Flux<Site> getAllSites(){
        return siteService.getAllSites();
    }

    @PostMapping
    public Mono<Site> createSite(@RequestBody SiteDTO siteDTO) {
        return siteService.createSite(siteDTO);
    }

    @PostMapping("/distance")
    public Mono<Site> createDistance(@RequestBody DistanceDTO distanceDTO) {
        return siteService.createDistance(distanceDTO);
    }

    //@PutMapping
    //public Site updateSite(@RequestBody Site site)  {return siteService.updateSite(site);}

    @DeleteMapping("/site/{siteId}")
    public void deleteSite(@PathVariable Long siteId){
        siteService.deleteSite(siteId);
    }

}