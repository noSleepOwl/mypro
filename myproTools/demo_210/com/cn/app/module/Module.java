package com.cn.app.module;

public interface Module<T,R> {
/**模块的名称
 * @return
 */
public String getName();
/**
 * 执行
 */
public boolean run();
/**
 * @param t
 * @return
 */
public R moduleBody(T t);
}
