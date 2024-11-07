package mas.s24365_projekt_koncowy.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotNull
    @Schema(description = "Transfer creation date", example = "2024-10-10")
    LocalDate transferDate;
    @NotNull
    @Schema(description = "Current state of transfer", example = "CREATED")
    State state;
    @NotNull
    Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    Account sender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    Account receiver;
}
