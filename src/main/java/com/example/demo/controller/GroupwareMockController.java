package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GwDocListRequest;
import com.example.demo.dto.GwDocListResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ekp/service/openapi")
public class GroupwareMockController {
  
  @PostMapping("/IF_APP_DOC_HTML")
  public List<GwDocListResponse> getGroupWareList(
    @Valid @RequestBody GwDocListRequest request
  ) {
    GwDocListResponse response = new GwDocListResponse();

    response.setDocId("DOC-001");
    response.setDocTitle("테스트 결재문서");
    response.setApprCompleteDt("2026-05-07 12:00:00");

    response.setDocDrftId("user01");
    response.setDocDrftNm("홍길동");

    response.setDocApprId("user02");
    response.setDocApprNm("김승인");

    response.setAttcFileNm("테스트.pdf");
    response.setAttcFileId("FILE-001");

    return List.of(response);
    // return List.of();
  }

  @PostMapping("/IF_APP_DOC_UPLOAD")
  public Map<String, Object> uploadGroupWare() {
      return Map.of(
        "resultCode", "Success",
        "resultMessage", "업로드 성공",
        "data", true
      );
  }

}
