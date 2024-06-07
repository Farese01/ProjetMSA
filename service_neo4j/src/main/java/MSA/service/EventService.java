package MSA.service;

import MSA.node.Event;
import MSA.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Flux<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Mono<Event> getEventById(UUID id) {
        return eventRepository.findById(id);
    }

    public Flux<Event> getEventByDate(String date) {
        return eventRepository.findByDate(date);
    }

    public Mono<Event> createEvent(Event event) {
        event.setId(UUID.randomUUID());
        return eventRepository.save(event);
    }

    public Mono<Event> updateEvent(UUID id, Event event) {
        return eventRepository.findById(id)
                .flatMap(existingEvent -> {
                    existingEvent.setDate(event.getDate());
                    existingEvent.setSites(event.getSites());
                    return eventRepository.save(existingEvent);
                });
    }

    public Mono<Void> deleteEvent(UUID id) {
        return eventRepository.deleteById(id);
    }
}
