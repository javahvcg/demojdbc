/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author anhquan12
 */
public class Room {
    private int room_id;
    private String clan;
    
    private ArrayList<Student> list = new ArrayList<>();
    
    public Room(int room_id, String clan) {
        this.room_id = room_id;
        this.clan = clan;
    }

    public Room(String clan) {
        this.clan = clan;
    }
    

    public Room() {
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public ArrayList<Student> getList() {
        return list;
    }

    public void setList(ArrayList<Student> list) {
        this.list = list;
    }

}
