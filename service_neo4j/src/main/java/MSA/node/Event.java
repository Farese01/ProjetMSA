package MSA.node;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("Event")
public class Event {
    @Id
    Long id;
    LocalDate date;

    @Relationship(type = "LOCATED_AT", direction = Relationship.Direction.OUTGOING)
    private List<Site> sites = new ArrayList<>();

}