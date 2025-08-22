package jp.co.backend.dao;

import static jp.co.backend.dao.generated.UserGeneratedDynamicSqlSupport.id;
import static jp.co.backend.dao.generated.UserGeneratedDynamicSqlSupport.loginId;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

import jp.co.backend.dao.generated.UserGeneratedMapper;
import jp.co.backend.models.generated.User;

/**
 * usersテーブルに対するマッパー
 */
@Mapper
public interface UserMapper extends UserGeneratedMapper {

	/**
	 * 指定したIDのユーザーを取得
	 * 
	 * @param ids ユーザーID
	 * @return ユーザー
	 */
	default List<User> selectByIds(@Param("ids") List<Integer> ids) {
		SelectDSLCompleter completer = select ->
			select.where(id, isIn(ids))
				.orderBy(id);
			
		return select(completer);
	}
	
	/**
	 * 指定したログインIDのユーザー件数を取得
	 * 
	 * @param targetLoginId ログインID
	 * @return ユーザー件数
	 */
	default long countByLoginId(String targetLoginId) {
		return count(c -> c.where(loginId, isEqualTo(targetLoginId)));
	}
}
