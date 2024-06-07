package MSA.controller;

import MSA.node.Sport;
import MSA.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/neo4j/sports")
public class SportController {

    @Autowired
    private SportService sportService;

    @GetMapping
    public Flux<Sport> getAllSports() {
        return sportService.getAllSports();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Sport>> getSportById(@PathVariable UUID id) {
        return sportService.getSportById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public Mono<ResponseEntity<Sport>> getSportByName(@PathVariable String name) {
        return sportService.getSportByName(name)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/date/{date}")
    public Flux<Sport> getSportsByEventDate(@PathVariable String date) {
        return sportService.getSportsByEventDate(date);
    }

    @GetMapping("/site/{siteName}")
    public Flux<Sport> getSportsByEventSite(@PathVariable String siteName) {
        return sportService.getSportsByEventSite(siteName);
    }

    @GetMapping("/date/{date}/site/{siteName}")
    public Flux<Sport> getSportsByEventDateAndSite(@PathVariable String date, @PathVariable String siteName) {
        return sportService.getSportsByEventDateAndSite(date, siteName);
    }

    @PostMapping
    public Mono<Sport> createSport(@RequestBody Sport sport) {
        return sportService.createSport(sport);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Sport>> updateSport(@PathVariable UUID id, @RequestBody Sport sport) {
        return sportService.updateSport(id, sport)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteSport(@PathVariable UUID id) {
        return sportService.deleteSport(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
