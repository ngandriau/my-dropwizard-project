package nico.dw.core

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length

/**
 * Created by ngandriau on 4/3/14.
 */
class Saying {

    @JsonProperty
    private long id;

    @Length(max = 3)
    @JsonProperty
    private String content;

}
