package com.staunch.tech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateReqDto {

    @NotNull
    private  int id;
    private String status;
    @NotNull(message="user id Empty")
    private int userId;
}