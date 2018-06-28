package com.study.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.study.spring.dao.BoardDAO;
import com.study.spring.util.PageNavigator;
import com.study.spring.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;
	
	private final int countPerPage = 10;
	private final int pagePerGroup = 5;

	@Override
	public ArrayList<BoardVO> getList(BoardVO vo, PageNavigator navi, Map<String, String> map, String startFrom) {
		if (vo.getNo() == 0) {
			//startRecord:全体のレコードの中で現在のページの最初（０から始まる）
			//countPerPage:ページ当たりポスティング数
			return dao.getBoardList(vo, navi.getStartRecord(), navi.getCountPerPage(), map);
		} else {
			if (startFrom.equals("board")) {
				dao.addHit(vo.getNo());
			}
			return dao.getList(vo);
		}
	}

	@Override
	public int modifyBoard(BoardVO vo) {
		return dao.modifyBoard(vo);
	}

	@Override
	public int deleteBoard(int no) {
		return dao.deleteBoard(no);
	}

	@Override
	public int writeBoard(BoardVO vo) {
		return dao.writeBoard(vo);
	}

	@Override
	public PageNavigator getNavi(int currentPage, Map<String, String> map) {
		//1.全体のポスティング数をｄｂから数える。
		int totalRecordsCount = dao.getTotalBoard(map);
		PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, currentPage, totalRecordsCount);
		return navi;
	}

	@Override
	public void downloadFile(BoardVO vo, HttpServletResponse response) {
		// 実際のサーバーにセーブされているファイル名
		File file = new File("C:/Users/kim.sunhye/Desktop/JAVA/Spring/saveFileFolder/" + vo.getSave_file_sys());
		// ユーザーが確認したファイル名
		String save_file = vo.getSave_file();
		try {                
			// response.setHeader() : ファイルのダウンロードするということを明示           
			response.setHeader("Content-Disposition",                           
					"attachment;filename=" + URLEncoder.encode(save_file, "UTF-8"));                
			// response.setContentLength() 
			//			: クライアントのウェブブラウザに該当ファイルのファイル名及びファイルサイズほどの空間を確保するようにする。
			response.setContentLength((int)file.length());                
			// new FileInputStream(file) : サーバにセーブされたファイルをストリートで読んでくる。                
			// response.getOutputStream() : 読んできたファイルをストリームで送り出す。             
			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());           
			} catch (IOException e) {                
				e.printStackTrace();           
				} 
		}
		
}
