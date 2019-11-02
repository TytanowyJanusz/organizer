package com.lepczynski.hubert.organizer.Controllers.ExceptionHandling;

public class MasterTaskNotFoundException extends RuntimeException
{
    public MasterTaskNotFoundException(){super("Couldn't find task referenced as master task");}
}
