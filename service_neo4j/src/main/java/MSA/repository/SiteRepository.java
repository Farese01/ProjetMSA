package MSA.repository;

import MSA.node.Site;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface SiteRepository extends ReactiveNeo4jRepository<Site, UUID> {
    @Query("Match(n) return n")
    Flux<Site> findAll();
    @Query("MATCH (n:Site {name: $name}) RETURN n")
    Mono<Site> findByName(String name);
    @Query("MATCH (n:Site) WHERE id(n) = $id RETURN n")
    Mono<Site> findById(UUID id);
    @Query("MATCH (site:Site)-[:LOCATED_AT]-(e:Event)-[:ORGANIZES]-(s:Sport) WHERE s.name = $sportName RETURN site")
    Flux<Site> findBySport(String sportName);

    @Query("MATCH (site:Site)-[:LOCATED_AT]-(e:Event) WHERE e.date = $date RETURN site")
    Flux<Site> findByEventDate(String date);

    @Query("MATCH (site:Site)-[:LOCATED_AT]-(e:Event)-[:ORGANIZES]-(s:Sport) WHERE s.name = $sportName AND e.date = $date RETURN site")
    Flux<Site> findBySportAndEventDate(String sportName, String date);
}