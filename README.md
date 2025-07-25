# 構築手順
## WSL2導入
管理者でPowerShellを実行し、以下のコマンド入力後PC再起動
```
wsl --install -d Ubuntu-20.04
wsl --set-default-version 2
wsl --set-default Ubuntu-20.04
wsl --list –-v
ubuntu2004 config --default-user root
```
※WSLの設定ファイルを「C:\Users\＜ユーザ名＞.wslconfig」にスペックに合わせて変更しファイルを新規作成
```
[wsl2]
memory=8GB
processors=4
```

## WSL環境構築
アプリからUbuntsを実行し、以下のコマンドで必要なファイルを配置する
```
git clone https://gitlab.com/progress-all/piita.git
```

## VSCodeの起動
```
cd piita
code .
```

# 開発方法
## データ、マッパーの準備
```
docker-compose run --rm flyway-clean
docker-compose run --rm flyway-migrate
docker compose run --rm backend ./gradlew mbGenerator
```

## POSTGRESへのログイン
```
docker exec -it application-database-1 psql -U postgres -d application
```

## gradlewのビルド
```
docker-compose run --rm gradle-build
```
