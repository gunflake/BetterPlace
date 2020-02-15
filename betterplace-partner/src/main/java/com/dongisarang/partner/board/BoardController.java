package com.dongisarang.partner.board;

import com.dongisarang.partner.page.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardRepository repo;

    @GetMapping("/notice")
    public String goNotice(@ModelAttribute("pageVO") PageVO vo, Model model) {
        List<Board> result = repo.findAll();
        for(int i=0;i<40;i++)
        {
            Board tmpBoard = new Board();
            tmpBoard.setBoardNo(i);
            tmpBoard.setBoardType(1);
            tmpBoard.setTitle("[공지] 2019.12.24 ~ 12.29 고객센터 성수기 운영 / 휴무 시간 안내");
            tmpBoard.setContent("안녕하세요, 스페이스클라우드 게스트 여러분!\n" +
                    "\n" +
                    "2019년 12월 24일 ~ 29일 성수기 시즌을 맞이해 스페이스클라우드 고객센터 운영 및 휴무시간 안내드립니다.\n" +
                    "\n" +
                    "월 ~ 금 평일은 동일하게 운영되며, 평일이 아닌 공휴일에는 온라인 문의만 접수가 되오니 이용에 참고하시기 바랍니다.");
            result.add(tmpBoard);
        }
        model.addAttribute("result", result);
        return "page/notice";
    }
}

