package com.studyspring.dogshop.controller;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.studyspring.dogshop.service.DogService;
import com.studyspring.dogshop.vo.Cart;
import com.studyspring.dogshop.vo.Dog;

/**
 * cookieに関する勉強のため作成。
 */
@Controller
public class HomeController {
	@Autowired
	private DogService service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, 
			HttpServletRequest request, HttpServletResponse response) {
		return "main";
	}
	
	@RequestMapping(value = "dogsList", method = RequestMethod.GET)
	public ModelAndView dogsList(ModelAndView mv, Dog vo, HttpServletRequest request) {
		//全体の犬リスト
		ArrayList<Dog> alldogsList = service.getDogList(vo);
		//今日チェックしたもののリスト
		ArrayList<Dog> cookieList = new ArrayList<>();
		Cookie[] cookies = request.getCookies();
		String[] check_id_array = null;
		String[] check_img_array = null;
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("check_id")) {
				String check_id = cookies[i].getValue();
				check_id_array = check_id.split(":");
			}
			if (cookies[i].getName().equals("check_img")) {
				String check_img = cookies[i].getValue();
				check_img_array = check_img.split(":");
			}
		}// for
		
		if (check_id_array != null) {
			for (int i = 0; i < check_id_array.length; i++) {
				Dog cookieVo = new Dog();
				cookieVo.setId(Integer.parseInt(check_id_array[i]));
				cookieVo.setImage(check_img_array[i]);
				cookieList.add(cookieVo);
			}
			
			if (cookieList.size() == 1 && cookieList.get(0).getId() == 0) {
				cookieList = null;
			} 
		}
		
		if (cookieList.size() == 0) {
			cookieList = null;
		}
		
		mv.setViewName("dogsList");
		mv.addObject("alldogsList", alldogsList);
		mv.addObject("cookieList", cookieList);
		return mv;
	};
	
	@RequestMapping(value = "getDogDetail", method = RequestMethod.GET)
	public ModelAndView getDogDetail(ModelAndView mv, Dog vo, 
			HttpServletRequest request, HttpServletResponse response) {
		
		//今日チェックしたもののidをクッキーに入れる
		Cookie[] cookies = request.getCookies();
		String[] check_id_array = null;
		String[] check_img_array = null;
		String check_id = String.valueOf(vo.getId());
		String check_img = vo.getImage();
		
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("check_id")) {
				check_id_array = cookies[i].getValue().split(":");
			} 
			if (cookies[i].getName().equals("check_img")) {
				check_img_array = cookies[i].getValue().split(":");
			}
		}
		if (check_id_array != null) {
			for (int i = 0; i < check_id_array.length; i++) {
				if (check_id_array[i].equals(String.valueOf(vo.getId()))) {
					continue;
				}
				check_id += ":" + check_id_array[i];
			}
			for (int i = 0; i < check_img_array.length; i++) {
				if (check_img_array[i].equals(vo.getImage())) {
					continue;
				}
				check_img += ":" + check_img_array[i];
			}
		}
		Cookie cookie_id = new Cookie("check_id", check_id);
		Cookie cookie_img = new Cookie("check_img", check_img);
		cookie_id.setMaxAge(60);
		cookie_img.setMaxAge(60);
		response.addCookie(cookie_id);
		response.addCookie(cookie_img);
		
		ArrayList<Dog> resultList = service.getDogList(vo); 
		mv.setViewName("dogDetail");
		mv.addObject("vo", resultList.get(0));
		return mv;
	}
	
	@RequestMapping(value = "addCart", method = RequestMethod.GET)
	public ModelAndView addCart(ModelAndView mv, Dog vo, 
			@RequestParam (name = "qty", defaultValue="1") int qty,
			@RequestParam (name="id", defaultValue = "0") int id,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (qty < 1) {
			qty = 1;
		}
		
		Cart cart = null;
		if (id != 0) {
			cart = new Cart();
			vo.setId(id);
			cart.setId(id);
			cart.setQty(qty);
		}
		
		Cookie[] cookies = request.getCookies();
		String cookie_id = String.valueOf(vo.getId()).trim();
		String cookie_cart = cookie_id;
		String cookie_qty = String.valueOf(qty);
		String[] cookieCart = null;	 // カートに入れてある犬リスト
		String[] cookieQty = null;	 //　カートに入れてある各犬の数量
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("cartList")) {
				cookieCart = cookies[i].getValue().split(":");
				for (int j = 0; j < cookieCart.length; j++) {
					if (!cookieCart[j].equals(cookie_id)) {
						cookie_cart += ":" + cookieCart[j];
					}
				}// inner for
			}
		}// outer for
		
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("qtyList")) {
				cookieQty = cookies[i].getValue().split(":");
				for (int j = 0; j < cookieCart.length; j++) {
					if (!cookieCart[j].equals(cookie_id)) {
						cookie_qty += ":" + cookieQty[j];
					}
				}// inner for
			}
		}
		
		ArrayList<Cart> cartList = new ArrayList<>();
		String[] cookie_cart_array = cookie_cart.split(":");
		String[] cookie_qty_array = cookie_qty.split(":");
		for (int i = 0; i < cookie_cart_array.length; i++) {
			Dog searchVo = new Dog();
			searchVo.setId(Integer.parseInt(cookie_cart_array[i]));
			Dog resultVo = service.getDogList(searchVo).get(0);
			Cart reCart = new Cart();
			reCart.setId(resultVo.getId());
			reCart.setKind(resultVo.getKind());
			reCart.setPrice(resultVo.getPrice());
			reCart.setImage(resultVo.getImage());
			
			if (cart.getId() == resultVo.getId()) {
				reCart.setQty(cart.getQty());
			} else {
				reCart.setQty(Integer.parseInt(cookie_qty_array [i]));
			}
			cartList.add(reCart);
		}// for
		
		Cookie qty_cookie = new Cookie("qtyList", cookie_qty );
		Cookie cart_cookie = new Cookie("cartList", cookie_cart);
		response.addCookie(qty_cookie);
		response.addCookie(cart_cookie);
		
		mv.setViewName("cart");
		mv.addObject("cartList", cartList);
		return mv;
	}
	
	@RequestMapping (value = "addQty", method = RequestMethod.GET)
	public String addQty(int id, int qty, Model model, Dog vo) { // 
//		model.addAttribute("id", id);
//		model.addAttribute("qty", qty < 1 ? 1 : qty);
		// @RequestParamで受ける時にはmodelオブジェクトに入れなくてもそのまま渡される。
		//　ここで変数の値を変えても最初の値が渡される。
		return "forward:addCart";
	}
	
	@RequestMapping (value = "deleteCart", method = RequestMethod.GET)
	public ModelAndView deleteCart(ModelAndView mv, String[] checkedArray,
			HttpServletRequest request, HttpServletResponse response) {
		
		Cookie[] cookies = request.getCookies();
		String[] cookieCart = null;	 // カートに入れてある犬リスト
		String[] cookieQty = null;	 //　カートに入れてある各犬の数量
		String cookie_cart = "";
		String cookie_qty = "";
		
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("qtyList")) {
				cookieQty = cookies[i].getValue().split(":");
			}
		}// cookieQty
		
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("cartList")) {
				cookieCart = cookies[i].getValue().split(":");
			}
		}// cookieCart
		
		// cookieCart VS. checkedArray(delete target)
		if (checkedArray != null) {
			for (int i = 0; i < cookieCart.length; i++) {
				boolean flag = true;
				for (int j = 0; j < checkedArray.length; j++) {
					if (cookieCart[i].equals(checkedArray[j])) {
						flag = false;
						break;
					} 
				}
				if (flag) {
					cookie_cart += cookieCart[i];
					cookie_qty += cookieQty[i];
					if (i == cookieCart.length-1) {
						continue;
					}
					cookie_cart += ":";
					cookie_qty += ":";
				}
			}
		} else {
			for (int i = 0; i < cookieCart.length; i++) {
				cookie_cart += cookieCart[i];
				cookie_qty += cookieQty[i];
				if (i == cookieCart.length-1) {
					continue;
				}
				cookie_cart += ":";
				cookie_qty += ":";
			}
		}
		
		ArrayList<Cart> cartList = new ArrayList<>();
		String[] cookie_cart_array = cookie_cart.split(":");
		String[] cookie_qty_array = cookie_qty.split(":");
		if (cookie_cart == null || cookie_cart.equals("") ) {
			cartList = null;
		} else {
			for (int i = 0; i < cookie_cart_array.length; i++) {
				
				Dog searchVo = new Dog();
				searchVo.setId(Integer.parseInt(cookie_cart_array[i]));
				Dog resultVo = service.getDogList(searchVo).get(0);
				Cart reCart = new Cart();
				reCart.setId(resultVo.getId());
				reCart.setKind(resultVo.getKind());
				reCart.setPrice(resultVo.getPrice());
				reCart.setImage(resultVo.getImage());
				reCart.setQty(Integer.parseInt(cookie_qty_array [i]));
				cartList.add(reCart);
			}// for
		}
		Cookie qty_cookie = new Cookie("qtyList", cookie_qty );
		Cookie cart_cookie = new Cookie("cartList", cookie_cart);
		response.addCookie(qty_cookie);
		response.addCookie(cart_cookie);
		
		mv.setViewName("cart");
		mv.addObject("cartList", cartList);
		return mv;
	}
	
}
