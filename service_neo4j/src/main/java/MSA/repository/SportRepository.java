package MSA.repository;

import MSA.node.Sport;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface SportRepository extends ReactiveNeo4jRepository<Sport, UUID> {
    @Query("Match(n) return n")
    Flux<Sport> findAll();
    @Query("MATCH (s:Sport) WHERE s.name = $name RETURN s")
    Mono<Sport> findByName(String name);
    @Query("MATCH (s:Sport) WHERE id(s) = $id RETURN s")
    Mono <Sport> findById(UUID id);
    @Query("MATCH (s:Sport)-[:ORGANIZES]->(e:Event) WHERE e.date = $date RETURN s")
    Flux<Sport> findByEventDate(String date);

    @Query("MATCH (s:Sport)-[:ORGANIZES]->(e:Event)-[:LOCATED_AT]->(site:Site) WHERE site.name = $siteName RETURN s")
    Flux<Sport> findByEventSite(String siteName);

    @Query("MATCH (s:Sport)-[:ORGANIZES]->(e:Event)-[:LOCATED_AT]->(site:Site) WHERE e.date = $date AND site.name = $siteName RETURN s")
    Flux<Sport> findByEventDateAndSite(String date, String siteName);
}