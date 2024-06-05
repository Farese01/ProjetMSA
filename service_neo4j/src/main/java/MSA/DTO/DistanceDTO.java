package MSA.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DistanceDTO {
    private Long fromSiteId;
    private Long toSiteId;
    private float distance;
    private float travelTime;
}
