INSERT INTO "users" (login_id, password, display_name)
VALUES ('user1', '$2a$10$uBB55DqSI9lRbNHkzQj7q.CMotDKFqbmE.AHsEY2tF2VmhP3cocpC', 'ユーザー１'),
       ('user2', '$2a$10$uBB55DqSI9lRbNHkzQj7q.CMotDKFqbmE.AHsEY2tF2VmhP3cocpC', 'ユーザー２'),
       ('user3', '$2a$10$uBB55DqSI9lRbNHkzQj7q.CMotDKFqbmE.AHsEY2tF2VmhP3cocpC', 'ユーザー３');

INSERT INTO "posts" (author_id, title, body, posted_at, repost_at, is_draft)
VALUES (1, '【SQL】SELECT文の実行結果もっと見やすくならないですか？',
        '後輩ちゃん「SELECT文の実行結果もっと見やすく表示できないのかな」
        後輩ちゃん「カラム量が多かったり、レコードの中身が多かったりすると表示が崩れて実行結果が見ずらいんだよな～」

        すずこ「そんな時は、メタコマンドを使おう！」',
        '2020-01-01 10:00:00', '2021-01-01 10:00:00', false),
       (2, 'AIが作った画像か判別するAIを4hで作ったら割と精度出たのでWebサービスとして公開した件', 'ボディ2', '2020-01-02 10:00:00', null, false),
       (3, 'GASでスイッチサイエンスの在庫数を取得して表にまとめてみた', 'ボディ3', '2020-01-03 10:00:00', null, false),
       (1, '【docker】M1 Macでdockerのmysqlが爆死するけど何故？【mysql】', 'ボディ4', '2020-01-04 10:00:00', null, false),
       (2, 'TAURIでファイルダイアログを開き、ローカルファイルにアクセスするには', 'ボディ5', '2020-01-05 10:00:00', null, false),
       (3, 'Laravel　多対多のリレーション', 'ボディ6', '2020-01-06 10:00:00', null, false),
       (1, 'Nitro + TypeScript で作る軽量APIサーバ', 'ボディ7', '2020-01-07 10:00:00', null, false),
       (2, 'Reactを学ぶ上で知っておくべきJavaScriptの基本概念', 'ボディ8', '2020-01-08 10:00:00', null, false),
       (3, 'サバイバルTypeScriptを読むまで知らなかったこと', 'ボディ9', '2020-01-09 10:00:00', null, false),
       (1, 'IBM Cloud VPCの仮想インスタンスでPython(Django)アプリ作成', 'ボディ10', '2020-01-10 10:00:00', null, false),
       (2, 'SQL文を自動生成するプログラムをつくる⑤', 'ボディ11', '2020-03-10 10:00:00', null, false),
       (3, 'Android studioアプリ開発の解説動画を観て、おみくじアプリを作りました。', 'ボディ12', '2020-03-09 10:00:00', null, false),
       (1, '【kabuslib】auカブコム証券のkabuステーションREST APIをswaggerで生成したライブラリ', 'ボディ13', '2020-03-08 10:00:00', null, false),
       (2, 'JAVAの命名規則について', 'ボディ14', '2020-03-07 10:00:00', null, false),
       (3, 'Apple Silicon Mac 上の Docker コンテナで Google Chrome をインストールする', 'ボディ15', '2020-03-06 10:00:00', null, false),
       (1, 'Node.jsでDatabaseに接続する', 'ボディ16', '2020-03-05 10:00:00', null, false),
       (2, 'Javaで何か作ってみる(2022/10/30 Day10)', 'ボディ17', '2020-03-04 10:00:00', null, false),
       (3, '【android】Activityの切り替えと値の渡し方', 'ボディ18', '2020-03-02 10:00:00', null, false),
       (1, '【android】画面遷移について', 'ボディ19', '2020-03-01 10:00:00', null, false),
       (2, 'Javaで何か作ってみる(2022/10/29 Day9)', 'ボディ20', '2020-04-01 10:00:00', null, false),
       (3, 'System.currentTimeMillis() と System.nanoTime() の違い', 'ボディ21', '2020-04-02 10:00:00', null, false),
       (1, 'dockerでmysqlを使ったときに必要になった設定', 'ボディ22', '2020-04-03 10:00:00', null, false),
       (2, '【android】ボタンクリック時のイベント', 'ボディ23', '2020-04-04 10:00:00', null, false);
