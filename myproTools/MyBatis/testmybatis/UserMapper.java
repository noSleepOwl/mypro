package testmybatis;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	public int queryIntBysql(@Param("SQL") String SQL);
	public Map<String, Object> queryMapBysql(@Param("SQL") String SQL);
	public Map<String, Object> queryListMapBysql(@Param("SQL") String SQL);
	public int executeSql(@Param("SQL") String SQL);
	
}
