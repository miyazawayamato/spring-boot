package com.example.project;


import java.util.List;

import com.example.project.Entity.Products;
import com.example.project.Form.ProductsForm;
import com.example.project.Service.ProductsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
    
	// トップ・ログイン、新規登録、テストユーザー
	// メニュー・最後でいい？
	
	// 商品管理画面・crud 削除
	// 納品先情報・crud 削除
	
	// 納品登録 createのみ
	// 納品履歴 read delete put 並べ替えも？
	
	// 登録画面 納品先と商品のallを返す
	// 登録post create
	// 履歴 deliIdとhisの一覧
	// 変更 
	// 削除 



	// 納品書出力 pdf 後実装
	
	// ユーザー 認証 
	
	@Autowired
	ProductsService products;
	
	@RequestMapping("/test")
	public @ResponseBody List<Products> getAll() {
		
		return products.getAll();
		
	}
	
	@ModelAttribute
    public  ProductsForm setupForm() {
        return new  ProductsForm();
    }
	
	@RequestMapping(value = "/product/create", method = RequestMethod.POST)
    public @ResponseBody Products createProducts(@Validated ProductsForm form, BindingResult bindingResult) {
        
        Products pro = products.create(form);
        return pro;
    }
	
	
    @RequestMapping("/hello")
	public String index() {
		return "signup";
	}
    
}
