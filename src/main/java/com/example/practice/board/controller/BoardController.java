package com.example.practice.board.controller;

import com.example.practice.board.dto.BoardDto;
import com.example.practice.board.dto.BoardDtoPage;
import com.example.practice.board.service.BoardService;
import com.example.practice.board.util.Pagination;
import com.example.practice.comment.dto.CommentDto;
import com.example.practice.comment.dto.CommentDtoPage;
import com.example.practice.comment.service.CommentService;
import com.example.practice.login.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class BoardController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BoardService boardService;


    // 페이지당 댓글 수를 기준으로 댓글 페이지 정보를 반환하는 메서드
    public CommentDtoPage getCommentDtoPage(int pageNum, int pageSize, int board_id) {
        int size = 5;
        int total = commentService.commentCount(board_id); // 전체 댓글 수 조회
        List<CommentDto> commentDtos = commentService.commentSelectDescPage((pageNum - 1) * size, size, board_id); // 페이지에 해당하는 사용자 목록 조회
        return new CommentDtoPage(total, pageNum, size, commentDtos); // 댓글 페이지 정보를 생성하여 반환
    }

    // 페이지당 회원 수를 기준으로 사용자 페이지 정보를 반환하는 메서드
    public BoardDtoPage getBoardDtoPage(int pageNum, int pageSize) {
        int size = 10;
        int total = boardService.boardAllCount(); // 전체 사용자 수 조회
        List<BoardDto> boardDtos = boardService.boardAllSelectDescPage((pageNum - 1) * size, size); // 페이지에 해당하는 사용자 목록 조회
        return new BoardDtoPage(total, pageNum, size, boardDtos); // 사용자 페이지 정보를 생성하여 반환
    }
    
    //Main
    @GetMapping("board/main")
    public String boardMain(Model model, Pagination pagination, BoardDto boardDto, @AuthenticationPrincipal UserDto userDto) {
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("user", userDto);
		params.put("pagination", pagination);
		params.put("boardDto", boardDto);
		int totalcnt = boardService.boardAllListCnt(params);
		
		model.addAttribute("boardDto", boardDto);		

		pagination.setListSize(15);
		pagination.setUrl("/board/main");
		pagination.pageInfo(totalcnt);
		
		List<BoardDto> boardList = boardService.boardAllList(params);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageResult",pagination.pagingResult());
		
        return "boardMain2";
    }

    @GetMapping("board/insert")
    public String boardInsert(@AuthenticationPrincipal UserDto userDto) {
        System.out.println(userDto);
        return "thymeleaf/board/boardInsert";
    }
    @GetMapping("board/oneInsert")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> boardInsertPost(@AuthenticationPrincipal UserDto userDto,
                                                            @RequestParam(value = "board_title") String board_title,
                                                            @RequestParam(value = "board_sub") String board_sub) {
        Map<String, Object> response = new HashMap<>();
        System.out.println(board_title);
        System.out.println("boardsub:" + board_sub);
        System.out.println(userDto);
        boardService.boardInsert(userDto.getUser_no(), board_title, board_sub);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("board/view")
    public String boardView(@AuthenticationPrincipal UserDto userDto, @RequestParam(value = "board_id") int board_id, Model model,
                            @RequestParam(name = "pageNo", required = false) String pageNo){
        int pageSize = 5; // 페이지 크기 설정 (한 페이지에 보여줄 회원 수)
        int pageNum = 1;
        try {
            if (pageNo != null) {
                pageNum = Integer.parseInt(pageNo);
            }
        } catch (NumberFormatException e) {
            // 페이지 번호가 숫자로 변환되지 않을 때 처리할 코드 추가
            // 예를 들어, 기본 페이지 번호를 설정하거나 에러 핸들링을 수행합니다.
            pageNum = 1; // 기본 페이지 번호 설정
        }

        System.out.println(board_id);
        BoardDto boardDto = boardService.boardOneSelect(board_id);
        int role = userDto.getRole();
        int user_no = userDto.getUser_no();
        CommentDtoPage commentDtoPage = getCommentDtoPage(pageNum, pageSize, board_id);
//        System.out.println("user_no 값:"+user_no);
//        System.out.println("boardDto 값:"+boardDto);
        model.addAttribute("boardDto", boardDto);
        model.addAttribute("listPage", commentDtoPage);
        model.addAttribute("user_no", user_no);
        model.addAttribute("role", role);
        return "thymeleaf/board/boardView";
    }

    @GetMapping("board/update")
    public String boardUpdate(@AuthenticationPrincipal UserDto userDto, @RequestParam(value = "board_id") int board_id, Model model){
        BoardDto boardDto = boardService.boardOneSelect(board_id);
        int role = userDto.getRole();
        int user_no = userDto.getUser_no();

        model.addAttribute("boardDto", boardDto);
        model.addAttribute("user_no", user_no);
        model.addAttribute("role", role);
        return "thymeleaf/board/boardUpdate";
    }

    @PostMapping("board/update")
    public String boardUpdate(@AuthenticationPrincipal UserDto userDto, @RequestParam(value = "board_id") int board_id,
                              @RequestParam(value = "board_title") String board_title, @RequestParam(value = "board_sub") String board_sub){
        BoardDto boardDto = boardService.boardOneSelect(board_id);
        int role = userDto.getRole();
        int user_no = userDto.getUser_no();

        if (user_no == boardDto.getUser_no() || role == 1) {

            boardService.boardUpdate(board_title,board_sub,board_id);
        }
        return "redirect:/board/view?board_id=" + board_id;
    }

    @GetMapping("/board/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> boardDelete(
            @RequestParam("board_id") int board_id) {
        Map<String, Object> response = new HashMap<>();

//        // 삭제한 amfi_no를 응답 데이터에 추가
//        response.put("occ_no", occ_no);
//        response.put("ccim_no", ccim_no);
//        response.put("ccim_title",ccim_title);

        // 해당 amfi_no를 삭제
        boardService.boardDelete(board_id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/board/dataTableList")
    public ResponseEntity<Map<String, Object>> dataTableList(Pagination pagination, BoardDto boardDto) {
    	 Map<String, Object> response = new HashMap<>();
    	 HashMap<String, Object> params = new HashMap<String, Object>();
    	 List<BoardDto> boardList = new ArrayList<BoardDto>(); 	
    	 
    	 params.put("pagination", pagination);
    	 params.put("boardDto", boardDto);
    	 boardList = boardService.boardAll(params);
    	 response.put("boardList", boardList);
    	 response.put("pagination", pagination);
    	 return new ResponseEntity<>(response, HttpStatus.OK);
    }
   
}
