package com.gsm.domain.notice.presentation;

import com.gsm.domain.notice.presentation.dto.request.CreateNoticeRequest;
import com.gsm.domain.notice.presentation.dto.request.EditNoticeRequest;
import com.gsm.domain.notice.presentation.dto.response.NoticeDetailResponse;
import com.gsm.domain.notice.presentation.dto.response.NoticeListResponse;
import com.gsm.domain.notice.serivce.*;
import com.gsm.global.annotation.RestRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestRequestService("/notice")
public class NoticeController {

    private final ListNoticeService listNoticeService;
    private final GetNoticeDetailService getNoticeDetailService;
    private final CreateNoticeService createNoticeService;
    private final EditNoticeService editNoticeService;
    private final DeleteNoticeService deleteNoticeService;

    @GetMapping
    public ResponseEntity<NoticeListResponse> findAll(){
        var list = listNoticeService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeDetailResponse> findOne(@PathVariable("id") Long id){
        NoticeDetailResponse oneFindById = getNoticeDetailService.execute(id);
        return new ResponseEntity<>(oneFindById,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateNoticeRequest createNoticeRequest){
        createNoticeService.execute(createNoticeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody @Valid EditNoticeRequest editNoticeRequest){
        editNoticeService.execute(id, editNoticeRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        deleteNoticeService.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
