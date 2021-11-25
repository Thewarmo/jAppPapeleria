/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.models;

/**
 *
 * @author abayo
 */
public class Producto {
    private Integer pro_id;
    private String pro_nombre;
    private Integer pro_precio;
    private Integer pro_stock;

    public Producto(Integer pro_id, String pro_nombre, Integer pro_precio, Integer pro_stock) {
        this.pro_id = pro_id;
        this.pro_nombre = pro_nombre;
        this.pro_precio = pro_precio;
        this.pro_stock = pro_stock;
    }

    public Integer getPro_id() {
        return pro_id;
    }

    public void setPro_id(Integer pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_nombre() {
        return pro_nombre;
    }

    public void setPro_nombre(String pro_nombre) {
        this.pro_nombre = pro_nombre;
    }

    public Integer getPro_precio() {
        return pro_precio;
    }

    public void setPro_precio(Integer pro_precio) {
        this.pro_precio = pro_precio;
    }

    public Integer getPro_stock() {
        return pro_stock;
    }

    public void setPro_stock(Integer pro_stock) {
        this.pro_stock = pro_stock;
    }
    
}
