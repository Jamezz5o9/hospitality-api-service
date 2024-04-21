package com.mkobo.hospitality.apiserverice.dto.request;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateStaffRequest {

    @NotBlank(message = "Name cannot be empty")
    private String name;
}
