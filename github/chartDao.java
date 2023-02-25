package org.example.Dao;

import com.alibaba.fastjson.JSONArray;
import org.example.Connect.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.example.Bean.ec;

public class chartDao {
    public List<ec> search(String whe){

        List<ec> list=new ArrayList<>();
        Connection conn=DBUtil.getConn();
        PreparedStatement stmt=null;
        ResultSet rs=null;

        String sql="select * from ec"+" where name like ?";

        try {
            stmt=conn.prepareStatement(sql);
            stmt.setString(1,"%"+whe+"%");

            rs=stmt.executeQuery();

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
            DBUtil.close(rs, (Statement) stmt,conn);
        }

        return list;
    }
}
