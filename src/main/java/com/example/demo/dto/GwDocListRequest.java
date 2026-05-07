package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GwDocListRequest {

  @NotBlank
  private String userId;

  @NotBlank
  private String sdate;
  @NotBlank
  private String edate;

  @NotNull
  private Long pageSize;
  @NotNull
  private Long pageNumber;


  private String keyword;
  private String searchType;
}
