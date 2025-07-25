-- 行がアップデートされた際にupdate_atを更新
CREATE FUNCTION set_updated_at() RETURNS TRIGGER AS
$$
BEGIN
    IF (TG_OP = 'UPDATE') THEN
        NEW.updated_at := CURRENT_TIMESTAMP;
        RETURN NEW;
    END IF;
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- テーブルにトリガーを設定
CREATE TRIGGER trg_users_updated_at
    BEFORE UPDATE
    ON users
    FOR EACH ROW
EXECUTE PROCEDURE set_updated_at();
