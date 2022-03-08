package ru.alexey.site.exception;
/* 
08.03.2022: Alexey created this file inside the package: ru.alexey.site.exception 
*/

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
