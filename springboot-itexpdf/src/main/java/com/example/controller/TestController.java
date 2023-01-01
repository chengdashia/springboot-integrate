package com.example.controller;

import com.example.dao.UserDao;
import com.example.domain.User;
import com.example.excel.EasyExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/8/16 19:43
 */
@RestController
public class TestController {

    @Autowired
    private UserDao userDao;

    @GetMapping("export")
    public void export(HttpServletResponse response) throws IOException {
        List<User> users = userDao.selectList(null);
        Long aLong = userDao.selectCount(null);
        System.out.println(aLong);
        //EasyExcelUtil.writerExcel(response,"用户",users,User.class);

        EasyExcelUtil.writeWithSheetsWeb(response,"111",User.class,users);

    }

    //@GetMapping("export2")
    //public void export2(HttpServletResponse response) throws IOException {
    //    Workbook workbook;
    //    ExportParams params  = new ExportParams("大数据测试", "测试", ExcelType.XSSF);
    //    /**
    //     * params:（表格标题属性）筛选条件，sheet值
    //     * MsgClient：表格的实体类
    //     */
    //    List<User> users = userDao.selectList(null);
    //    //List<List<User>> list = ListUtil.partition(users, 10000);
    //    int totalPage = (users.size() / 10000) + 1;
    //    int pageSize = 10000;
    //    workbook = ExcelExportUtil.exportBigExcel(params, User.class, new IExcelExportServer() {
    //        @Override
    //        public List<Object> selectListForExcelExport(Object o, int page) {
    //            if (page > totalPage) {
    //                return null;
    //            }
    //
    //            // fromIndex开始索引，toIndex结束索引
    //            int fromIndex = (page - 1) * pageSize;
    //            int toIndex = page != totalPage ? fromIndex + pageSize :users.size();
    //
    //            List<Object> list = new ArrayList<>();
    //            list.addAll(users.subList(fromIndex, toIndex));
    //
    //            return list;
    //
    //        }
    //    },totalPage);
    //    ExcelUtils.downLoadExcel("111",response,workbook);
    //
    //
    //
    //}
}
