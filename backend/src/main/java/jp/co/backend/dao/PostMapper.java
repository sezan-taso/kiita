package jp.co.backend.dao;

import org.apache.ibatis.annotations.Mapper;

import jp.co.backend.dao.generated.PostGeneratedMapper;

/**
 * postsテーブルに対するマッパー
 */
@Mapper
public interface PostMapper extends PostGeneratedMapper {

}
