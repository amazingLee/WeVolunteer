package com.example.core.listener;

import com.example.model.AccessTokenBO;

/**
 * 项目名称：BaseAndroid
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/7/23 15:05
 * 修改备注：
 */
public interface AccessTokenListener {

    public void success(AccessTokenBO accessTokenBO);

    public void fail();
}
