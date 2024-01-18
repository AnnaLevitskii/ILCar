package models;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Search {
    private String city;
    private String dateFrom;
    private String dateTo;
}
