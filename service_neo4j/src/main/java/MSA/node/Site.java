package MSA.node;

import MSA.relationship.Distance;
import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("Site")

public class Site {
    @Id
    @GeneratedValue
    Long id;
    String name;
    Float latitude;
    Float longitude;
    Boolean is_paralympic;

    @Relationship(type = "DISTANCE_TO", direction = Relationship.Direction.OUTGOING)
    private List<Distance> distances = new ArrayList<>();;

    public Site(String name) {
        this.name = name;
        this.latitude = 0.0F;
        this.longitude = 0.0F;
        this.is_paralympic = false;
    }
    public Site(String name, Float latitude, Float longitude, Boolean isParalympic) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.is_paralympic = isParalympic;
    }

    public void addDistance(Distance distance){
        this.distances.add(distance);
    }
}
