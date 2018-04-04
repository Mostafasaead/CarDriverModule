package com.mytaxi.datatransferobject;

/**
 * @author Mostafa El-Gazzar.
 */
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManufacturerDTO {
	Long id;
}
