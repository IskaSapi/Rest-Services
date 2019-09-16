package kz.bitlab.toDo.models.responses;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessaege {
    private boolean success;
    private String message;
}
