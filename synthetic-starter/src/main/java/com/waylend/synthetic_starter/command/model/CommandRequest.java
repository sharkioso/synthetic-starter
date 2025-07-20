package com.waylend.synthetic_starter.command.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CommandRequest {

    @NotBlank
    @Size(max = 1000)
    private String description;
    
    @NotBlank
    private Prioryty prioryty;
    
    @NotBlank
    @Size(max = 100)
    private String author;

    @NotBlank
    @Pattern
    (
        regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:d{3}Z$",
        message = "Время должно быть в формате ISO-8601"
    )
    private String time;

}
