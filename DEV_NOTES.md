API作成順
1. リポジトリ（Repository） ← JPAやMyBatis
2. モデル（ DTO / DAO / Request / Response）
3. サービス（ Exception / Service） ← ビジネスロジック
4. コントローラ（Controller） ← APIエンドポイント
5. テスト（Unit / Integration）