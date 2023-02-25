# Spring Boot Shop Application

## Introduction

This is a Spring Boot project which contains a data model, repositories and a set of pre-defined data.
The system automatically loads data into H2 in-memory database when the Spring Boot app starts up.

## Data Model

The data model consists of Customer, Order and Product.
Customers can place multiple orders and each order contains a number of products.

## Description

Every customer has a status which is updated based on the total amount spent.
This is calculated depending on the orders and returns he makes.

## Things learned in this project

- H2 & H2Console
- JPA Repository
- Lombok
- Lambda
- Streams
