package org.example.test3yashpc2;

public class order {
    private int orderId;
    private String cName;
    private String mobNo;
    private String size;
    private String topping;
    private double total;

    public order(int orderId, String cName, String mobNo, String size, String topping, double total) {
        this.orderId = orderId;
        this.cName = cName;
        this.mobNo = mobNo;
        this.size = size;
        this.topping = topping;
        this.total = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
