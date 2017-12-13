package cn.hwolf.common.entity;

import cn.hwolf.modules.entity.po.SysUserPO;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * @author hwolf
 * @email h.wolf@qq.com
 * @date 2017/12/2.
 */
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 当前用户
     */
    protected SysUserPO currentUser;

    /**
     * 当前实体分页对象
     */
    protected PageInfoDTO page;

    /**
     * 自定义SQL（SQL标识，SQL内容）
     */
    protected Map<String, String> sqlMap;

    public SysUserPO getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(SysUserPO currentUser) {
        this.currentUser = currentUser;
    }

    public PageInfoDTO getPage() {
        return page;
    }

    public void setPage(PageInfoDTO page) {
        this.page = page;
    }

    public Map<String, String> getSqlMap() {
        if (sqlMap == null){
            sqlMap = Maps.newHashMap();
        }
        return sqlMap;
    }

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

    public BaseEntity() {

    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    public static final String DEL_FLAG_AUDIT = "2";

}