package com.mkobo.hospitality.apiserverice.dto.request;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdateStaffRequest {

    @NotBlank(message = "Name cannot be blank.")
    private String name;
}

