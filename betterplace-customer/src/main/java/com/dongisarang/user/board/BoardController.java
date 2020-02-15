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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board/")
@Log
public class BoardController {

    @Autowired
    BoardRepository repo;

    @GetMapping("notice")
    public String goNotice(@ModelAttribute("pageVO") PageVO vo, Model model) {

        log.info("notice call");

        Iterable<Board> search = repo.findAll(repo.makePredicateNotice(vo.getType(), vo.getKeyword()));

        List<Board> result = new ArrayList<>();
        search.forEach(result::add);

        log.info(""+result.size());
        model.addAttribute("result", result);

        return "pages/notice";
    }

    @GetMapping("info")
    public String goInfo(@ModelAttribute("pageVO") PageVO vo, Model model) {
        log.info("notice call");

        if(vo.getType() == null) {
            vo.setType(0);
        }

        Iterable<Board> search = repo.findAll(repo.makePredicateInfo(vo.getType(), vo.getKeyword()));

        List<Board> result = new ArrayList<>();
        search.forEach(result::add);

        log.info(""+result.size());
        model.addAttribute("result", result);

        return "pages/info";
    }
}
