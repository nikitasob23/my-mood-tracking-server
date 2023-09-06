package com.niksob.authorization_model.mapper.error;

import com.niksob.authorization_model.exception.response.ResponseStatusException;
import com.niksob.authorization_model.model.response.error.ErrorDetails;
import javax.annotation.processing.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-06T23:23:49+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class ErrorDetailsMapperImpl implements ErrorDetailsMapper {

    @Override
    public ErrorDetails fromResponseStatusException(ResponseStatusException ex) {
        if ( ex == null ) {
            return null;
        }

        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setError( exHttpStatusReasonPhrase( ex ) );
        errorDetails.setTimestamp( ex.getTimestamp() );
        errorDetails.setMessage( ex.getMessage() );
        errorDetails.setPath( ex.getPath() );

        errorDetails.setStatus( ex.getHttpStatus().value() );

        return errorDetails;
    }

    private String exHttpStatusReasonPhrase(ResponseStatusException responseStatusException) {
        if ( responseStatusException == null ) {
            return null;
        }
        HttpStatus httpStatus = responseStatusException.getHttpStatus();
        if ( httpStatus == null ) {
            return null;
        }
        String reasonPhrase = httpStatus.getReasonPhrase();
        if ( reasonPhrase == null ) {
            return null;
        }
        return reasonPhrase;
    }
}
