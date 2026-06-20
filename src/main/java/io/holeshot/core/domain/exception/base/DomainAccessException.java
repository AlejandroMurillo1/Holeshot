package io.holeshot.core.domain.exception.base;

public class DomainAccessException extends RuntimeException {
    public DomainAccessException(String message) {
        super(message);
    }
}
