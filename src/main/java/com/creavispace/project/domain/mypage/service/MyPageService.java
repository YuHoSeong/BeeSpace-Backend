package com.creavispace.project.domain.mypage.service;

import com.creavispace.project.domain.bookmark.service.BookmarkService;
import com.creavispace.project.domain.feedback.service.FeedbackService;
import com.creavispace.project.domain.like.service.ProjectLikeService;
import com.creavispace.project.domain.member.service.MemberService;
import com.creavispace.project.domain.project.service.ProjectService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/*
 * 마이 페이지에 필요한 정보
 * 사용자 이름1
 * 사용자 자기소개1
 * 사용자 관심 스택1
 * 사용자 직무1
 * 사용자가 게시한 게시글 리스트(커뮤니티, 모집 서비스 필요)
 * 사용자 북마크1
 * 사용자가 받은 피드백1
 * 사용자가 작성한 댓글1
 *
 * --기능--
 * 프로필 조회(이름, 자기소개, 관심 스택, 직무, 프로필)
 * 프로필 수정(프로필, 관심 스택, 직무, 자기소개)
 * 게시글 리스트 조회(프로젝트, 모집, 커뮤니티)
 * 북마크 리스트(프로젝트, 모집)
 * 댓글 리스트
 * 피드백 리스트
 *
 * */
@Service
@RequiredArgsConstructor
public class MyPageService {

    //이름, 자기소개, 관심 스택, 사용자 직무
    private MemberService memberService;
    //게시글 리스트
    private ProjectService projectService;
    //북마크 리스트
    private BookmarkService projectBookmarkService;
    //좋아요 누른 게시글 리스트
    private ProjectLikeService projectLikeService;
    //받은 피드백
    private FeedbackService feedbackService;


    //사용자 프로필 조회
    public Map<String, String> readMyProfile() {
        return new HashMap<>();
    }

    //게시글, 피드백, 댓글, 북마크를 맵에 담아서 한번에 리턴
    public Map<String, List<Object>> readMyContent() {
        return new HashMap<>();
    }

    //프로필, 관심스택 수정
}
