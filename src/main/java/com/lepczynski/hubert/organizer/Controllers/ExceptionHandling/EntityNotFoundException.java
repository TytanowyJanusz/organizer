package com.lepczynski.hubert.organizer.Controllers.ExceptionHandling;

public class EntityNotFoundException extends RuntimeException
{
    public EntityNotFoundException(Long id, String entityName)
    {
        super("Could not find "+entityName+" by this id: "+ id);
    }
}
