package com.example.practice.board.service;


import com.example.practice.board.dao.BoardDao;
import com.example.practice.board.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardDao boardDao;

    public Integer boardAllCount(){
        return boardDao.boardAllCount();
    };
    public List<BoardDto> boardAllSelectDescPage(int startRow, int size) {
        return boardDao.boardAllSelectDescPage(startRow,size);
    };

    public BoardDto boardOneSelect(int board_id){
        return boardDao.boardOneSelect(board_id);
    };

    public void boardInsert(int user_no, String board_title, String board_sub) {
        boardDao.boardInsert(user_no, board_title, board_sub);
    }

    public void boardUpdate(String board_title, String board_sub, int board_id) {
        boardDao.boardUpdate(board_title,board_sub,board_id);
    }
    public void boardDelete(int board_id){
        boardDao.boardDelete(board_id);
    }
	public List<BoardDto> boardAllList(HashMap<String, Object> params) {
		return boardDao.boardAllList(params);
	}
	public int boardAllListCnt(HashMap<String, Object> params) {
		return boardDao.boardAllListCnt(params);
	}
	public List<BoardDto> boardAll(HashMap<String, Object> params) {
		return boardDao.boardAllList(params);
	}
}
