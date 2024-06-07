package MSA.repository;

import MSA.node.Event;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface EventRepository extends ReactiveNeo4jRepository<Event, UUID> {
    @Query("Match(n) return n")
    Flux<Event> findAll();
    @Query("MATCH (e:Event) WHERE id(e) = $id RETURN e")
    Mono<Event> findById(UUID id);
    @Query("MATCH (e:Event {date: $date}) RETURN e")
    Flux<Event> findByDate(String date);
}