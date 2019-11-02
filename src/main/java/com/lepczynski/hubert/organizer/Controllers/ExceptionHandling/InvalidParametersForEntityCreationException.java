package com.lepczynski.hubert.organizer.Controllers.ExceptionHandling;

public class InvalidParametersForEntityCreationException extends RuntimeException
{
    public InvalidParametersForEntityCreationException(String entityName) {super(entityName+ " cannot be created with given parameteres");}
}
