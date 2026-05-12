package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GwApiResponse<T> {
  private String responseText;
  private T data;
  private String responseCode;
  private String systemError;
}
