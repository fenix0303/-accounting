package ua.sych.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FilterProductRequest {

    List<OneFilterProductRequest> oneFilterProductRequests = new ArrayList<>();

}
