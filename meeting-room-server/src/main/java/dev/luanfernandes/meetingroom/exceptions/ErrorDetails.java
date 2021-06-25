package dev.luanfernandes.meetingroom.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Setter
public class ErrorDetails {

    private final LocalDateTime timestamp;
    private final String message;
    private final String details;
}
