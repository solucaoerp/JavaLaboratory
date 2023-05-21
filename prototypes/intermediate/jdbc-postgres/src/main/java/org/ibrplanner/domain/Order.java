package org.ibrplanner.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private Long id;
    private Double latitude;
    private Double Longitude;
    private Instant moment;
    private OrderStatus status;
    private List<Product> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", Longitude=" + Longitude +
                ", moment=" + moment +
                ", status=" + status +
                '}';
    }
}
