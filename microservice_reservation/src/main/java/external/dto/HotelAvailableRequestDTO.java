package external.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // Constructor sin parámetros
@AllArgsConstructor // Constructor con todos los parámetros
@Getter
@Setter
public class HotelAvailableRequestDTO {

	private String destination;
	private LocalDate from;
	private LocalDate to;
}
