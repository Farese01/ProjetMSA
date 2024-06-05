package MSA.node;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("Sport")
public class Sport {

    @Id
    Long id;
    String name;

    @Relationship(type = "ORGANIZES", direction = Relationship.Direction.OUTGOING)
    private List<Event> events = new ArrayList<>();

}