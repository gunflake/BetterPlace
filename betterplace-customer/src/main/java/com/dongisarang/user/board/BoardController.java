package com.dongisarang.user.board;

import com.dongisarang.user.page.PageMaker;
import com.dongisarang.user.page.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/")
@Log
public class BoardController {

    @Autowired
    BoardRepository repo;

    @GetMapping("notice")
    public String goNotice(PageVO vo, Model model) {
        log.info("notice call");

        /*for(int i = 0; i < 40; i++) {
            Board bo = new Board();
            bo.setBoardNo(i+1);
            bo.setTitle("Title0"+(i+1));
            bo.setContent("Content0"+(i+1));
            bo.setBoardType(0);
            repo.save(bo);
        }*/

        Pageable page = vo.makePageable("boardNo");

        Page<Board> result = repo.findAll(repo.makePredicate(null,null), page);

        log.info("" + page);
        log.info("" + result);

        log.info("total page number" + result.getTotalPages());

        /*for( Board board : result) {
            log.info(board.getContent());
        }*/

        PageMaker<Board> paging = new PageMaker<>(result);

        model.addAttribute("result", result);

        return "pages/notice";
    }

    @PostMapping("notice")
    public void searchNotice(Pageable pageable) {
        log.info("post notice");

        //System.out.println(pageable.getPageNumber());
        //System.out.println(pageable.getPageSize());
        //System.out.println(pageable.getSort());
    }

    @GetMapping("info")
    public String goInfo(Pageable pageable, Model model) {
        log.info("info call");
        return "pages/info";
    }
}
