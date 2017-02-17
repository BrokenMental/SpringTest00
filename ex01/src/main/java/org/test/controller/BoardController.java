package org.test.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.test.domain.BoardVO;
import org.test.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception{
		logger.info("register get ...........");
	}
	
	/*
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registPOST(BoardVO board, Model model) throws Exception {
		logger.info("regist post ...........");
		logger.info(board.toString());
		
		service.regist(board);
		
		model.addAttribute("result", "success");
		
		//return "/board/success";
		return "redirect:/board/listAll";
	}
	*/
	// RedirectAttributes의 addFlashAttribute를 이용하면 uri 뒤에붙는 부가정보를 가릴수 있다.
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registPOST(BoardVO board, RedirectAttributes rttr)
	throws Exception {
		
		logger.info("regist post ...........");
		logger.info(board.toString());
		
		service.regist(board);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		
		logger.info("show all list...........");
		model.addAttribute("list", service.listAll());
		
	}
	
}
