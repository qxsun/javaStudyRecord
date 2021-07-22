/**
 * Project:TODO ADD PROJECT NAME
 * Modify Information:
 * ================================================================
 * Author         Date           Description
 * ------------   ----------      --------------------------------
 * ums_qxsun        2021/6/28         TODO:
 * ================================================================
 * Copyright (c) 银联商务股份有限公司 www.chinaums.com
 */
package com.qxsun.stu.designModel;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Desc: TODO ADD DESC
 * <p>
 * Function:
 * <dl>
 * <dt>核心功能点1</dt>
 * <dd>核心功能点1说明</dd>
 * <dt>核心功能点2</dt>
 * <dd>核心功能点2说明</dd>
 * </dl>
 *
 * @app <服务名称英文缩写>
 * @layer <代码所在分层>
 * @refApp <依赖服务的英文缩写>
 * @author <a href="mailto:ums_qxsun@chinaums.com">ums_qxsun</a>
 * @since 2021/6/28
 * @version 2021/6/28
 */
public class DecorateModel {
    DataInputStream is = new DataInputStream(
            new BufferedInputStream(
                    new FileInputStream("")));

    public DecorateModel() throws FileNotFoundException {
    }
}
