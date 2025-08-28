package io.github.mortenjenne;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
public class Task {
    private String title;
    private String description;
    private LocalDate dueDate;
}
