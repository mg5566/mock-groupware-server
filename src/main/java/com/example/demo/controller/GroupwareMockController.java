package com.example.demo.controller;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  private static final Logger log = LoggerFactory.getLogger(GroupwareMockController.class);
  
  @PostMapping("/IF_APP_DOC_LIST")
  public Map<String, Object> getGroupWareList(
    @Valid @RequestBody GwDocListRequest request
  ) {
    log.info(
      "IF_APP_DOC_LIST called: userId={}, sdate={}, edate={}, pageNumber={}, pageSize={}",
      request.getUserId(),
      request.getSdate(),
      request.getEdate(),
      request.getPageNumber(),
      request.getPageSize()
    );

    if ("fail".equals(request.getUserId())) {
      log.warn("IF_APP_DOC_LIST failed for userId={}", request.getUserId());
      return Map.of(
        "responseText", "SUCCESS",
        "data", Map.of(
          "result", "fail",
          "message", "오류가 발생했습니다."
        )
      );
    }

    List<GwDocListResponse> responses = createDummyDocs();
    
    return Map.of(
      "responseText", "SUCCESS",
      "data", responses
    );
  }

  private List<GwDocListResponse> createDummyDocs() {
    return java.util.stream.IntStream.rangeClosed(1, 10)
      .mapToObj(this::createDummyDoc)
      .toList();
  }

  private GwDocListResponse createDummyDoc(int index) {
    GwDocListResponse response = new GwDocListResponse();

    response.setDocId(String.format("DOC-%03d", index));
    response.setDocTitle("테스트 결재문서 " + index);
    response.setApprCompleteDt("2026-05-07 12:00:00");

    response.setDocDrftId("user01");
    response.setDocDrftNm("홍길동");

    response.setDocDrftDeptCd("D001");
    response.setDocDrftDeptNm("경영관리본부");

    response.setDocApprId("user02");
    response.setDocApprNm("김승인");

    response.setDocDrftDeptCd("D001");
    response.setDocDrftDeptNm("경영관리본부");

    response.setAttcFileNm("테스트" + index + ".pdf|TEST" + index + ".txt");
    response.setAttcFileId(
      String.format("FILE-%03d|FILE-%03d", index, index + 100)
    );

    return response;
  }

  @PostMapping("/IF_APP_DOC_UPLOAD")
  public Map<String, Object> uploadGroupWare(
    @RequestBody Map<String, Object> request
  ) {
    log.info("IF_APP_DOC_UPLOAD called: {}", request);

    String fullPath = (String) request.get("docLoc");
    String fileName = (String) request.get("GW_FILE_NM");

    if (fullPath == null || fullPath.isBlank()) {
      return Map.of(
        "resultCode", "Fail",
        "resultMessage", "fullPath가 없습니다.",
        "data", false
      );
    }

    if (fileName == null || fileName.isBlank()) {
      return Map.of(
        "resultCode", "Fail",
        "resultMessage", "GW_FILE_NAME이 없습니다.",
        "data", false
      );
    }

    File parentDir = new File(fullPath);
    File file = new File(parentDir, fileName);

    try {
      if (!parentDir.exists()) {
        parentDir.mkdirs();
      }

      Files.write(
        file.toPath(),
        ("mock groupware file: " + fullPath + ", fileName: " + fileName).getBytes(StandardCharsets.UTF_8)
      );

      log.info(
        "mock file uploaded. fullPath={}, fileName={}, targetPath={}, exists={}, size={}",
        fullPath,
        fileName,
        file.getAbsolutePath(),
        file.exists(),
        file.length()
      );

      return Map.of(
        "resultCode", "Success",
        "resultMessage", "업로드 성공",
        "data", true
      );
    } catch (Exception e) {
      log.error("mock file upload failed. fullPath={}, fileName={}", fullPath, fileName, e);

      return Map.of(
        "resultCode", "Fail",
        "resultMessage", e.getMessage(),
        "data", false
      );
    }
  }
}
