# README

https://github.com/eiKatou/Sample/tree/master/Spring/RESTWebService

# API

## 顧客一覧の取得
```bash
curl http://localhost:8080/api/customers -v -X GET
```

## 顧客一覧の取得（1ページ目：1件目から2件目）
```bash
curl "http://localhost:8080/api/customers?page=0&size=2" -v -X GET
```

## 顧客一覧の取得（2ページ目：3件目から4件目）
```bash
curl "http://localhost:8080/api/customers?page=1&size=2" -v -X GET
```


## 顧客の取得
```bash
curl http://localhost:8080/api/customers/1 -v -X GET
```

## 顧客の新規作成
```bash
curl http://localhost:8080/api/customers -v -X POST -H "Content-Type: application/json" -d "{\"firstName\":\"Tamako\",\"lastName\":\"Nobi\"}" 
```

## 顧客の1件更新
```bash
curl http://localhost:8080/api/customers/1 -v -X PUT -H "Content-Type: application/json" -d "{\"firstName\":\"Nobio\",\"lastName\":\"Nobi\"}" 
```

## 顧客の削除
```bash
curl http://localhost:8080/api/customers/1 -v -X DELETE 
```

# その他

## テスト接続画面
http://localhost:8080/

## H2 Console
http://localhost:8080/h2-console/