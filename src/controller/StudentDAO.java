/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Room;
import entities.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBConnection;

/**
 *
 * @author anhquan12
 */
public class StudentDAO {

    PreparedStatement ps = null;
    Connection connection = null;
    ResultSet rs = null;

    public Student addNew(Student student) {
//Insert dữ liệu thì nhớ Insert table chứa Primary key trước.
//Sau đó mới Insert dữ liệu cho table chứa Foreign key
        DBConnection conn = new DBConnection();
        connection = conn.getConnection();
        try {
//            String sql1 = "insert into room(room_id , clan) values(2,'HaNoi')";
//            ps = connection.prepareStatement(sql1);

            String sql2 = "insert into student(id, room_id, name, dob) values (?,?,?,?)";
            ps = connection.prepareStatement(sql2);
            ps.setInt(1, 2);
            ps.setInt(2, 2);
            ps.setString(3, "VaneLove");
            ps.setString(4, "1988-06-07");
            int executeUpdate = ps.executeUpdate();
            if (executeUpdate > 0) {
                System.out.println("Insert data success !");
                return student;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conn.close(rs, ps, connection);
        }
        return null;
    }
//    public static void main(String[] args) {
//        StudentDAO dao = new StudentDAO();
//        Student student = new Student();
//        dao.addNew(student);
//    }

    public ArrayList<Student> getAll() {
        ArrayList<Student> list = null;
        DBConnection conn = new DBConnection();
        connection = conn.getConnection();
        String sql = "select * from student inner join room on student.room_id = room.room_id";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int room_id = rs.getInt("room_id");
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String clan = rs.getString("clan");
                Room room = new Room(room_id, clan);
                Student student = new Student(id, name, dob, room);
                list.add(student);
                System.out.println(student.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close(rs, ps, connection);
        }
        return list;

    }

//    public static void main(String[] args) {
//        StudentDAO dao = new StudentDAO();
//        Student student = new Student();
//        dao.getAll();
//    }
    public boolean updateById(int id, String name, String dob) {
        //Update 2 table có quan hệ thì phải lần lượt update từng cái
        DBConnection conn = new DBConnection();
        connection = conn.getConnection();
        int result = 0;
        String sql = "update student set student.name , dob where id=?";
        id = new Scanner(System.in).nextInt();
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, dob);
            result = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close(rs, ps, connection);
        }

        return result > 0;
    }

    public boolean deleteById(int id) {
        //Delete dữ liệu thì Delete dữ liệu trong table chứa Foreign key trước.
        //Sau đó mới delete dữ liệu trong table chứa Primary key
        DBConnection conn = new DBConnection();
        connection = conn.getConnection();
        int result = 0;
        id = new Scanner(System.in).nextInt();
        String sql = "delete from student where id=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close(rs, ps, connection);
        }

        return result > 0;
    }

    public Student getById(int id) {
        DBConnection conn = new DBConnection();
        connection = conn.getConnection();
        Student student = null;
        String sql = "select * from student where id=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int stId = rs.getInt("id");
                int room_id = rs.getInt("room_id");
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String clan = rs.getString("clan");
                Room room = new Room(room_id, clan);
                student = new Student(stId, name, dob, room);
                System.out.println(student.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            conn.close(rs, ps, connection);
        }
        return student;
    }
}
