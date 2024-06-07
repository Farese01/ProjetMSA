package MSA.service;

import MSA.node.Sport;
import MSA.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class SportService {

    @Autowired
    private SportRepository sportRepository;

    public Flux<Sport> getAllSports() {
        return sportRepository.findAll();
    }

    public Mono<Sport> getSportById(UUID id) {
        return sportRepository.findById(id);
    }

    public Mono<Sport> getSportByName(String name) {
        return sportRepository.findByName(name);
    }

    public Flux<Sport> getSportsByEventDate(String date) {
        return sportRepository.findByEventDate(date);
    }

    public Flux<Sport> getSportsByEventSite(String siteName) {
        return sportRepository.findByEventSite(siteName);
    }

    public Flux<Sport> getSportsByEventDateAndSite(String date, String siteName) {
        return sportRepository.findByEventDateAndSite(date, siteName);
    }

    public Mono<Sport> createSport(Sport sport) {
        sport.setId(UUID.randomUUID());
        return sportRepository.save(sport);
    }

    public Mono<Sport> updateSport(UUID id, Sport sport) {
        return sportRepository.findById(id)
                .flatMap(existingSport -> {
                    existingSport.setName(sport.getName());
                    existingSport.setEvents(sport.getEvents());
                    return sportRepository.save(existingSport);
                });
    }

    public Mono<Void> deleteSport(UUID id) {
        return sportRepository.deleteById(id);
    }
}
