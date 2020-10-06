package com.example.justjava3;

public class Order {

    //Attributes Of Every Order
    private int _id;
    private String _custName;
    private int _saleAmount;

    //Constructor
    public Order(String _custName, int _saleAmount) {
        this._custName = _custName;
        this._saleAmount = _saleAmount;
    }

    //Get Method
    public int get_id() {
        return _id;
    }

    public String get_custName() {
        return _custName;
    }

    public int get_saleAmount() {
        return _saleAmount;
    }

    //Set Method
    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_custName(String _custName) {
        this._custName = _custName;
    }

    public void set_saleAmount(int _saleAmount) {
        this._saleAmount = _saleAmount;
    }
}
