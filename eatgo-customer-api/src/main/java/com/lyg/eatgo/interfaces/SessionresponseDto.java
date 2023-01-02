package com.lyg.eatgo.interfaces;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionresponseDto {

    private String accessToken;
}
