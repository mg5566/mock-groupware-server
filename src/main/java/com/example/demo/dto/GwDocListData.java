package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GwDocListData {
  private String result;
  private String message;
  private List<GwDocListResponse> list;
}
