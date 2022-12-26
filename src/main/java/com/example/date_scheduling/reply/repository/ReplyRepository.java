package com.example.date_scheduling.reply.repository;

import com.example.date_scheduling.reply.entity.Reply;

// 역할 : 댓글 데이터를 CRUD 한다.
public interface ReplyRepository {

    // 댓글 등록 기능
    boolean register(Reply reply);

    // 댓글 수정 기능


}
