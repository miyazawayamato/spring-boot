package com.example.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectApplicationTests {

	@Test
	void contextLoads() {
	}

}

//curlコマンド
// Invoke-RestMethod -Uri "http://localhost:8080/api/customer/delete/23" -Method DELETE 

// Invoke-RestMethod -Uri "http://localhost:8080/api/product/delete" -Method DELETE -Body ( @{"id"=1} | ConvertTo-Json) -ContentType "application/json"

// Invoke-RestMethod -Uri "http://localhost:8080/api/customer/create" -Method POST -Body ( @{"name"="企業１";"phone"=0265980489;"address"="zyuusyo14-219"} | ConvertTo-Json) -ContentType "application/json"

//
// Invoke-RestMethod -Uri "http://localhost:8080/api/derivery/register" -Method POST -Body ( @{"cutomerId"=1; "products"=@{"name"="更新テスト";"stock"=200; "price"=30} } | ConvertTo-Json) -ContentType "application/json"

// Invoke-RestMethod -Uri "http://localhost:8080/api/derivery/register" -Method POST -Body ( @{ "products"=[@["name"="test";"stock"=200; "price"=30]] } | ConvertTo-Json) -ContentType "application/json"

//数字もダブルクォーテーションで囲む
// curl -X POST -H 'Content-Type: application/json' -d '{"cutomerId":1 , "histories":[{"name":"ftko","qty":"345","price":"980"}, {"name":"1","qty":"900","price":"10"} ]}' http://localhost:8080/api/derivery/register

// curl -X POST -H 'Content-Type: application/json' -d '{"customer": "query" , "histories":[{"name":"yu","qty":"22","price":"222"}, {"name":"menndo","qty":"666","price":"66"} ], "products":[{"id":"4","stock":"50"}, {"id":"6","stock":"73"} ]}' http://localhost:8080/api/derivery/register

// Invoke-RestMethod -Uri "http://localhost:8080/api/product/put/test" -Method GET

