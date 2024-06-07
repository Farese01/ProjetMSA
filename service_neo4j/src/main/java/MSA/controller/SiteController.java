package MSA.controller;

import MSA.node.Site;
import MSA.service.SiteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequestMapping("/api/neo4j/sites")
@AllArgsConstructor
public class SiteController {

    @Autowired
    private SiteService siteService;

    @GetMapping
    public Flux<Site> getAllSites() {
        return siteService.getAllSites();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Site>> getSiteById(@PathVariable UUID id) {
        return siteService.getSiteById(id)
                .map(site -> ResponseEntity.ok(site))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public Mono<ResponseEntity<Site>> getSiteByName(@PathVariable String name) {
        return siteService.getSiteByName(name)
                .map(site -> ResponseEntity.ok(site))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/sport/{sportName}")
    public Flux<Site> getSitesBySport(@PathVariable String sportName) {
        return siteService.getSitesBySport(sportName);
    }

    @GetMapping("/date/{date}")
    public Flux<Site> getSitesByEventDate(@PathVariable String date) {
        return siteService.getSitesByEventDate(date);
    }

    @GetMapping("/sport/{sportName}/date/{date}")
    public Flux<Site> getSitesBySportAndEventDate(@PathVariable String sportName, @PathVariable String date) {
        return siteService.getSitesBySportAndEventDate(sportName, date);
    }

    @PostMapping
    public Mono<Site> createSite(@RequestBody Site site) {
        return siteService.createSite(site);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteSite(@PathVariable UUID id) {
        return siteService.deleteSite(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}