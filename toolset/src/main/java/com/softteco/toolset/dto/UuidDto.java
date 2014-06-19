package com.softteco.toolset.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 *
 * @author serge
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UuidDto implements Serializable {

    public String uuid;
}
