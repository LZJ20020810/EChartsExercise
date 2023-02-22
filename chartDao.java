package org.example.Dao;

import com.alibaba.fastjson.JSONArray;
import org.example.Connect.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.example.Bean.ec;

public class chartDao {
    public List<ec> search(){
        String sql="select * from ec";

        List<ec> list=new ArrayList<>();
        Connection conn=DBUtil.getConn();
        Statement stmt=null;
        ResultSet rs=null;

        try {
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);

            ec e=null;
            while(rs.next()){
                String name=rs.getString("name");
                int count=Integer.parseInt(rs.getString("count"));

                System.out.println(name);
                System.out.println(count);//成功导出数据
                e=new ec(name,count);
                list.add(e);
                System.out.println(list);//成功进入到list集合里面
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.close(rs,stmt,conn);
        }

        return list;
    }
}
